package services;


import com.kumuluz.ee.discovery.annotations.DiscoverService;
import entities.CommentEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentBean {
    private Logger log = Logger.getLogger(CommentBean.class.getName());

    @PersistenceContext(unitName = "images-jpa")
    private EntityManager em;

    private Client httpClient;

    public List getCommentsList(){
        Query query = em.createNamedQuery("Comment.getAll", CommentEntity.class);
        return query.getResultList();
    }

    public CommentEntity getComment(Integer commentId) {
        CommentEntity commentEntity = em.find(CommentEntity.class, commentId);

        if (commentEntity == null) {
            throw  new NotFoundException();
        }

        return commentEntity;
    }
}
