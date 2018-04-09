package cn.edu.ncut.dao.sentiment;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/8 21:10
 */
@Repository
public class SentimenDao {
    public static String ROOT_DIR = "/Users/lixiwei-mac/Documents/DataSet/recommend/";
    public static String BOOK_NAME = ROOT_DIR + "BookName.txt";
    public static String PN_VOCAB = ROOT_DIR + "PNVocab.txt";
    public static String NB_RESULT = ROOT_DIR + "NBSResult.txt";
    public static String NO_RATING_COMMENT = ROOT_DIR + "NoRatingUserComment.txt";

    public List<String> vocabStrList;
    public List<String> bayesResStrList;
    public List<String> noRatingCommentStrList;

    @PostConstruct
    private void init() throws IOException {
        vocabStrList = FileUtils.readLines(new File(PN_VOCAB));
        bayesResStrList = FileUtils.readLines(new File(NB_RESULT));
        noRatingCommentStrList = FileUtils.readLines(new File(NO_RATING_COMMENT));
    }

    public List<String> getVocabStrList() {
        return vocabStrList;
    }

    public List<String> getBayesResStrList() {
        return bayesResStrList;
    }

    public List<String> getNoRatingCommentStrList() {
        return noRatingCommentStrList;
    }
}
