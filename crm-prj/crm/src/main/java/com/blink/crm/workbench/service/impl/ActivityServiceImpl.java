package com.blink.crm.workbench.service.impl;

import com.blink.crm.workbench.domain.Activity;
import com.blink.crm.workbench.mapper.ActivityMapper;
import com.blink.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    //创建新的市场活动
    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }
}
