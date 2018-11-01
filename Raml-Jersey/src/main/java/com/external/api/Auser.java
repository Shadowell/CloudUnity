package com.external.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/auser")
public interface Auser {
  /**
   */
  @GET
  @Produces("application/json")
  GetAuserResponse getAuser();

  /**
   */
  @POST
  @Produces("application/json")
  PostAuserResponse postAuser();

  class GetAuserResponse extends ResponseDelegate {
    private GetAuserResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetAuserResponse(Response response) {
      super(response);
    }

    public static GetAuserResponse respond200WithApplicationJson(User entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetAuserResponse(responseBuilder.build(), entity);
    }

    public static GetAuserResponse respond500WithApplicationJson(ResponseEntity entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetAuserResponse(responseBuilder.build(), entity);
    }
  }

  class PostAuserResponse extends ResponseDelegate {
    private PostAuserResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostAuserResponse(Response response) {
      super(response);
    }

    public static PostAuserResponse respond200WithApplicationJson(ResponseEntity entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostAuserResponse(responseBuilder.build(), entity);
    }
  }
}
