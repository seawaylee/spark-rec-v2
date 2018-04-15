package cn.edu.ncut.service.similar;

import org.assertj.core.util.Sets;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author SeawayLee
 * @create 2018/4/15 16:44
 */
@Service
public class SimilarService {
    public static final String PREPARE_USER_KEY = "PREPARE_USER";
    public static final String USER_BOOK_STATUS_KEY = "USER_BOOK_STATUS_";
    public static final String USER_MAX_SIMILAR_KEY = "USER_MAX_SIMILAR_";
    public static final float k1Collect = 0.8F;
    public static final float k2Wish = 0.1F;
    public static final float k3Do = 0.1F;
    public static final String DO_KEY = "tb_userbookdo";
    public static final String WISH_KEY = "tb_userbookwish";
    public static final String COLLECT_KEY = "tb_userbookcollect";
    public static final String USER_MAX_VALUE_SPLITER = " - ";

    public float calcSimilar(String prefereType, Map<String, Set<String>> userA, Map<String, Set<String>> userB) {
        Set<String> unionRes = new HashSet<>();
        final Set<String> collectListA = Sets.newHashSet(userA.get(prefereType));
        final Set<String> collectListB = Sets.newHashSet(userB.get(prefereType));
        unionRes.addAll(collectListA);
        unionRes.addAll(collectListB);
        int unionSize = unionRes.size();
        collectListA.retainAll(collectListB);
        int mergeSize = collectListA.size();
        //System.out.println(mergeSize + "/" + unionSize);
        return (mergeSize * 1.0F) / (unionSize * 1.0F);
    }
}
