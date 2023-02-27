package com.blink.crm.workbench.service;

import com.blink.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @date 2023/2/17 - 15:36
 */
public interface ActivityService {
    //创建新的市场活动
    int saveCreateActivity(Activity activity);
    //分页查询
    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);
    //根据条件查询市场活动的总条数
    int queryCountOfActivityByCondition(Map<String,Object> map);
    //根据ids批量删除市场活动
    int deleteActivityByIds(String[] ids);
    //根据id查询市场活动的信息
    Activity queryActivityById(String id);
    //保存修改的市场活动
    int saveEditActivity(Activity activity);
    //查询所有的市场活动
    List<Activity> queryAllActivitys();
}
