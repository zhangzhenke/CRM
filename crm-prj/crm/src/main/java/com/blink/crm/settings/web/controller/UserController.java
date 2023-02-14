package com.blink.crm.settings.web.controller;

import com.blink.crm.commons.constants.Constants;
import com.blink.crm.commons.domain.ReturnObject;
import com.blink.crm.commons.utils.DateUtils;
import com.blink.crm.settings.domain.User;
import com.blink.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * url要和controller方法处理完请求之后，响应信息返回的页面的资源目录保持一致
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        //请求转发到登录页面
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service层方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);

        //根据查询结果，生成响应信息
        ReturnObject returnObject=new ReturnObject();
        if(user==null){
            //登录失败,用户名或者密码错误
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或者密码错误");
        }else{//进一步判断账号是否合法
            //user.getExpireTime()   //2019-10-20
            //        new Date()     //2020-09-10
            /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowStr=sdf.format(new Date());*/
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //登录失败，账号已过期
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            }else if("0".equals(user.getLockState())){
                //登录失败，状态被锁定
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip受限
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else{
                //登录成功
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);

                //把user保存到session中
                session.setAttribute(Constants.SESSION_USER,user);
            }
        }

        return returnObject;

    }
}
