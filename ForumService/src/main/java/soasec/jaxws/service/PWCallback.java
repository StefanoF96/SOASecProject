package soasec.jaxws.service;

import org.apache.axis2.context.MessageContext;
import org.apache.ws.security.WSPasswordCallback;

import soasec.jaxws.db.DbConnection;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.namespace.QName;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PWCallback implements CallbackHandler {
    public void handle(Callback[] callbacks)
            throws IOException, UnsupportedCallbackException {
                    for (int i = 0; i < callbacks.length; i++) {
                    if (callbacks[i] instanceof WSPasswordCallback) {
                    	
                            WSPasswordCallback pc=(WSPasswordCallback)callbacks[i];
                            System.out.println(pc.getIdentifier());
                            
                            
                            if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                            	System.out.println("user token");
	                            try {
		                            DbConnection db = new DbConnection();
		                            String query = "SELECT username, password FROM users";
		                			ResultSet result = db.executeQuery(query);
		                			if (!result.isBeforeFirst()) throw new Exception();
		                			while (result.next()) {
			                			if (pc.getIdentifier().equals(result.getString("username"))) {
			                				//System.out.println("user = " + result.getString("username"));
			                				//System.out.println("password = " + result.getString("password"));
			                				pc.setPassword(result.getString("password"));
			                				break;
			                			}	
		                			}
	                            } catch(Exception e) {
	                            	System.err.println("error in Service PWCallback DB comunication");
	                            	e.printStackTrace();
	                            }  
                            }
                            
                            else if (pc.getIdentifier().equals("service")){
                            		System.out.println("service outflow callaback");
                            		pc.setPassword("service_password");
                            } else{
                                    throw new UnsupportedCallbackException(callbacks[i], "Unknown user");
                            }
                    } else {
                            throw new UnsupportedCallbackException(callbacks[i],"Unrecognized Callback");
                    }
            }
    }
}