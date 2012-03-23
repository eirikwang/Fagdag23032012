package no.bekk.rest;

import no.bekk.pojo.Parser;
import no.bekk.pojo.Transaction;
import no.bekk.service.Broker;

import java.net.URI;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
@Path("/parser")
public class ParserResource {
    private Broker broker = Broker.getInstance();

    @Path("/{id}")
    @Produces("application/json")
    public Parser getParser(@PathParam("id") Long id){
        return broker.findParser(id);
    }

    @GET
    @Produces("application/json")
    public List<Parser> getParsers(){
        return broker.findParsers();
    }

    @POST
    public Response addFileToParse() {
        Parser p = broker.createParser();
        return Response.created(UriBuilder.fromResource(ParserResource.class).path(ParserResource.class, "getParser").build(p.id)).build();
    }

    private URI buildPath(Transaction transaction) {
        return UriBuilder.fromPath("..")
                .path(TransactionResource.class)
                .path(TransactionResource.class, "getTransaction")
                .build(transaction.id);
    }
}
