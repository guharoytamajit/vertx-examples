package example_multiverticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MainVerticle2 extends AbstractVerticle {

	// @Override
	// public void init(Vertx vertx, Context context) {
	// super.init(vertx, context);
	// fooRepository = Guice
	// .createInjector(new FooModule())
	// .getInstance(FooRepository.class);
	// }

	@Override
	public void start(Future<Void> startFuture) {
		System.out.println("starting MainVerticle2");
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.end("hello2!!");
		});

		server.requestHandler(arg -> router.accept(arg)).listen(8081);
	}

}