package cn.edu.ncut.dto.sentiment;

/**
 * @author SeawayLee
 * @create 2018/4/8 13:39
 */
public class CategoryParamsDTO {
    private Integer pageNum;
    private Boolean hasRating;


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Boolean getHasRating() {
        return hasRating;
    }

    public void setHasRating(Boolean hasRating) {
        this.hasRating = hasRating;
    }

    @Override
    public String toString() {
        return "CategoryParamsDTO{" +
                "pageNum=" + pageNum +
                ", hasRating=" + hasRating +
                '}';
    }
}
