package cn.edu.ncut.util;

/**
 * @author SeawayLee
 * @create 2018/4/8 13:43
 */
public class PageUtils {
    public static Integer PAGE_SIZE = 10;

    public static Integer getTotalPage(int totalRecord) {
        return Integer.valueOf((int) Math.ceil(totalRecord / PAGE_SIZE) + "");
    }

    public static Integer getFrom(int pageNum) {
        return Math.max(0, (pageNum - 1) * PAGE_SIZE);
    }
}
