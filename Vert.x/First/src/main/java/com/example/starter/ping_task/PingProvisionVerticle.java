package com.example.starter.ping_task;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;
import io.vertx.core.buffer.Buffer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingProvisionVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        vertx.deployVerticle(new PingProvisionVerticle());
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter IP address to ping: ");
        String ip = scanner.nextLine();

        if (isValidIP(ip)) {
            vertx.executeBlocking(promise -> {
                if (pingHost(ip)) {
                    System.out.println("Host is alive, starting provisioning...");
                    startProvisioning(ip);
                    promise.complete();
                } else {
                    System.out.println("Host is unreachable.");
                    promise.fail("Host is unreachable");
                }
            }, res -> {
                if (res.failed()) {
                    System.out.println("Error: " + res.cause().getMessage());
                }
            });
        } else {
            System.out.println("Invalid IP address.");
        }
    }

    // Method to validate the IP address
    private boolean isValidIP(String ip) {
        String ipv4Regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9][0-9]?)$";
        String ipv6Regex = "([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|::|...";  // Simplified for brevity
        return ip.matches(ipv4Regex) || ip.matches(ipv6Regex);
    }

    // Method to ping the host and check if it's alive
    private boolean pingHost(String ip) {
        try {
            ProcessBuilder builder = new ProcessBuilder("ping", "-c", "1", ip);
            Process process = builder.start();
            int exitCode = process.waitFor();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            while ((str = stdInput.readLine()) != null) {
                System.out.println(str);
            }

            return (exitCode == 0);  // 0 means the ping was successful
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to start provisioning and poll the ping output every 60 seconds
    private void startProvisioning(String ip) {
        vertx.setPeriodic(60000, id -> {
            vertx.executeBlocking(promise -> {
                List<String> pingOutput = runPing(ip);
                if (pingOutput != null) {
                    JsonObject pingStats = extractPingStatistics(pingOutput);
                    if (pingStats != null) {
                        writeToFile(pingStats.encodePrettily());
                        promise.complete();
                    } else {
                        promise.fail("Failed to extract ping statistics");
                    }
                } else {
                    promise.fail("Ping command failed");
                }
            }, res -> {
                if (res.failed()) {
                    System.out.println("Error: " + res.cause().getMessage());
                }
            });
        });
    }

    // Method to execute the ping command and return output as a list of strings
    private List<String> runPing(String ip) {
        try {
            ProcessBuilder builder = new ProcessBuilder("ping", "-q", "-c", "10", ip);
            Process process = builder.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String str;
            List<String> output = new ArrayList<>();
            while ((str = stdInput.readLine()) != null) {
                output.add(str);
            }

            int exitCode = process.waitFor();
            return (exitCode == 0) ? output : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to extract ping statistics from the output
    private JsonObject extractPingStatistics(List<String> output) {
        String packetInfoRegex = "(\\d+) packets transmitted, (\\d+) received, (\\d+)% packet loss";
        String latencyInfoRegex = "rtt min/avg/max/mdev = ([0-9.]+)/([0-9.]+)/([0-9.]+)/([0-9.]+) ms";

        Pattern packetPattern = Pattern.compile(packetInfoRegex);
        Pattern latencyPattern = Pattern.compile(latencyInfoRegex);

        JsonObject pingStats = new JsonObject();
        for (String line : output) {
            Matcher packetMatcher = packetPattern.matcher(line);
            if (packetMatcher.find()) {
                pingStats.put("packets_transmitted", Integer.parseInt(packetMatcher.group(1)))
                    .put("packets_received", Integer.parseInt(packetMatcher.group(2)))
                    .put("packet_loss", Integer.parseInt(packetMatcher.group(3)));
            }

            Matcher latencyMatcher = latencyPattern.matcher(line);
            if (latencyMatcher.find()) {
                pingStats.put("min_latency", Double.parseDouble(latencyMatcher.group(1)))
                    .put("max_latency", Double.parseDouble(latencyMatcher.group(3)));
            }
        }
        return pingStats;
    }

    // Method to write the ping statistics to a file
    private void writeToFile(String data) {
        FileSystem fs = vertx.fileSystem();
        String filePath = "ping_output.txt";
        fs.writeFile(filePath, Buffer.buffer(data), res -> {
            if (res.succeeded()) {
                System.out.println("Ping stats written to file: " + filePath);
            } else {
                System.out.println("Failed to write file: " + res.cause().getMessage());
            }
        });
    }
}
