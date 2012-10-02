package auth;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * 
 */
public class BasicResource extends ServerResource {
	@Override  
	protected void doInit() throws ResourceException {  
	}
	@Get("form:html")
	public String get(Form form) {
		org.restlet.security.User u = getClientInfo().getUser();
		
		return "<html><body>Protected (Basic): " + u + " : " + u.getFirstName() + " " + u.getLastName() + " : " + u.getIdentifier() + "</body></html>";
	}
}
