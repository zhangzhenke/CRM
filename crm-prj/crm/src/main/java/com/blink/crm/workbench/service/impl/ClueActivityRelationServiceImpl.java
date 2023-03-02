package com.blink.crm.workbench.service.impl;

import com.blink.crm.workbench.domain.ClueActivityRelation;
import com.blink.crm.workbench.mapper.ClueActivityRelationMapper;
import com.blink.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    //批量保存创建的线索和市场活动的关联关系
    @Override
    public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }


}
