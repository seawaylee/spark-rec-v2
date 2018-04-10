package cn.edu.ncut;

import cn.edu.ncut.dao.sentiment.SentimenDao;
import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.extend.BarInfoData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/9 21:05
 */
public class TestCF extends TestBase {
    @Autowired
    private BookCommentMapper bookCommentMapper;
    @Autowired
    private SentimenDao sentimenDao;
    @Test
    public void test() throws IOException {
        String s = FileUtils.readFileToString(new File("/Users/lixiwei-mac/Documents/IdeaProjects/spark-rec-v2/src/main/resources/json/relation.json"));
        System.out.println(s);
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println("jsonObject:" + jsonObject.toString());
    }

    @Test
    public void testStatistic() {
        List<BookComment> bookRatingTimes = bookCommentMapper.getBookRatingTimes(10);
        for (BookComment bookRatingTime : bookRatingTimes) {
            String bookno = bookRatingTime.getBookno();
            List<BookComment> commentByBookNo = bookCommentMapper.getCommentByBookNo(bookno);
            for (BookComment bookComment : commentByBookNo) {
                System.out.println(bookComment.getUserno());
            }
            System.out.println(bookRatingTime);
        }
    }

    @Test
    public void testMerge() {
        List<BookComment> result = bookCommentMapper.getRatingStatistic();
        List<String> bayesResStrList = sentimenDao.getBayesResStrList();
        String[] xAxis = new String[result.size() + bayesResStrList.size()];
        String[] yAxis = new String[result.size() + bayesResStrList.size()];
        for (int i = 0; i < result.size(); i++) {
            xAxis[i] = result.get(i).getRating().toString();
            yAxis[i] = result.get(i).getItemResultAmount().toString();
        }
        for (int i = 0; i < bayesResStrList.size(); i++) {
            for (int xIndex = 0; xIndex < xAxis.length; xIndex++) {
                if (xAxis[xIndex].equalsIgnoreCase(bayesResStrList.get(i).split(",")[2])) {
                    yAxis[xIndex] = Integer.valueOf(yAxis[xIndex]) + 1 + "";
                }
            }
        }
        BarInfoData commentData = new BarInfoData(yAxis, xAxis);
        commentData.setxAxis(xAxis);
        commentData.setyAxis(yAxis);
    }
}
