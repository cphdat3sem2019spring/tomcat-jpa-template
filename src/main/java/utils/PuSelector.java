package utils;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;

public class PuSelector {
   private static String PU_NAME = "pu";
   private static EntityManagerFactory emf;

  public static EntityManagerFactory getEntityManagerFactory() {
    if(emf != null){
       System.out.println("--- Created EntityManagerFactory --> " + PU_NAME);
      return emf;
    }
    String puVal = System.getProperty("PU_NAME");
    System.out.println(">>>>>>>>>>>>>>>>>>>> "+puVal);
    if (puVal != null) {
      PU_NAME = puVal;
    }
    Properties props = null;
    if(PU_NAME.equals("pu-test-on-travis")){
      /*REF: https://stackoverflow.com/questions/7227814/jpa-using-alternative-persistence-xml */
      props = new Properties();
      props.setProperty(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,"META-INF/persistence-for-travis.xml");  
    }
    emf = Persistence.createEntityManagerFactory(PU_NAME, props); 
    System.out.println("--- Created EntityManagerFactory --> " + PU_NAME);
    return emf;
  }  
}
