package cn.edu.ncut.dto.cf;

/**
 * @author SeawayLee
 * @create 2018/4/9 18:40
 */
public class UserRatingDTO {
    private Integer userId;
    private Integer bookId;
    private Integer rating;

    public UserRatingDTO(Integer userId, Integer bookId, Integer rating) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
