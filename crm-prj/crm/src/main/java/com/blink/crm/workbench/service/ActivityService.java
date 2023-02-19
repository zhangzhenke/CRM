package com.blink.crm.workbench.service;

import com.blink.crm.workbench.domain.Activity;

/**
 * @author shkstart
 * @date 2023/2/17 - 15:36
 */
public interface ActivityService {
    //创建新的市场活动
    int saveCreateActivity(Activity activity);
}
