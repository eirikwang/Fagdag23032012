package no.bekk.rest;

import no.bekk.pojo.Transaction;
import no.bekk.service.Broker;

import java.net.URI;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
@Path("/parser")
public class ParserResource {
    private Broker broker = Broker.getInstance();


    @POST
    public Response addFileToParse(@PathParam("id") Long id) {
        Transaction transaction = broker.addTransaction(Transaction.Type.Parse, null);
        return Response.created(buildPath(transaction)).build();
    }

    private URI buildPath(Transaction transaction) {
        return UriBuilder.fromPath("..")
                .path(TransactionResource.class)
                .path(TransactionResource.class, "getTransaction")
                .build(transaction.id);
    }
}
