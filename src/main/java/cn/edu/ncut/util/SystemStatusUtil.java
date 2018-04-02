package cn.edu.ncut.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SeawayLee
 * @create 2018/4/2 16:47
 */
public class SystemStatusUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");

    /**
     * 通过IP获取网络延迟
     */
    public static long getIpNetDelay(String remoteInetAddr) {
        boolean reachable = false;
        long begin = System.currentTimeMillis();
        try {
            InetAddress address = InetAddress.getByName(remoteInetAddr);
            reachable = address.isReachable(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!reachable) {
            return 9999999;
        }
        return System.currentTimeMillis() - begin;
    }

    /**
     * 通过网址获取网络延迟
     */
    public static long getHostNetDelay(String hostname) {
        URL url = null;
        Boolean bon = false;
        long begin = System.currentTimeMillis();
        try {
            url = new URL(hostname);
            InputStream in = url.openStream();
            in.close();
        } catch (IOException e) {
            return 9999999;
        }
        return System.currentTimeMillis() - begin;
    }

    public static Map<String, Object> getJvmStatus() {
        Map<String, Object> jvmMap = new HashMap<>();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryMXBean.getHeapMemoryUsage();
        jvmMap.put("initHeap", usage.getInit() / 1024 / 1024);
        jvmMap.put("maxHeap", usage.getMax() / 1024 / 1024);
        jvmMap.put("usedHeap", usage.getUsed() / 1024 / 1024);
        return jvmMap;
    }


    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }
}
