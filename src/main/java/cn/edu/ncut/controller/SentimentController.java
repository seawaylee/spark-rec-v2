package cn.edu.ncut.controller;

import cn.edu.ncut.dao.sentiment.SentimenDao;
import cn.edu.ncut.dao.spider.BookCommentMapper;
import cn.edu.ncut.dao.spider.BookInfoMapper;
import cn.edu.ncut.dao.spider.SimpleBookInfoMapper;
import cn.edu.ncut.dto.sentiment.BayesResDTO;
import cn.edu.ncut.dto.sentiment.CategoryParamsDTO;
import cn.edu.ncut.dto.sentiment.VocabDTO;
import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.util.PageUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SeawayLee
 * @create 2018/4/2 15:01
 */
@Controller
@RequestMapping(value = "/sentiment")
public class SentimentController {

    @Autowired
    private BookCommentMapper bookCommentMapper;
    @Autowired
    private SimpleBookInfoMapper simpleBookInfoMapper;
    @Autowired
    private SentimenDao sentimenDao;

    /**
     * 获取语料数据源
     */
    @RequestMapping(value = "/getComments")
    public String getBookCommentList(CategoryParamsDTO categoryParamsDTO, ModelMap modelMap) {
        Boolean hasRating = categoryParamsDTO.getHasRating() == null ? true : categoryParamsDTO.getHasRating();
        Integer pageNum = categoryParamsDTO.getPageNum() == null ? 1 : categoryParamsDTO.getPageNum();
        List<BookComment> bookCommentList;
        Integer commentCount = 0;
        if (hasRating) {
            bookCommentList = bookCommentMapper.selectAllHasRatings(PageUtils.getFrom(pageNum), PageUtils.PAGE_SIZE);
            commentCount = bookCommentMapper.selectHasRatingCount();
        } else {
            bookCommentList = bookCommentMapper.selectAllNoRatings(PageUtils.getFrom(pageNum), PageUtils.PAGE_SIZE);
            commentCount = bookCommentMapper.selectNoRatingCount();
        }
        for (BookComment bookComment : bookCommentList) {
            bookComment.setBookname(simpleBookInfoMapper.getBookByNo(bookComment.getBookno()).getTitle());
            bookComment.setContent(bookComment.getContent().length() > 30 ? bookComment.getContent().substring(0, 30) + "..." : bookComment.getContent());
        }
        modelMap.put("bookCommentList", bookCommentList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", commentCount);
        modelMap.put("totalPage", PageUtils.getTotalPage(commentCount));
        modelMap.put("hasRating", hasRating);

        return "sentiment/category";
    }

    /**
     * 获取极性评论语料
     */
    @RequestMapping(value = "/getPrepareData")
    public String getPrepareData(@RequestParam(defaultValue = "积极") String dataType, @RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) {
        Integer rating = "积极".equals(dataType) ? 5 : 1;
        List<BookComment> bookCommentList = bookCommentMapper.selectByRatings(rating, PageUtils.getFrom(pageNum), PageUtils.PAGE_SIZE);
        Integer ratingCount = bookCommentMapper.selectRatingCount(rating);
        modelMap.put("bookCommentList", bookCommentList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", ratingCount);
        modelMap.put("totalPage", PageUtils.getTotalPage(ratingCount));
        modelMap.put("dataType", dataType);
        return "sentiment/prepareData";
    }

    /**
     * 获取情感词典数据
     */
    @RequestMapping(value = "/getVocabData")
    public String getVocabData(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) throws IOException {
        List<String> pagedVocabStrList = sentimenDao.getVocabStrList().subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        String[] vocabMap;
        List<VocabDTO> vocabList = new ArrayList<>();
        for (String vocab : pagedVocabStrList) {
            vocabMap = vocab.replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "").split(",");
            vocabList.add(new VocabDTO(vocabMap[0], Integer.valueOf(vocabMap[1])));
        }
        modelMap.put("vocabList", vocabList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", vocabList.size());
        modelMap.put("totalPage", PageUtils.getTotalPage(sentimenDao.getVocabStrList().size()));
        return "sentiment/vocabData";
    }

    @RequestMapping(value = "/getBayesRes")
    public String getBayesRes(@RequestParam(defaultValue = "1") Integer pageNum, ModelMap modelMap) throws IOException {
        List<String> pagedBayesResStrList = sentimenDao.getBayesResStrList().subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        List<String> pagedNoRatingCommentStrList = sentimenDao.getNoRatingCommentStrList().subList(PageUtils.getFrom(pageNum), PageUtils.getFrom(pageNum) + PageUtils.PAGE_SIZE);
        List<BayesResDTO> bayesResDTOList = new ArrayList<>();
        String[] vocabMap;
        for (int i = 0; i < pagedBayesResStrList.size(); i++) {
            String[] bayesResStr = pagedBayesResStrList.get(i).split(",");
            String[] noRatingStr = pagedNoRatingCommentStrList.get(i).split("##\\*##");
            BayesResDTO bayesResDTO = new BayesResDTO(bayesResStr[0], simpleBookInfoMapper.getBookByNo(bayesResStr[1]).getTitle(), noRatingStr[2], Integer.valueOf(bayesResStr[2]));
            bayesResDTO.setType(bayesResDTO.getRating() == 5 ? "积极" : "消极");
            if (bayesResDTO.getContent().contains("20")) {
                continue;
            }
            bayesResDTO.setContent(bayesResDTO.getContent().length() > 30 ? bayesResDTO.getContent().substring(0, 30) + "..." : bayesResDTO.getContent());
            bayesResDTOList.add(bayesResDTO);
        }
        modelMap.put("bayesResList", bayesResDTOList);
        modelMap.put("pageNum", Math.max(1, pageNum));
        modelMap.put("totalRecord", sentimenDao.getBayesResStrList().size());
        modelMap.put("totalPage", PageUtils.getTotalPage(sentimenDao.getBayesResStrList().size()));
        return "sentiment/bayesResData";
    }
}
