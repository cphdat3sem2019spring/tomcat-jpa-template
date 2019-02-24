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
       System.out.println("------> " + PU_NAME);
      return emf;
    }
    String puVal = System.getProperty("PU_NAME");
    //String puValEnv = System.getenv("PU_NAME");
    
    if (puVal != null) {
      PU_NAME = puVal;
    }
    if(PU_NAME.equals("pu-test-on-travis")){
      /*REF: https://stackoverflow.com/questions/7227814/jpa-using-alternative-persistence-xml */
      Properties pros = new Properties();
      pros.setProperty(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,"META-INF/persistence-for-travis.xml"); 
    }
    emf = Persistence.createEntityManagerFactory(PU_NAME, null); 
    System.out.println("------> " + PU_NAME);
    return emf;
  }  
  
  
//  public static String getPuName() {
//    String puVal = System.getProperty("PU_NAME");
//    String puValEnv = System.getenv("PU_NAME");
//    
//    if (puVal != null) {
//      PU_NAME = puVal;
//    }
//    System.out.println("------> " + PU_NAME);
//    return PU_NAME;
//  }  
}
