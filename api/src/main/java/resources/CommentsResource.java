package resources;

import config.IntegrationProperties;
import entities.CommentEntity;
import org.eclipse.microprofile.metrics.annotation.Counted;
import services.CommentBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentsResource {
    private Logger log = Logger.getLogger(CommentsResource.class.getName());

    @Inject
    private CommentBean commentsBean;

    @Inject
    private IntegrationProperties integrationProperties;

    @GET
    public Response getCommentsList() {
        final List<entities.CommentEntity> commentsList = commentsBean.getCommentsList();

        return Response.ok(commentsList).build();
    }

    @GET
    @Path("/{commentId}")
    public Response getComment(@PathParam("commentId") Integer commentId) {

        CommentEntity comment = commentsBean.getComment(commentId);

        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(comment).build();
    }

    @Counted(name = "CommentsCount")
    @GET
    @Path("count")
    public Response getCommentsCount(@QueryParam("imageId") Integer imageId) {

        List<entities.CommentEntity> comments = commentsBean.getCommentsList();

        comments.stream().filter(comment -> comment.getImageId().equals(imageId)).collect(Collectors.toList());

        if(integrationProperties.isMakeError()) {
            return Response.ok( "neveljavna vrednost").build();
        } else {
            return Response.ok(comments.size()).build();
        }
    }

}