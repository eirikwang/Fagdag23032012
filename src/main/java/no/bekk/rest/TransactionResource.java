package no.bekk.rest;

import no.bekk.pojo.Transaction;
import no.bekk.service.Broker;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sun.jersey.api.NotFoundException;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 * @since 1.0
 */
@Path("/transaction")
public class TransactionResource {
    private Broker broker = Broker.getInstance();

    @GET
    @Produces("application/json")
    public List<Transaction> find(@QueryParam("status") Transaction.Status status){
        return broker.findAll(status);
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Transaction getTransaction(@PathParam("id") Long id) {
        Transaction transaction = broker.findTransaction(id);
        if(transaction == null){
            throw new NotFoundException("Transaction not found");
        }
        return transaction;
    }
}
