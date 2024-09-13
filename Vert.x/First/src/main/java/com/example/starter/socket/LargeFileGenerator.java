package com.example.starter.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LargeFileGenerator {
    public static void main(String[] args) {
        String fileName = "1GBFile.txt";
        long fileSize = 1L * 1024 * 1024 * 1024; // 1 GB in bytes
        int bufferSize = 8192; // 8 KB buffer
        byte[] buffer = new byte[bufferSize];

        // Fill buffer with random data or repeated content
        String data = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        byte[] content = data.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = content[i % content.length];
        }

        // Generate the file
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            long bytesWritten = 0;

            while (bytesWritten < fileSize) {
                fos.write(buffer);
                bytesWritten += buffer.length;
            }

            System.out.println("1GB file generated: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

