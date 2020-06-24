package br.mack.resources;

import br.mack.entidade.HeartRate;
import br.mack.persistencia.HeartRateDAO;
import br.mack.persistencia.HeartRateDAOMySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("hearts")
@Produces(MediaType.APPLICATION_JSON)
public class HeartRateResource {
    HeartRateDAOMySQL dao;

    public HeartRateResource(HeartRateDAOMySQL dao){
        this.dao = dao;
    }

    /*@GET
    public Response getBpm() {
        List<HeartRate> allProducts = HeartRateDAOMySQL.getBpm();
        return Response.ok(allProducts).build();
    }*/

    @POST
    public void createHeart(HeartRate heartRate) {
        if (heartRate == null) {
            throw new BadRequestException("product data missing");
        }
        dao.create(heartRate);

    }

    @GET
    public void getHeart() {
        dao.read();
    }

    @DELETE
    @Path("/{id}")
    public void deleteHeart(@PathParam("id") int id){
        dao.delete(id);
    }

    @PUT
    public void updateHeart(HeartRate heartRate){
        if (heartRate == null) {
            throw new BadRequestException("product data missing");
        }
        dao.update(heartRate);

    }
}

