package com.blink.crm.workbench.service.impl;

import com.blink.crm.workbench.domain.Activity;
import com.blink.crm.workbench.mapper.ActivityMapper;
import com.blink.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    //创建新的市场活动
    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    //分页查询
    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    //根据条件查询市场活动的总条数
    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    //根据ids批量删除市场活动
    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    //根据id查询市场活动的信息
    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

}
