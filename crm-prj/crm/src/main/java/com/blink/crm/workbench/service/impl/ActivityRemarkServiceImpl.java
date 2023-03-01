package com.blink.crm.workbench.service.impl;

import com.blink.crm.workbench.domain.ActivityRemark;
import com.blink.crm.workbench.mapper.ActivityRemarkMapper;
import com.blink.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    //根据activityId查询该市场活动下所有备注的明细信息
    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }

    //保存创建的市场活动备注
    @Override
    public int saveCreateActivityRemark(ActivityRemark remark) {
        return activityRemarkMapper.insertActivityRemark(remark);
    }

    //根据id删除市场活动备注
    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemarkById(id);
    }

    //保存修改的市场活动备注
    @Override
    public int saveEditActivityRemark(ActivityRemark remark) {
        return activityRemarkMapper.updateActivityRemark(remark);
    }
}
