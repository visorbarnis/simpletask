package com.example.task.filters;

import org.springframework.stereotype.Component;

/**
 * disable CORS filter for developing and debugging Angular application
 * todo Remove it in production
 *
 */
@Component
public class CorsFilter {
}

//public class CorsFilter implements Filter {
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
//        chain.doFilter(req, res);
//    }
//}

