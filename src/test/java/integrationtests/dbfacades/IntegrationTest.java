package integrationtests.dbfacades;

import entity.Car;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.hamcrest.Matchers.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import utils.PuSelector;


public class IntegrationTest {

  private static final int SERVER_PORT = 7777;
  private static final String APP_CONTEXT = "/restdemo";  //IMPORTANT--> this should reflect the value in META-INF/context.xml
  private static EntityManagerFactory emf;

  public IntegrationTest() throws NamingException {}

  @Test
  public void serverIsRunning() {
    given().when().get().then().statusCode(200);
  }

  @BeforeClass
  public static void setUpBeforeAll() {
    //emf = Persistence.createEntityManagerFactory("pu-test-with-mysql");
    emf = PuSelector.getEntityManagerFactory();
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = SERVER_PORT;
    RestAssured.basePath = APP_CONTEXT;
    RestAssured.defaultParser = Parser.JSON;
  }

  @AfterClass
  public static void setUpAfterAll() {
    emf.close();
  }

  /**
   * Setup test data in the database to a known state BEFORE Each test
   */
  @Before
  public void setUp() {

    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      //Delete all, since some future test cases might add/change data
      em.createQuery("delete from Car").executeUpdate();
      //Add our test data
      Car e1 = new Car("Volvo");
      Car e2 = new Car("WW");
      em.persist(e1);
      em.persist(e2);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  @Test
  public void testRestNoAuthenticationRequired() {
    given()
            .contentType("application/json")
            .when()
            .get("/api/demo/ping").then()
            .statusCode(200)
            .body("msg", equalTo("Pong"));
  }

}
