package com.shashi.threading.httpserverthroughput;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpServerThroughputApplication {

	public static String fileName = "/resources/text-file.txt";

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HttpServerThroughputApplication.class, args);
		HttpServerImpl.createServer();
	}

}
