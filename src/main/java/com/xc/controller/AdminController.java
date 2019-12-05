package com.xc.controller;

import com.xc.JDBCUtil2.SecurityCode;
import com.xc.JDBCUtil2.SecurityImage;
import com.xc.entity.Admin;
import com.xc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lenovo on 2019/11/26.
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    @ResponseBody
    public String login(String code, HttpServletRequest request,Admin admin){
        String codeOld= (String) request.getSession().getAttribute("code");
        if(codeOld.equals(code)){
            try {
                adminService.login(admin);
                request.getSession().setAttribute("admin",admin);
                return null;
            } catch (Exception e) {
                String messgae=e.getMessage();
                return messgae;
            }
        }else {
            String message="验证码错误";
            return message;
        }

    }

    @RequestMapping("code")
    public String code(HttpServletRequest request, HttpServletResponse response) throws IOException {//验证码生成
        //通过工具类随机生成四位数
        String securityCode= SecurityCode.getSecurityCode();
        //存入sqlsession中
        HttpSession session=request.getSession();
        session.setAttribute("code", securityCode);
        //生成随机图片
        BufferedImage image= SecurityImage.createImage(securityCode);
        OutputStream out=response.getOutputStream();
        ImageIO.write(image, "png", out);
        return null;
    }
}
