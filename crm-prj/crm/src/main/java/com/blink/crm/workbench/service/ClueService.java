package com.blink.crm.workbench.service;

import com.blink.crm.workbench.domain.Clue;


public interface ClueService {
    //保存创建的线索
    int saveCreateClue(Clue clue);
    //根据id查询线索的明细信息
    Clue queryClueForDetailById(String id);
}
