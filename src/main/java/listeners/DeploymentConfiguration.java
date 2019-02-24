package listeners;

import entity.Car;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DeploymentConfiguration implements ServletContextListener {

  private static String PU_NAME = "pu";
  private static EntityManagerFactory emf;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    String puVal = System.getProperty("PU_NAME");
    if (puVal != null) {
      PU_NAME = puVal;
    }
    Persistence.generateSchema(PU_NAME, null);
    emf = Persistence.createEntityManagerFactory(PU_NAME);
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    System.out.println("------> " + PU_NAME);
    return emf;
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
