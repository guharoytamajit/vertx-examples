package example_jdbc;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {
public static void main(String[] args) {
	 Vertx vertx = Vertx.vertx();
	  vertx.deployVerticle(JdbcVerticle.class,new DeploymentOptions());
}
}
