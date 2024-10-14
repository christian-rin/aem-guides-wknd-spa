package com.adobe.aem.guides.wknd.spa.react.core.servlets;
import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.SlingHttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
        service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/facebook-login",
                "sling.servlet.methods=POST"
        }
)
public class FacebookLoginServlet extends SlingAllMethodsServlet {
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("accessToken");
        // Process the token: validate, store, or interact with Facebook's Graph API
    }
}