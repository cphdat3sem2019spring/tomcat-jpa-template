package listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class DeploymentConfiguration implements ServletContextListener {

  private static String PU_NAME = "pu";
  private static EntityManagerFactory emf;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    String puVal = System.getProperty("PU_NAME");
    String puValEnv = System.getenv("PU_NAME");
    System.out.println(">>>>>>> "+puVal +", >>>>>>>>>>>>> "+puValEnv);
     
    
    if (puVal != null) {
      PU_NAME = puVal;
    }
//    Persistence.generateSchema(PU_NAME, null);
//    emf = Persistence.createEntityManagerFactory(PU_NAME);
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    System.out.println("------> " + PU_NAME);
    return emf;
  }
  public static String getPuName() {
    System.out.println("------> " + PU_NAME);
    return PU_NAME;
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }
}
