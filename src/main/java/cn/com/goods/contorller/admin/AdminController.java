package cn.com.goods.contorller.admin;
import cn.com.goods.common.Constants;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/common/mall/kaptcha")
    public void getCode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        // 调用工具类生成的验证码和验证码图片
        // Map<String, Object> codeMap = new HashMap<String, Object>();
        try {
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/png");

            // 三个参数分别为宽、高、位数
            SpecCaptcha captcha = new SpecCaptcha (100, 30, 4);

            // 设置类型 数字和字母混合
            captcha.setCharType(Captcha.TYPE_DEFAULT);

            //设置字体
            captcha.setCharType(Captcha.FONT_9);

            // 验证码存入session
            httpServletRequest.getSession().setAttribute(Constants.MALL_VERIFY_CODE_KEY, captcha.text().toLowerCase());

            // 输出图片流
            captcha.out(httpServletResponse.getOutputStream());
        } catch (Exception e) {
            logger.info("系统异常" + e);

        }


    }

    @GetMapping({"admin/login"})
    public String login() {
        System.out.println("hello");
        return "admin/login";
    }
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        logger.info ("index");
        request.setAttribute("path", "index");
        return "admin/index";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("loginName") String loginName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        System.out.println (loginName+password);
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        return "admin/index";
    }

}
