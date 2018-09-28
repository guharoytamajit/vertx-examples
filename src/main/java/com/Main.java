package com;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class Main {
public static void main(String[] args) {
	Vertx vertx = Vertx.vertx();

	HttpServer server = vertx.createHttpServer();

	server.requestHandler((HttpServerRequest request) -> {

	  // This handler gets called for each request that arrives on the server
	  HttpServerResponse response = request.response();
	  response.putHeader("content-type", "text/plain");

	  response.setChunked(true);
	  // Write to the response and end it
	  response.write("Hello World!");
	  response.end();
	});

	server.listen(8080);
}
}
