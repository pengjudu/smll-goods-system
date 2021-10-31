package cn.com.goods.mapper;

import cn.com.goods.model.TbCsUser;

public interface TbCsUserMapper {
    int insert(TbCsUser record);

    int insertSelective(TbCsUser record);
}