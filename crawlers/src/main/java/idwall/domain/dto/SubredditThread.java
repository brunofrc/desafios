package idwall.domain.dto;

/**
 * Created by Bruno Fernandes.
 */
public class SubredditThread {

    private Integer upvotes;

    private String redditLink;

    private String title;

    private String commentsLink;


    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public String getRedditLink() {
        return redditLink;
    }

    public void setRedditLink(String redditLink) {
        this.redditLink = redditLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCommentsLink() {
        return commentsLink;
    }

    public void setCommentsLink(String commentsLink) {
        this.commentsLink = commentsLink;
    }

    @Override
    public String toString() {
        return  "Reddit:" + redditLink + "\n" +
                "Thread: " + title + "\n" +
                "Up votes: " + upvotes + "\n" +
                "Comments: " + commentsLink;
    }
}