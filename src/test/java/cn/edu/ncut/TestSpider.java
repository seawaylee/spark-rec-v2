package cn.edu.ncut;

import cn.edu.ncut.dto.spider.UserBookStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SeawayLee
 * @create 2018/4/2 16:29
 */
public class TestSpider extends TestBase{

    @Autowired
    UserBookStatus userBookStatus;
    @Test
    public void test() {
        System.out.println(userBookStatus);
    }
}
