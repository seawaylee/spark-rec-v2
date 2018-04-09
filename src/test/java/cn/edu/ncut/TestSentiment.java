package cn.edu.ncut;

import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dto.spider.BookComment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/8 11:11
 */
public class TestSentiment extends TestBase {

    @Autowired
    private BookCommentMapper bookCommentMapper;

    @Test
    public void testSelect() {
        List<BookComment> bookComments = bookCommentMapper.selectAllNoRatings(0, 10);
        for (BookComment bookComment : bookComments) {
            System.out.println(bookComment);
        }
    }
}
