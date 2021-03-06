package example_eventbus;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class EventBusReceiverVerticle extends AbstractVerticle {

    private String name = null;

	public EventBusReceiverVerticle(String name) {
        this.name = name;
    }

	@Override
    public void start(Future<Void> startFuture) {
        vertx.eventBus().consumer("anAddress", message -> {
            System.out.println(this.name + 
                " received message: " +
                message.body());
        });
    }
}