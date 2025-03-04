package io.quarkiverse.langchain4j.sample.chatbot;
import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.oidc.IdToken;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

/**
 * Login resource which returns a movie muse page to the authenticated user
 */
@Path("/login")
@Authenticated
public class LoginResource {
    @Inject
    @IdToken
    JsonWebToken idToken;
    
    @Inject
    WebSocketTickets tickets;
    
    @Inject
    Template movieMuse;
    
    @GET
    @Produces("text/html")
    public TemplateInstance movieMuse() {
        return movieMuse.data("name", idToken.getName()).data("ticket", tickets.getTicket());
    }
}
