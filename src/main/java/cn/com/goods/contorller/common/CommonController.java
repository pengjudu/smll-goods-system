package cn.com.goods.contorller.common;

import cn.com.goods.common.Constants;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class CommonController {
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

}
