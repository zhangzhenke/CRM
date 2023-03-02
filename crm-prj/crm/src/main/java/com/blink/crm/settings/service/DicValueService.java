package com.blink.crm.settings.service;

import com.blink.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueService {
    //根据typeCode查询数据字典值
    List<DicValue> queryDicValueByTypeCode(String typeCode);
}
