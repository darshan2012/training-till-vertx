package com.example.starter.fileSystem;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.FileSystem;

public class FileSystemUsage extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    FileSystem fs = vertx.fileSystem();
  }
}
