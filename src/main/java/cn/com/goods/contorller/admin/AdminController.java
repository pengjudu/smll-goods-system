package cn.com.goods.contorller.admin;
import cn.com.goods.common.Constants;

import cn.com.goods.model.TbCsManager;
import cn.com.goods.service.TbCdManagerService;
import cn.com.goods.util.Message;
import cn.com.goods.util.jwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbCdManagerService tbCdManagerService;
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        System.out.println("fffffffffff");
        request.setAttribute("path", "index");
        return "admin/index";
    }

    @GetMapping("/login")
    public String first() {
        System.out.println("hello");
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("loginName") String loginName,
                              @RequestParam("password") String password,
                              @RequestParam("verifyCode") String verifyCode,
                              HttpSession session, HttpServletRequest request, HttpServletResponse response,ModelAndView mav) {
        System.out.println (loginName+password);
        Message message = new Message ();
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return"/admin/login";
        }
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return"/admin/login";

        }
        String kaptchaCode = session.getAttribute(Constants.MALL_VERIFY_CODE_KEY) + "";
        logger.info (kaptchaCode);
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.toLowerCase ().equals(kaptchaCode)) {
            logger.info (verifyCode);
            session.setAttribute("errorMsg", "验证码错误");
            return"/admin/login";

        }
        try{
            Map<String ,Object> paramMap = new HashMap<>();
            paramMap.put("userName", loginName);
            paramMap.put("password", password);
            List<TbCsManager> tbCsManagers = tbCdManagerService.queryManager(paramMap);
            TbCsManager tbCsManager = tbCsManagers.get(0);
            if (tbCsManagers.size()>0){
                String token = jwtUtil.generToken(loginName, null, verifyCode);
                response.setHeader("token", token);
                logger.info(token);
                request.getSession(false);
                session.setAttribute("loginUser",tbCsManager);
                return "redirect:/admin/index";

            }
            else{
                session.setAttribute("errorMsg", "用户名或者密码错误");
                return"/admin/login";


            }

        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("errorMsg", "系统异常");
            return"/admin/login";



        }
//        String token = jwtUtil.generToken("loginName", null, kaptchaCode);
//        response.setHeader("token", token);
//        logger.info (token);
//        message.setToken(token);
//        message.setBody("fff");
//        //request.getSession(false);
//        session.setAttribute("token", loginName);
        //return"/admin/login";

    }

    @GetMapping({"/logout"})
    public String logout(HttpServletRequest request){
        request.getSession ().removeAttribute ("errorMsg");
        return "admin/login";

    }
    @PostMapping({"/index"})
    public String index() {
        logger.info ("index");
        return "admin/index";
    }

}
