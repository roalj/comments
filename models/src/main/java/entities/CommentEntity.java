package entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@NamedQueries(value =
        {
                @NamedQuery(name = "Comment.getAll", query = "SELECT comments FROM CommentEntity comments")
        })
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;

    @Column(name = "authorId")
    private String authorId;

    @Column(name = "imageId")
    private Integer imageId;

    @Column(name = "text")
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
