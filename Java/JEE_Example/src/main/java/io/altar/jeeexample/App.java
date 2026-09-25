package io.altar.jeeexample;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Main JAX-RS application class that configures the REST API endpoints. This
 * class serves as the entry point for the Java EE web application. All REST
 * endpoints will be accessible under the "/api" path.
 */
@ApplicationPath("api")
public class App extends Application {

}
