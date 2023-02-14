package com.blink.crm.settings.service;

import com.blink.crm.settings.domain.User;

import java.util.Map;

/**
 * @author shkstart
 * @date 2023/2/12 - 20:25
 */
public interface UserService {

    User queryUserByLoginActAndPwd(Map<String,Object> map);
}
