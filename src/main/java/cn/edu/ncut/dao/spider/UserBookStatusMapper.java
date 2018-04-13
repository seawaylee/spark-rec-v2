package cn.edu.ncut.dao.spider;

import cn.edu.ncut.dto.spider.UserBookStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public interface UserBookStatusMapper extends Mapper<UserBookStatus> {
    List<UserBookStatus> selectByType(@Param("table") String table, @Param("from") int from, @Param("to") int to);
    Integer selectStatusCount(@Param("table") String table);
}