package example_eventbus;

import io.vertx.core.Vertx;

public class Main {
public static void main(String[] args) throws InterruptedException {
	Vertx vertx = Vertx.vertx();

	vertx.deployVerticle(new EventBusReceiverVerticle("R1"));
	vertx.deployVerticle(new EventBusReceiverVerticle("R2"));

	Thread.sleep(3000);
	vertx.deployVerticle(new EventBusSenderVerticle());
}
}
