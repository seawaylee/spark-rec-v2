package cn.edu.ncut.dao.spider;

import cn.edu.ncut.dto.spider.SimpleBookInfo;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service
public interface SimpleBookInfoMapper extends Mapper<SimpleBookInfo>
{
	List<SimpleBookInfo> getBookNumByTagStatic(Integer selectLimitAmount);
	List<SimpleBookInfo> getBookRatingSort(Integer selectLimitAmount);
	List<SimpleBookInfo> selectByName(String title);
	List<SimpleBookInfo> selectAllNames();
	List<SimpleBookInfo> selectAllByUrl(List<String> urls);
	List<SimpleBookInfo> selectByUrl(String url);
	List<SimpleBookInfo> selectAllUrls();
	SimpleBookInfo getBookByNo(String bookno);
	int update(SimpleBookInfo simpleBookInfo);
}