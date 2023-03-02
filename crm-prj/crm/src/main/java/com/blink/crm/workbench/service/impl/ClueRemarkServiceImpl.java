package com.blink.crm.workbench.service.impl;

import com.blink.crm.workbench.domain.ClueRemark;
import com.blink.crm.workbench.mapper.ClueRemarkMapper;
import com.blink.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    //根据clueId查询该线索下所有的备注
    @Override
    public List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }
}
