package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.LearningObjectiveInvalidInputException;
import exceptions.LearningObjectiveNotFoundException;
import utils.EMF_Creator;
import facades.Facade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/learning-objective")
public class LearningObjectiveResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final Facade FACADE = Facade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Test completed!\"}";
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLearningObjectiveById(@PathParam("id") int id) throws LearningObjectiveNotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getLearningObjectiveById(id))).build();
    }

    @Path("/random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLearningObjectiveByRandom() {
        return Response.ok().entity(GSON.toJson(FACADE.getLearningObjectiveByRandom())).build();
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllLearningObjectives() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllLearningObjectives())).build();
    }

    @Path("/allByCourseId/{courseId}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllLearningObjectivesByCourseId(@PathParam("courseId") int courseId) {
        return Response.ok().entity(GSON.toJson(FACADE.getAllLearningObjectivesByCourseId(courseId))).build();
    }

    @Path("/allByCourseShortName/{shortName}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllLearningObjectivesByCourseShortName(@PathParam("shortName") String shortName) throws LearningObjectiveInvalidInputException {
        return Response.ok().entity(GSON.toJson(FACADE.getAllLearningObjectivesByCourseShortName(shortName))).build();
    }
}
