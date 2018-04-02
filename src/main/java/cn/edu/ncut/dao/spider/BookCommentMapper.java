package cn.edu.ncut.dao.spider;

import cn.edu.ncut.dto.spider.BookComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public interface BookCommentMapper extends Mapper<BookComment> {
    List<BookComment> getCommentNumByUserNoStatistic(Integer selectLimitAmount);

    List<BookComment> getRatingStatistic();

    List<BookComment> getCommentTimeStatistic();

    List<BookComment> selectAllHasRatings(int from);

    List<BookComment> selectAllNoRatings();

    List<BookComment> selectAllPNRatings(int from);

    List<BookComment> selectByRatings(@Param("rating") int rating, @Param("from") int from);

    List<String> findAllCommentersNo();

}