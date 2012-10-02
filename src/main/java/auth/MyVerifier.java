package auth;

import org.restlet.security.SecretVerifier;

class MyVerifier extends SecretVerifier {
	public int verify(String identifier, char[] secret){
		if( !"guest".equals(identifier) )
			return RESULT_INVALID; //this could also return RESULT_UNKOWN
		if ( compare("1234".toCharArray(), secret) ) {
			return RESULT_VALID;
		} else {
			return RESULT_INVALID;
		}
	}
}
