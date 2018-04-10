package cn.edu.ncut.service.cf;

import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.SimpleBookInfo;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SeawayLee
 * @create 2018/4/10 16:13
 */
@Service
public class CfService {
    @Autowired
    private BookCommentMapper bookCommentMapper;

    public List<Object> buildCategories(List<SimpleBookInfo> bookInfos) {
        List<Object> categories = new ArrayList<>();
        for (SimpleBookInfo bookInfo : bookInfos) {
            categories.add(new HashMap<String, Object>() {{
                put("name", bookInfo.getTitle());
                put("base", bookInfo.getTitle());
                put("itemStyle", new HashMap<String, Object>() {{
                    put("normal", new HashMap<String, Object>() {{
                        put("brushType", "both");
                        put("strokeColor", "#5182ab");
                        put("lineWidth", 1);
                    }});
                }});
            }});
        }

        return categories;
    }

    public List<Map> buildNodes(List<SimpleBookInfo> bookInfos, Map<Object, List<Object>> nodeMap) {
        List<Map> nodes = new ArrayList<>();
        List<BookComment> bookCommentList;
        BookComment bookComment;
        for (int i = 0; i < bookInfos.size(); i++) {
            bookCommentList = bookCommentMapper.getCommentByBookNo(bookInfos.get(i).getBookno());
            for (int j = 0; j < bookCommentList.size(); j++) {
                int finalI = i;
                bookComment = bookCommentList.get(j);
                if (nodeMap.get(bookComment.getUserno()) == null) {
                    nodeMap.put(bookComment.getUserno(), Lists.newArrayList(i));
                } else {
                    nodeMap.get(bookComment.getUserno()).add(j);
                }
                BookComment finalBookComment = bookComment;
                nodes.add(new HashMap<String, Object>() {{
                    put("name", finalBookComment.getUserno());
                    put("value", 1);
                    put("category", finalI);
                }});
            }
        }
        for (int i = 0; i < bookInfos.size(); i++) {
            int finalI = i;
            nodes.add(i, new HashMap<String, Object>() {{
                put("name", bookInfos.get(finalI).getTitle());
                put("value", 5);
                put("category", finalI);
            }});
        }
        return nodes;
    }

    public List<Object> buildLinks(List<Map> nodes, Map<Object, List<Object>> nodeMap) {
        List<Object> links = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            int finalI = i;
            links.add(new HashMap<String, Object>() {{
                put("source", nodes.get(finalI).get("category"));
                put("target", finalI);
            }});
        }
        for (Map.Entry<Object, List<Object>> entry : nodeMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                for (int i = 0; i < entry.getValue().size() - 1; i++) {
                    int finalI = i;
                    int finalI1 = i;
                    links.add(new HashMap<String, Object>() {{
                        put("source", entry.getValue().get(finalI1));
                        put("target", entry.getValue().get(finalI1 + 1));
                    }});
                }
            }
        }
        return links;
    }
}
