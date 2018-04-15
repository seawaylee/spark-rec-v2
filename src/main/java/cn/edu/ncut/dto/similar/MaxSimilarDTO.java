package cn.edu.ncut.dto.similar;

/**
 * @author SeawayLee
 * @create 2018/4/16 00:48
 */
public class MaxSimilarDTO {
    private String userA;
    private String userB;
    private String similar;

    public MaxSimilarDTO(String userA, String userB, String similar) {
        this.userA = userA;
        this.userB = userB;
        this.similar = similar;
    }

    public String getUserA() {
        return userA;
    }

    public void setUserA(String userA) {
        this.userA = userA;
    }

    public String getUserB() {
        return userB;
    }

    public void setUserB(String userB) {
        this.userB = userB;
    }

    public String getSimilar() {
        return similar;
    }

    public void setSimilar(String similar) {
        this.similar = similar;
    }
}
