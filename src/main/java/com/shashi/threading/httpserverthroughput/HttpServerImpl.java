package com.shashi.threading.httpserverthroughput;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
// import javax.xml.ws.spi.http.HttpContext;
// import javax.xml.ws.spi.http.HttpExchange;
// import javax.xml.ws.spi.http.HttpHandler;

public class HttpServerImpl {

    public static String fileName =
            "\\Users\\shash\\code\\corejava\\threading\\http-server-throughput\\src\\main\\resources\\text-file.txt";
    // String data = new String(Files.readAllBytes(Paths.get(fileName)));

    public static void createServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
        HttpHandler handler = new CustomHttpHandler();
        server.createContext("/search", handler);
        server.start();
    }

    public static class CustomHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // TODO Auto-generated method stub
            exchange.getRequestURI().getQuery();
            String query = exchange.getRequestURI().getQuery();
            String[] pathc = query.split("=");
            System.out.println("inside handle");
            int count = getWordCount(pathc[1]);
            byte[] response = Long.toString(count).getBytes();
            exchange.sendResponseHeaders(200, response.length);
            OutputStream out = exchange.getResponseBody();
            out.write(response);
            out.close();

        }

        private int getWordCount(String value) throws IOException {
            System.out.println("outside while");
            String data = new String(Files.readAllBytes(Paths.get(fileName)));
            int count = 0;
            int index = 0;
            System.out.println("start while");
            while (index >= 0) {
                System.out.println("inside while");
                index = data.indexOf(value, index);
                if (index >= 0) {
                    count++;
                    index++;
                }
            }
            System.out.println(count);
            return count;
        }

    }

}
