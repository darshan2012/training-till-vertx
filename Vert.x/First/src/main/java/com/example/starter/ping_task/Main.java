package com.example.starter.ping_task;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Boolean checkValidIp(String ip) {
        String ipv4Regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9][0-9]?)$";
        String ipv6Regex = "^([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4}|:)$|^([0-9a-fA-F]{1,4}:){1,7}:$|^:((:[0-9a-fA-F]{1,4}){1,7}|:)$|^([0-9a-fA-F]{1,4}:){1,6}(:[0-9a-fA-F]{1,6})$";
        return Pattern.compile(ipv4Regex).matcher(ip).matches() || Pattern.compile(ipv6Regex).matcher(ip).matches();
    }

    public static void startProvisioning(String ip, Vertx vertx, WorkerExecutor workerExecutor) {
        vertx.setPeriodic(60000, id -> {
            workerExecutor.executeBlocking(future -> {
                try {
                    ProcessBuilder builder = new ProcessBuilder("ping", "-q", "-c", "10", ip);
                    Process process = builder.start();
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String str;
                    List<String> output = new ArrayList<>();
                    while ((str = stdInput.readLine()) != null) {
                        output.add(str);
                    }

                    future.complete(output);
                } catch (IOException e) {
                    future.fail(e);
                }
            }, res -> {
                if (res.succeeded()) {
                    List<String> output = (List<String>) res.result();
                    processPingOutput(ip, output, vertx);
                } else {
                    System.out.println("Error during provisioning: " + res.cause().getMessage());
                }
            });
        });
    }

    private static void processPingOutput(String ip, List<String> output, Vertx vertx) {
        int size = output.size();

        if (size < 2) {

            System.out.println("Ping output is incomplete.");

            return;
        }

        String packetInfoLine = output.get(size - 2);
        String latencyInfoLine = output.get(size - 1);

        // Regex patterns for extracting packet and latency info
        String packetInfoRegex = "(\\d+) packets transmitted, (\\d+) received, (\\d+)% packet loss, time (\\d+)ms";
        String latencyInfoRegex = "rtt min/avg/max/mdev = ([0-9.]+)/([0-9.]+)/([0-9.]+)/([0-9.]+) ms";

        // Extract and display packet information
        Matcher packetMatcher = Pattern.compile(packetInfoRegex).matcher(packetInfoLine);

        StringBuilder data = new StringBuilder();


        if (packetMatcher.find()) {


            data.append("Packets transmitted: ").append(packetMatcher.group(1)).append("\n");
            data.append("Packets received: ").append(packetMatcher.group(2)).append("\n");
            data.append("Packet loss: ").append(packetMatcher.group(3)).append("%\n");
            data.append("Time taken: ").append(packetMatcher.group(4)).append(" ms\n");

        } else {
            System.out.println("No match found for packet information.");
        }

        // Extract and display latency information
        Matcher latencyMatcher = Pattern.compile(latencyInfoRegex).matcher(latencyInfoLine);
        if (latencyMatcher.find()) {


//            System.out.println("Minimum latency: " + minLatency + " ms");
//            System.out.println("Maximum latency: " + maxLatency + " ms");

            data.append("Minimum latency: ").append(Double.parseDouble(latencyMatcher.group(1))).append(" ms\n");
            data.append("Average latency: ").append(Double.parseDouble(latencyMatcher.group(2))).append(" ms\n");
            data.append("Maximum latency: ").append(Double.parseDouble(latencyMatcher.group(3))).append(" ms\n");


            // Write latency info to a file asynchronously

        } else {
            System.out.println("No match found for latency information.");
        }
        writeToFile(ip, data.toString(), vertx);
    }

    private static void writeToFile(String ip, String data, Vertx vertx) {
        FileSystem fileSystem = vertx.fileSystem();

        // Create a directory for the IP if it doesn't exist
        fileSystem.mkdir("ping/"+ip, result -> {
            if (result.succeeded() || fileSystem.existsBlocking("ping/"+ip)) {
                // Generate a timestamp for the filename
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String filename = "ping/" + ip + "/" + timestamp + ".txt";

                // Write the data to the file
                fileSystem.writeFile(filename, Buffer.buffer(data), writeRes -> {
                    if (writeRes.succeeded()) {
                        System.out.println("Ping data successfully written to " + filename);
                    } else {
                        System.out.println("Failed to write data to file: " + writeRes.cause().getMessage());
                    }
                });
            } else {
                System.out.println("Failed to create directory: " + result.cause().getMessage());
            }
        });
    }


    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        int poolSize = 10;
        long maxExecuteTime = 2;
        TimeUnit maxExecuteTimeUnit = TimeUnit.MINUTES;

        WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool", poolSize, maxExecuteTime, maxExecuteTimeUnit);

        while (true) {
            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter valid IP: ");
                String ip = inputReader.readLine();

//                if (!checkValidIp(ip)) {
//                    System.out.println("Invalid IP[" + ip + "]");
//                    continue;
//                }

                ProcessBuilder builder = new ProcessBuilder("fping", ip);
                Process process = builder.start();
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String str = stdInput.readLine();

                if (!str.equals(ip + " is alive")) {
                    System.out.println("Host[" + ip + "] is down.");
                } else {
                    System.out.println("Host[" + ip + "] is up.");
                    System.out.print("Do you want to provision? [Y/N] : ");
                    String provision = inputReader.readLine();

                    if (provision.equalsIgnoreCase("y")) {
                        System.out.println("Starting the provisioning...");
                        startProvisioning(ip, vertx, executor);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
