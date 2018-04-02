package cn.edu.ncut;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = TestBase.class)
@ComponentScan(basePackages = {"cn.edu.ncut"})
public class TestBase {
    @Test
    public void testApp() {
        assert(true);
    }
}
