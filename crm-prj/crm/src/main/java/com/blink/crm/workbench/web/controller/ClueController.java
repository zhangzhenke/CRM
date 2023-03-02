package com.blink.crm.workbench.web.controller;

import com.blink.crm.settings.domain.DicValue;
import com.blink.crm.settings.domain.User;
import com.blink.crm.settings.service.DicValueService;
import com.blink.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;


    //跳转线索主页面
    @RequestMapping("/workbench/clue/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<User> userList = userService.queryAllUsers();
        //称呼，写死了
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        //线索状态
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        //来源
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList",userList);
        request.setAttribute("appellationList",appellationList);
        request.setAttribute("clueStateList",clueStateList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/clue/index";
    }


}
