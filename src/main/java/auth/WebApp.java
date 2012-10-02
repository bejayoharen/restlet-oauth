package auth;

import java.util.logging.Level;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;

public class WebApp {
	static int PORT;
	public static String MY_VALIDATION_REF = "http://localhost:8080/oauth/validate" ;

	public static void main(String[] args) throws Exception {
		runWeb();
	}
	
	private static void runWeb() {
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		
		PORT = Integer.parseInt(webPort);

		try {
			// Create a new Component.
			Component component = new Component();

			component.getServers().add(Protocol.HTTP, PORT);
			component.getClients().add(Protocol.HTTP);
			component.getClients().add(Protocol.HTTPS);
			component.getClients().add(Protocol.RIAP);
			component.getClients().add(Protocol.CLAP);

			// Attach the application:
			component.getDefaultHost().attach("/oauth", new OAuthApplication());
			component.getDefaultHost().attach("/protected", new ProtectedApplication());
			
			// Add a new HTTP server listening on PORT
//			Server s = new Server(new Context(),Protocol.HTTP, PORT);
//			component.getServers().add(s);

			// Start the component.
			component.start();
		} catch (Exception e) {
			// Something is wrong.
			throw new RuntimeException(e);
		}
	}
	
	public static int getPort() {
		return PORT;
	}
}
