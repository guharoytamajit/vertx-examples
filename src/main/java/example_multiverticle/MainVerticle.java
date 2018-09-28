package example_multiverticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

	// @Override
	// public void init(Vertx vertx, Context context) {
	// super.init(vertx, context);
	// fooRepository = Guice
	// .createInjector(new FooModule())
	// .getInstance(FooRepository.class);
	// }

	@Override
	public void start(Future<Void> startFuture) {
		System.out.println("starting MainVerticle");
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		HttpClient httpClient = vertx.createHttpClient();

		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.setChunked(true);
			//calling webservice in another verticle
			httpClient.getNow(8081, "localhost", "/", (HttpClientResponse httpClientResponse) -> {
				httpClientResponse.bodyHandler((Buffer buffer) -> {
					response.write(buffer);
					response.end("hello!!");
				});
			});
		});

		server.requestHandler(arg -> router.accept(arg)).listen(8080);
	}

}