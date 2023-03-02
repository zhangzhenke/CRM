package com.blink.crm.workbench.service.impl;

import com.blink.crm.workbench.domain.Clue;
import com.blink.crm.workbench.mapper.ClueMapper;
import com.blink.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clueService")
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    //保存创建的线索
    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }
}
