package cn.com.goods.service;

import cn.com.goods.model.TbCsManager;

import java.util.List;
import java.util.Map;

public interface TbCdManagerService {
    List<TbCsManager> queryManager(Map<String,Object> paramMap);
}
