package example_jdbc;

import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

public class JdbcVerticle extends AbstractVerticle {
	public void start() throws Exception {

	    final JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
	        .put("url", "jdbc:mysql://localhost:3306/test")
	        .put("driver_class", "com.mysql.jdbc.Driver")
	        .put("max_pool_size", 30)
	        .put("user", "root")
	        .put("password", ""));

	    client.getConnection(conn -> {
	      if (conn.failed()) {
	        System.err.println(conn.cause().getMessage());
	        return;
	      }

	      final SQLConnection connection = conn.result();
	      connection.execute("create table test(id int primary key, name varchar(255))", res -> {
	        if (res.failed()) {
	          throw new RuntimeException(res.cause());
	        }
	        // insert some test data
	        connection.execute("insert into test values(1, 'Hello')", insert -> {
	          // query some data
	          connection.query("select * from test", rs -> {
	            ResultSet resultSet = rs.result();
	 
	           
				for (JsonArray line : resultSet.getResults()) {
	              System.out.println(line.encode());
	            }
				for (JsonObject line : resultSet.getRows()) {
		              System.out.println( Json.decodeValue(line.encode(),Test.class ));
		            }
	            // and close the connection
	            connection.close(done -> {
	              if (done.failed()) {
	                throw new RuntimeException(done.cause());
	              }
	            });
	          });
	        });
	      });
	    });
	  }
}
