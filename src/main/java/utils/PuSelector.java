package utils;




public class PuSelector {
   private static String PU_NAME = "pu";
 // private static EntityManagerFactory emf;

  public static String getPuName() {
    String puVal = System.getProperty("PU_NAME");
    String puValEnv = System.getenv("PU_NAME");
    
    if (puVal != null) {
      PU_NAME = puVal;
    }
    System.out.println("------> " + PU_NAME);
    return PU_NAME;
  }  
}
