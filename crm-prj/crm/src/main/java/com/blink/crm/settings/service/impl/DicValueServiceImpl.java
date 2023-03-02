package com.blink.crm.settings.service.impl;

import com.blink.crm.settings.domain.DicValue;
import com.blink.crm.settings.mapper.DicValueMapper;
import com.blink.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;

    //根据typeCode查询数据字典值
    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }
}
