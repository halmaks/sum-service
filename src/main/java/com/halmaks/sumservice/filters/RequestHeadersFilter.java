package com.halmaks.sumservice.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.halmaks.sumservice.models.RequestContext;

import org.springframework.stereotype.Component;

import static com.halmaks.sumservice.services.ItemService.ADDITIONAL_HEADER;

@Component
public class RequestHeadersFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        RequestContext.set(ADDITIONAL_HEADER, request.getHeader(ADDITIONAL_HEADER));

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            throw e;
        } finally {
            RequestContext.clean();
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
