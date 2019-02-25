/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dbfacades.DemoFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import dtos.Data;
import javax.persistence.EntityManagerFactory;

import utils.PuSelector;

/**
 * REST Web Service
 */
@Path("demo")
public class DemoResource {

  private static String responseToPing = "Pong";
  private static final EntityManagerFactory emf = PuSelector.getEntityManagerFactory();
  //private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(utils.PuSelector.getPuName());
 
   
  private static DemoFacade facade = new DemoFacade(emf);
  
  @Context
  private UriInfo context;

  /**
   * Creates a new instance of DemoResource
   */
  public DemoResource(){}

  @GET
  @Path("ping")
  @Produces(MediaType.APPLICATION_JSON)
  public String getJson() {
    System.out.println("PING");
    return "{\"msg\":\""+responseToPing+"\"}";
  }
  
  @GET
  @Path("carscount")
  @Produces(MediaType.APPLICATION_JSON)
  public String getCarsCount(){
    Long count = facade.countCars(); 
    return "{ \"carCount\" : "+count +"}";
  }
  

  @PUT
  @Path("ping")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response putJson(Data content) {
    System.out.println("MSG "+content);
    responseToPing = content.msg;
    content.anotherMsg = "This was set by the server";
    return Response.status(200).entity(content).build();
  }
}
