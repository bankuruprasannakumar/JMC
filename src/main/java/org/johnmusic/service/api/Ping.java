package org.johnmusic.service.api;

import org.johnmusic.service.Constants;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bankuru on 12/2/17.
 */

@Path("ping")
@Produces(MediaType.APPLICATION_JSON)
public class Ping {
    @GET
    public Response getIt() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.SUCCESS, true);
        return Response.ok(jsonObject.toString(), MediaType.APPLICATION_JSON).build();
    }
}
