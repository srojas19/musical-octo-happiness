package co.edu.uniandes.csw.isis2503.security;


import co.edu.uniandes.csw.isis2503.security.jwt.api.JwtToken;
import co.edu.uniandes.csw.isis2503.security.jwt.api.VerifyToken;
import co.edu.uniandes.csw.isis2503.security.logic.dto.UserDTO;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.application.ApplicationList;
import com.stormpath.sdk.application.Applications;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.AuthenticationResult;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.resource.ResourceException;
import com.stormpath.sdk.tenant.Tenant;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.SimplePrincipalCollection;

public class SecurityAuthenticator implements Authenticator {
 
 
    public AuthenticationInfo authenticate(AuthenticationToken at) throws AuthenticationException {
        JwtToken authToken = (JwtToken) at;
        if (authToken.getToken() != null) {
            if (!authToken.getToken().equals("")) {
                //Descifrar token y establecer info de usuario
                UserDTO user = decodeUser(authToken.getToken());
                if (validarToken(user)) {
                    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
                    authenticationInfo.setPrincipals(new SimplePrincipalCollection(user, user.getUsername()));
                    return authenticationInfo;
                }
            }
        }
        throw new AccountException("Token invalido.");
    }
 
    public UserDTO decodeUser(String token) {
        UserDTO user = null;
        try {
            VerifyToken ver = new VerifyToken();
            user = ver.getDataUser(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return user;
    }
 
    public boolean validarToken(UserDTO user) {
 
        boolean result = false;
        String path = "C:\\Users\\Jj.alarcon10\\Documents\\apiKey.properties";//Colocar la Ubicacion de su archivo apiKey.properties
        ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
        Client client = Clients.builder().setApiKey(apiKey).build();
 
        try {
            AuthenticationRequest request = new UsernamePasswordRequest(user.getUsername(), user.getPassword());
            Tenant tenant = client.getCurrentTenant();
            // CAMBIAR POR EL NOMBRE DE SU APLICACIÓN, SI USO LAS CREDENCIALES DADAS POR DEFECTO DEJAR ESTA PARTE INTACTA.
            ApplicationList applications = tenant.getApplications(Applications.where(Applications.name().eqIgnoreCase("ISIS2503 - Group 1")));
            Application application = applications.iterator().next();
 
            AuthenticationResult resultAuth = application.authenticateAccount(request);
            result = true;
 
        }catch(ResourceException e){
            System.out.println("Error at Authenticating");
        }
        return result;
 
    }
 
}