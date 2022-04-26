package at.htl.leoquest.boundary;

import at.htl.leoquest.control.TransactionRepository;
import at.htl.leoquest.entities.Transaction;
import at.htl.leoquest.control.TransactionRepository;
import at.htl.leoquest.entities.Transaction;
import at.htl.leoquest.entities.Survey;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("leosurvey")
public class S_TransactionEndpoint {
    @Inject
    TransactionRepository transactionRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/transactioncode")
    public Response findAllCodes(){
        final List<Transaction> codes = transactionRepository.listAll();
        return Response.ok(codes).build();
    }
}
