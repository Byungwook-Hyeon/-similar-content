package com.lguplus.fleta.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> apiDocsFilters() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();
        registrationBean.setFilter(apiDocsFilter());
        registrationBean.addUrlPatterns("/api-docs/*");
        registrationBean.setOrder(1);
        registrationBean.setName("api-docs-filter");
        return registrationBean;
    }

    private Filter apiDocsFilter() {

        return new GenericFilterBean() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

                final HttpServletRequest httpRequest = (HttpServletRequest) request;
                log.debug("Accept Header : {}", httpRequest.getHeader("Accept"));

                final HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest) {

                    @Override
                    public String getHeader(final String name) {
                        if (HttpHeaders.ACCEPT.equalsIgnoreCase(name)) {
                            return MediaType.APPLICATION_JSON_VALUE;
                        } else {
                            return super.getHeader(name);
                        }
                    }

                    @Override
                    public Enumeration<String> getHeaders(final String name) {
                        return Collections.enumeration(List.of(getHeader(name)));
                    }
                };
                chain.doFilter(wrapper, response);
            }
        };

    }

}

