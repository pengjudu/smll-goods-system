package cn.com.goods.mapper;

import cn.com.goods.model.TbCsManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbCsManagerMapper {
    int insert(TbCsManager record);

    int insertSelective(TbCsManager record);

    List<TbCsManager> queryManager(Map<String ,Object> paramMap);
}