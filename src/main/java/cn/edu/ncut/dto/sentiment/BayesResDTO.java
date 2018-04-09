package cn.edu.ncut.dto.sentiment;

/**
 * @author SeawayLee
 * @create 2018/4/8 20:14
 */
public class BayesResDTO {
    private String userNo;
    private String bookName;
    private String content;
    private Integer rating;
    private String type;

    public BayesResDTO() {
    }

    public BayesResDTO(String userNo, String bookName, String content, Integer rating) {
        this.userNo = userNo;
        this.bookName = bookName;
        this.content = content;
        this.rating = rating;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
