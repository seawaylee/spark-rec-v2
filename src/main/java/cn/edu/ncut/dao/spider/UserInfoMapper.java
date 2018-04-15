package cn.edu.ncut.dao.spider;

import cn.edu.ncut.dto.spider.UserInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserInfoMapper extends Mapper<UserInfo>
{
    List<UserInfo> selectByUserno(String userno);
    List<String> selectAllUserNo();
}