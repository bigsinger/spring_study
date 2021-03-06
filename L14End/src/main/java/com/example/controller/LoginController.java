package com.example.controller;

import com.example.dao.model.User;
import com.example.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * Created by tengj on 2017/4/10.
 */
@Controller
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("ctx", getContextPath() + "/");
        return "login";
    }

    @RequestMapping(value = "/login_post", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login_post(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("ctx", request.getContextPath());
        Map<String, Object> map = new HashMap<String, Object>();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        userName = "admin"; password = "admin";
        if (!userName.equals("")  && password != "") {
            User user = new User(userName, password);
            request.getSession().setAttribute("user", user);
            map.put("result", "1");
        } else {
            map.put("result", "0");
        }
        return map;
    }
}
