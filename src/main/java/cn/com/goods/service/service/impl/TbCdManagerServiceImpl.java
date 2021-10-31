package cn.com.goods.service.service.impl;

import cn.com.goods.mapper.TbCsManagerMapper;
import cn.com.goods.model.TbCsManager;
import cn.com.goods.service.TbCdManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "tbCdManagerService")
public class TbCdManagerServiceImpl implements TbCdManagerService {
    @Autowired
    private TbCsManagerMapper tbCsManagerMapper;
    @Override
    public List<TbCsManager> queryManager(Map<String, Object> paramMap) {
        return tbCsManagerMapper.queryManager(paramMap);
    }
}
