package com.blink.crm.workbench.web.controller;

import com.blink.crm.commons.constants.Constants;
import com.blink.crm.commons.domain.ReturnObject;
import com.blink.crm.commons.utils.DateUtils;
import com.blink.crm.commons.utils.UUIDUtils;
import com.blink.crm.settings.domain.DicValue;
import com.blink.crm.settings.domain.User;
import com.blink.crm.settings.service.DicValueService;
import com.blink.crm.settings.service.UserService;
import com.blink.crm.workbench.domain.Activity;
import com.blink.crm.workbench.domain.Clue;
import com.blink.crm.workbench.domain.ClueActivityRelation;
import com.blink.crm.workbench.domain.ClueRemark;
import com.blink.crm.workbench.service.ActivityService;
import com.blink.crm.workbench.service.ClueActivityRelationService;
import com.blink.crm.workbench.service.ClueRemarkService;
import com.blink.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

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

    //保存创建的线索
    @RequestMapping("/workbench/clue/saveCreateClue.do")
    public @ResponseBody
    Object saveCreateClue(Clue clue, HttpSession session){
        User user=(User)session.getAttribute(Constants.SESSION_USER);

        //封装参数
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.formatDateTime(new Date()));
        clue.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存创建的线索
            int ret = clueService.saveCreateClue(clue);

            if(ret>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }

    //跳转线索详细页面
    @RequestMapping("/workbench/clue/detailClue.do")
    public String detailClue(String id,HttpServletRequest request){
        //调用service层方法，查询数据
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> remarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList = activityService.queryActivityForDetailByClueId(id);
        //把数据保存到request中
        request.setAttribute("clue",clue);
        request.setAttribute("remarkList",remarkList);
        request.setAttribute("activityList",activityList);
        //请求转发
        return "workbench/clue/detail";
    }

    //根据name模糊查询市场活动，并且把已经跟clueId关联过的市场活动排除
    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueId.do")
    public @ResponseBody Object queryActivityForDetailByNameClueId(String activityName,String clueId){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service层方法，查询市场活动
        List<Activity> activityList=activityService.queryActivityForDetailByNameClueId(map);
        //根据查询结果，返回响应信息
        return activityList;
    }

    //保存关联的市场活动
    @RequestMapping("/workbench/clue/saveBund.do")
    public @ResponseBody Object saveBund(String[] activityId,String clueId){
        //封装参数
        ClueActivityRelation car = null;
        List<ClueActivityRelation> relationList=new ArrayList<>();
        for(String ai:activityId){
            car=new ClueActivityRelation();
            car.setActivityId(ai);
            car.setClueId(clueId);
            car.setId(UUIDUtils.getUUID());
            relationList.add(car);
        }

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service方法，批量保存线索和市场活动的关联关系
            int ret = clueActivityRelationService.saveCreateClueActivityRelationByList(relationList);

            if(ret>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                //更新市场活动
                List<Activity> activityList=activityService.queryActivityForDetailByIds(activityId);
                returnObject.setRetData(activityList);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;
    }

}
