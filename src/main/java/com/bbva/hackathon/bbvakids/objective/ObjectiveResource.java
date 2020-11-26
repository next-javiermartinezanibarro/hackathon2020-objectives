package com.bbva.hackathon.bbvakids.objective;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/objectives")
@Produces(APPLICATION_JSON)
public class ObjectiveResource {

    private static final Logger LOGGER = Logger.getLogger(ObjectiveResource.class);

    @Inject
    ObjectiveService objectiveService;

    @GET
    public Response getAllObjectives(@HeaderParam("profileId") Long profileId) {
        List<Objective> profiles = objectiveService.findAllObjectivesByProfileId(profileId);
        return Response.ok(profiles).build();
    }

    @GET
    @Path("/{id}")
    public Response getObjective(
            @Parameter(description = "Objective identifier", required = true)
            @PathParam("id") Long id) {
        Objective objective = objectiveService.findObjectiveById(id);
        if (objective != null) {
            LOGGER.debug("Found objective " + objective);
            return Response.ok(objective).build();
        } else {
            LOGGER.debug("No objective found with id " + id);
            return Response.noContent().build();
        }
    }

    @POST
    public Response createObjective(
            @RequestBody(required = true, content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = Objective.class)))
            @Valid Objective objective, @Context UriInfo uriInfo, @HeaderParam("profileId") Long profileId) {
        objective.profileId = profileId;
        objective = objectiveService.persistObjective(objective);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(objective.id));
        LOGGER.debug("New Objective created with URI " + builder.build().toString());
        return Response.created(builder.build()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProfile(
            @Parameter(description = "Objective identifier", required = true)
            @PathParam("id") Long id) {
        objectiveService.deleteObjective(id);
        LOGGER.debug("objective deleted with " + id);
        return Response.noContent().build();
    }
}