package online.heycm.auth.config.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import online.heycm.auth.utils.Jwt;
import online.heycm.utils.result.ApiResult;
import online.heycm.utils.result.Result;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * JWT认证过滤器
 *
 * @Author heycm@qq.com
 * @Date 2020-10-31 20:25
 */
@Slf4j
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[开始][JWT认证过滤器初始化成功][结束]");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("[开始][收到请求，方式：{}，接口：{}][结束]", request.getMethod(), request.getRequestURI());
        // 开发API
        if (isOpenUri(request)) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        // jwt
        String token = request.getHeader(Jwt.HEADER);
        // 未登录
        if (StrUtil.isBlank(token)) {
            unAuth(response);
            return;
        }
        // jwt校验失败
        if (!Jwt.verify(token)) {
            badToken(response);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("[开始][JWT认证过滤器销毁][结束]");
    }

    /**
     * 开放API
     */
    private boolean isOpenUri(HttpServletRequest request) {
        List<String> openUris = new ArrayList<>();
        // openUris.add("/static/**");
        // openUris.add("/doc.html");
        // openUris.add("/swagger/**");
        // openUris.add("/webjars/**");
        // openUris.add("/v2/**");
        // openUris.add("/swagger-resources/**");
        // openUris.add("/favicon.ico");
        // openUris.add("**.js");
        openUris.add("/login");
        openUris.add("/sso");
        String uri = request.getRequestURI();
        PathMatcher matcher = new AntPathMatcher();
        for (String open : openUris) {
            if (matcher.match(open, uri)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 未认证
     * @param response
     */
    private void unAuth(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            ApiResult result = Result.error("未登录");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
        } catch (IOException e) {
            log.error("[开始][未登录处理IO流异常][结束]");
        } finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    log.error("[开始][未登录处理关闭IO流异常][结束]");
                }
            }
        }
    }

    /**
     * Token校验失败
     * @param response
     */
    private void badToken(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            ApiResult result = Result.error("身份认证失败");
            printWriter.print(JSON.toJSONString(result));
            printWriter.flush();
        } catch (IOException e) {
            log.error("[开始][Token校验失败处理IO流异常][结束]");
        } finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    log.error("[开始][Token校验失败处理关闭IO流异常][结束]");
                }
            }
        }
    }
}
