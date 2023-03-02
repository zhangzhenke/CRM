package com.blink.crm.workbench.service;

import com.blink.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkService {
    //根据clueId查询该线索下所有的备注
    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);
}
