package com;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class Main2 {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		router.route().handler((RoutingContext routingContext) -> {

			// This handler will be called for every request
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "application/json");

			// Write to the response and end it
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.end("{\"name\":\"tamajit\",\"skill\":[\"java\",\"spring\"]}");
		});

		server.requestHandler(arg -> router.accept(arg)).listen(8080);
	}
}
