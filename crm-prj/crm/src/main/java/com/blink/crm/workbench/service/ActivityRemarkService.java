package com.blink.crm.workbench.service;


import com.blink.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {

    //根据activityId查询该市场活动下所有备注的明细信息
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);
    //保存创建的市场活动备注
    int saveCreateActivityRemark(ActivityRemark remark);
}
