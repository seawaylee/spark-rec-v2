package cn.edu.ncut.dao.spider;

import cn.edu.ncut.dto.spider.BookTag;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BookTagMapper extends Mapper<BookTag> {

    List<BookTag> selectByName(String tagName);

}