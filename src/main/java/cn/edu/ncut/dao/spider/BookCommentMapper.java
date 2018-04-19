package cn.edu.ncut.dao.spider;

import cn.edu.ncut.dto.spider.BookComment;
import cn.edu.ncut.dto.spider.extend.NameValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public interface BookCommentMapper extends Mapper<BookComment> {
    List<NameValue> getCommentNumByUserNoStatistic(Integer selectLimitAmount);

    List<BookComment> getRatingStatistic();

    List<BookComment> getBookRatingTimes(Integer selectLimitAmount);

    List<BookComment> getCommentTimeStatistic();

    List<BookComment> getCommentByBookNo(String bookno);

    List<BookComment> selectAllHasRatings(@Param("from") int from, @Param("to") int to);

    List<BookComment> selectAllNoRatings(@Param("from") int from, @Param("to") int to);

    Integer selectHasRatingCount();

    Integer selectNoRatingCount();

    Integer selectRatingCount(@Param("rating") int rating);

    List<BookComment> selectByRatings(@Param("rating") int rating, @Param("from") int from, @Param("to") int to);

    List<String> findAllCommentersNo();

}