package cn.com.goods.contorller.admin;
import cn.com.goods.common.Constants;

import cn.com.goods.util.Message;
import cn.com.goods.util.jwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/login")
    public String login() {
        System.out.println("hello");
        return "admin/login";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session,HttpServletRequest request, HttpServletResponse response) {
        System.out.println (loginName+password);
        Message message = new Message ();
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute(Constants.MALL_VERIFY_CODE_KEY) + "";
        logger.info (kaptchaCode);
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.toLowerCase ().equals(kaptchaCode)) {
            logger.info (verifyCode);
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        String token = jwtUtil.generToken("loginName", null, kaptchaCode);
        response.setHeader("token", token);
        logger.info (token);
        message.setToken(token);
        message.setBody("fff");
        //request.getSession(false);
        session.setAttribute("user", loginName);
        return "forward:/home/index";
    }

    @GetMapping({"/logout"})
    public String logout(HttpServletRequest request){
        request.getSession ().removeAttribute ("errorMsg");
        return "admin/login";

    }

}
