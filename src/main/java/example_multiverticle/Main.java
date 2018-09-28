package example_multiverticle;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main {
public static void main(String[] args) {
	  Vertx vertx = Vertx.vertx();
	  vertx.deployVerticle(MainVerticle.class,new DeploymentOptions().setInstances(2));
	  vertx.deployVerticle(MainVerticle2.class,new DeploymentOptions(), res -> {
		  if (res.succeeded()) {
			    System.out.println("Deployment id is: " + res.result());
			  } else {
			    System.out.println("Deployment failed!");
			  }
			});
	  System.out.println("vertex started");
}
}
