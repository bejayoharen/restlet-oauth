package auth;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * 
 */
public class OAuthResource extends ServerResource {
	@Override  
	protected void doInit() throws ResourceException {  
	}
	@Get("html")
	public String represent() {
		org.restlet.security.User u = getClientInfo().getUser();
		
		return "<html><body>Protected (OAuth): " + u + " : " + u.getFirstName() + " " + u.getLastName() + " : " + u.getIdentifier() + "</body></html>";
	}
}
