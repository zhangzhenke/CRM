package com.blink.crm.workbench.service;

import com.blink.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

public interface ClueActivityRelationService {
    //批量保存创建的线索和市场活动的关联关系
    int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list);


}
