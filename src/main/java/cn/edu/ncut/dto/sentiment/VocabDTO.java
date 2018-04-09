package cn.edu.ncut.dto.sentiment;

/**
 * @author SeawayLee
 * @create 2018/4/8 19:28
 */
public class VocabDTO {
    private String vocab;
    private Integer count;

    public VocabDTO(String vocab, Integer count) {
        this.vocab = vocab;
        this.count = count;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

