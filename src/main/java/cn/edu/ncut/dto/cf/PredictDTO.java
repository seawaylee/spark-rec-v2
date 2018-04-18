package cn.edu.ncut.dto.cf;

/**
 * @author SeawayLee
 * @create 2018/4/18 13:52
 */
public class PredictDTO {
    private Integer id;
    private Integer bookId;
    private Float score;
    private String url;
    private String title;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
