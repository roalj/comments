package resources;

import entities.CommentEntity;
import services.CommentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentsResource {

    @Inject
    private CommentBean commentsBean;

    @GET
    public Response getImageList() {
        final List<entities.CommentEntity> commentsList = commentsBean.getCommentsList();

        return Response.ok(commentsList).build();
    }

    @GET
    @Path("/{commentId}")
    public Response getImageMetadata(@PathParam("commentId") Integer commentId) {

        CommentEntity comment = commentsBean.getComment(commentId);

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(comment).build();
    }

}