package auth;

import java.util.ArrayList;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.ext.oauth.OAuthAuthorizer;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;
import org.restlet.security.Role;

public class ProtectedApplication extends org.restlet.Application {
    @Override
    public Restlet createInboundRoot() {
        Context ctx = getContext();
        Router router = new Router(ctx);

        OAuthAuthorizer auth = new OAuthAuthorizer( "http://localhost:8080/oauth/validate" );
        //ArrayList <Role> roles = new ArrayList <Role>();
        //roles.add(new Role("AnOAuthScope", null));
        //auth.setAuthorizedRoles(roles);
        auth.setNext(ProtectedResource.class);
        router.attach("/data", auth);

        return router;
    }
    public static class ProtectedResource extends ServerResource {
    	@Override  
    	protected void doInit() throws ResourceException {  
    	}
    	@Get("html")
    	public String represent() {
    		org.restlet.security.User u = getClientInfo().getUser();
    		
    		if( u == null )
    			return "<html><body>Protected (OAuth): " + u + "</body></html>";
    		else
    			return "<html><body>Protected (OAuth): " + u + " : " + u.getFirstName() + " " + u.getLastName() + " : " + u.getIdentifier() + "</body></html>";
    	}
    }
}
