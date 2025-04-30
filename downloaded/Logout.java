package webapp.servlet.Auth;

import webapp.core.Config;
import webapp.core.ServletAbstract;
import webapp.service.AuthService;
import webapp.service.FlashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxime on 11/25/2014.
 */
public class Logout extends ServletAbstract {

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

        AuthService.logOut(request);

        FlashService.addMessage(FlashService.FlashLevel.SUCCESS, Config.MESSAGE_LOGOUT);
        
        response.sendRedirect(Config.ROUTE_SIGNIN);
    }
}
