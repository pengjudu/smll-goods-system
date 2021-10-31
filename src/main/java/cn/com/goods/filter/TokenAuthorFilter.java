//package cn.com.goods.filter;
//
//import cn.com.goods.util.Message;
//import cn.com.goods.util.jwtUtil;
//import com.alibaba.fastjson.JSON;
//
//import io.jsonwebtoken.Claims;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//
//@Component
//@WebFilter(urlPatterns = { "/home/*"}, filterName = "tokenAuthorFilter")
//
//public class TokenAuthorFilter implements Filter {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Override
//	public void destroy() {
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse response1, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) response1;
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=utf-8");
//		// chain.doFilter(req, rep);
//		Message resultInfo = new Message();
//		String token = response.getHeader ("token");// header方式
//		//Object fk =reqf.getAttribute("user");
//		//logger.info(fk.toString()+"-----------跨域连接器-----------" + token+resultInfo.getToken());
//
//
//
//		boolean isFilter = false;
//		String method = ((HttpServletRequest) request).getMethod();
//		logger.info("Method:" + method);
//		if (method.equals("OPTIONS")) {
//			response.setStatus(HttpServletResponse.SC_OK);
//		} else {
//			if (null == token || token.isEmpty()) {
//				logger.info("head is null");
//				resultInfo.setRetCode("01");
//				resultInfo.setRetMsg("用户授权认证没有通过!客户端请求参数中无token信息");
//			} else {
//
//				Claims claims;
//				try {
//					claims = jwtUtil.verifyToken(token);
//					resultInfo.setRetCode("00");
//					token = jwtUtil.generToken(claims.getId(), null, null);
//					isFilter = true;
//					response.setHeader("token", token);
//					Message message = new Message();
//					message.setToken(token);
//					request.setAttribute("message", message);
//
//					// }
//				} catch (Exception e) {
//					logger.error(e.getMessage());
//					logger.info("系统异常" + e);
//					logger.error("用户授权认证没有通过!客户端请求参数token信息无效");
//					resultInfo.setRetCode("01");
//					resultInfo.setRetMsg("用户授权认证没有通过!客户端请求参数token信息无效");
//
//				}
//			}
//			if (!resultInfo.getRetCode().equals("00")) {// 验证失败
//				PrintWriter writer = null;
//				OutputStreamWriter osw = null;
//				try {
//					osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
//					writer = new PrintWriter(osw, true);
//					String jsonStr = JSON.toJSONString(resultInfo);
//					writer.write(jsonStr);
//					writer.flush();
//					writer.close();
//					osw.close();
//				} catch (UnsupportedEncodingException e) {
//					logger.error("过滤器返回信息失败:" + e.getMessage(), e);
//				} catch (IOException e) {
//					logger.error("过滤器返回信息失败:" + e.getMessage(), e);
//				} finally {
//					if (null != writer) {
//						writer.close();
//					}
//					if (null != osw) {
//						osw.close();
//					}
//				}
//				return;
//			}
//			if (isFilter) {
//				logger.info("token filter过滤ok!");
//				chain.doFilter(request, response);
//			}
//		}
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//}
//
