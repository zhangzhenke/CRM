package com.blink.crm.workbench.web.controller;

import com.blink.crm.commons.constants.Constants;
import com.blink.crm.commons.domain.ReturnObject;
import com.blink.crm.commons.utils.DateUtils;
import com.blink.crm.commons.utils.UUIDUtils;
import com.blink.crm.settings.domain.User;
import com.blink.crm.settings.service.UserService;
import com.blink.crm.workbench.domain.Activity;
import com.blink.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    //显示市场活动页面
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询所有的用户
        List<User> userList=userService.queryAllUsers();
        //把数据保存到request中
        request.setAttribute("userList",userList);
        //请求转发到市场活动的主页面（我要携带数据）
        return "workbench/activity/index";
    }

    //创建新的市场活动
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    //自动生成json串
    public @ResponseBody
    Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        //写数据，需要判断是否出现异常，它改动数据库
        try {
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);

            if(ret>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }
        return returnObject;
    }

    //根据条件分页查询市场活动的列表
    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    //自动生成json串,第几页 每页几条
    public @ResponseBody
    Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate, int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        //第几页的数据
        map.put("beginNo",(pageNo-1)*pageSize);
        //每页显示条数
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        //根据查询结果结果，生成响应信息
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("activityList",activityList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

}