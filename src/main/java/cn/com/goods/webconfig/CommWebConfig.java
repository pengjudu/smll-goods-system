/**
 * 
 */
package cn.com.goods.webconfig;

import cn.com.goods.interceptor.CommInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author dong5945
 *
 */
@Configuration
public class CommWebConfig  extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new CommInterceptor ()).addPathPatterns("/**")
				//登录接口不用通过拦截器否则会形成死循环，因为第一次登录没有token信息会一直跳转到登录接口
				.excludePathPatterns("/admin/login");
		super.addInterceptors(registry);
	}
	
}
