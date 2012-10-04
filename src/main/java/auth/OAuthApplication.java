package auth;

import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.ext.oauth.AccessTokenServerResource;
import org.restlet.ext.oauth.AuthPageServerResource;
import org.restlet.ext.oauth.ClientStore;
import org.restlet.ext.oauth.ClientStoreFactory;
import org.restlet.ext.oauth.HttpOAuthHelper;
import org.restlet.ext.oauth.ValidationServerResource;

public class OAuthApplication extends org.restlet.Application {
	@Override
	public synchronized Restlet createInboundRoot() {

		// Engine.setLogLevel(Level.FINE);
		Router root = new Router(getContext());

		// create a test client:
		ClientStore<?> clientStore = ClientStoreFactory.getInstance();
		clientStore.createClient("CLIENT_ID", "CLIENT_SECRET", "http://localhost:8080/protected");

		// Challenge Authenticator
		ChallengeAuthenticator au = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC, "OAuth Test Server");
		au.setVerifier(new MyVerifier());
		au.setNext(org.restlet.ext.oauth.AuthorizationServerResource.class);
		root.attach("/authorize", au);
		root.attach("/token", AccessTokenServerResource.class);
		root.attach("/validate",ValidationServerResource.class);
		root.attach(HttpOAuthHelper.getAuthPage(getContext()), AuthPageServerResource.class);
		//
		// //Set Template for AuthPage:
		// HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
		// //Dont ask for approval if previously approved
		// HttpOAuthHelper.setAuthSkipApproved(true, getContext());

		return root;
	}
}
