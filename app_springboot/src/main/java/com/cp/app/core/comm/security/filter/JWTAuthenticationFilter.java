package com.cp.app.core.comm.security.filter;

import com.cp.app.core.comm.security.JwtsUtil;
import com.cp.app.core.comm.security.TokenException;
import com.cp.app.core.comm.security.authentiation.SystemUserDetails;
import com.cp.app.core.comm.security.authentiation.SystemUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName JWTAuthenticationFilter
 * @Description TODO 登录过滤器(用户认证)
 * JWT认证过滤器
 * * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * * 如果校验通过，就认为这是一个取得授权的合法请求
 * @createdate 2019/2/14 星期四 14:04
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private SystemUserDetailsService systemUserDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SystemUserDetailsService userDetailsService) {
        super(authenticationManager);
        this.systemUserDetailsService = userDetailsService;
        logger.info("创建JWTAuthenticationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("进入doFilterInternal方法");
        String header = request.getHeader("Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        long start = System.currentTimeMillis();
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new TokenException("Token为空");
        }
        String username = null;
        try {
            username = JwtsUtil.getUserName(token);
            long end = System.currentTimeMillis();
            logger.info("执行时间: {}", (end - start) + " 毫秒");
            if (username != null) {
                SystemUserDetails sysUser = (SystemUserDetails) systemUserDetailsService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(sysUser, token, sysUser.getAuthorities());
            }
        } catch (ExpiredJwtException e) {
            logger.info("Token已过期：" + e.getMessage());
        } catch (Exception e) {
            logger.info("无效的Token：" + e.getMessage());
        }
        return null;
    }
}