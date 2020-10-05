package rest;

import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DataManager;

public class LearningObjectiveResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        DataManager.setupTestData(em);
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        DataManager.tearDownTestData(em);
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given()
                .when()
                .get("/learning-objective")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDemoMessage() throws Exception {
        given()
                .contentType("application/json")
                .get("/learning-objective/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Test completed!"));
    }

    @Test
    public void testGetLearningObjectiveByIdMustGiveResult() {
        given()
                .contentType("application/json")
                .get("/learning-objective/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("course", notNullValue())
                .body("question", notNullValue());
    }

    @Test
    public void testGetLearningObjectiveByIdMustGiveNotFound() {
        given()
                .contentType("application/json")
                .get("/learning-objective/9000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND_404.getStatusCode());
    }

    @Test
    public void testGetLearningObjectiveByRandomMustGiveResult() {
        given()
                .contentType("application/json")
                .get("/learning-objective/random")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("course", notNullValue())
                .body("question", notNullValue());
    }

    @Test
    public void testGetAllLearningObjectivesMustGiveSizeOfFive() {
        given()
                .contentType("application/json")
                .get("/learning-objective/all")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("learningObjectiveDTOs", hasSize(5));
    }

    @Test
    public void testGetAllLearningObjectivesByCourseIdMustGiveNotNull() {
        given()
                .contentType("application/json")
                .get("/learning-objective/allByCourseId/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("learningObjectiveDTOs", notNullValue());
    }

    @Test
    public void testGetAllLearningObjectivesByCourseIdMustGiveSizeOfZero() {
        given()
                .contentType("application/json")
                .get("/learning-objective/allByCourseId/9000")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("learningObjectiveDTOs", hasSize(0));
    }

    @Test
    public void testGetAllLearningObjectivesByCourseShortNameMustGiveSizeOfTwo() {
        given()
                .contentType("application/json")
                .get("/learning-objective/allByCourseShortName/TST")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("learningObjectiveDTOs", hasSize(2));
    }

    @Test
    public void testGetAllLearningObjectivesByCourseShortNameMustGiveSizeOfZero() {
        given()
                .contentType("application/json")
                .get("/learning-objective/allByCourseShortName/RANDOM")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("learningObjectiveDTOs", hasSize(0));
    }
}
