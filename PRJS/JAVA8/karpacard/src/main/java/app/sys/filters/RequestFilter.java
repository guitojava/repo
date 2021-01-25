/*
 * Copyright (c) 2018. LeonDev, SA.
 * This software is the proprietary information of George Leon.
 * Use is subject to license terms.
 */

package app.sys.filters;

import app.sys.ApiPaths;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {
    private static final transient Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    private static final String SESSION_ID_PARAM_NAME = "sessionId";
    public static final String REQUEST_ID_PARAM_NAME = "requestId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
logger.info("Today is > "+new Date());
        String sessionId = ((HttpServletRequest) request).getSession().getId();
        MDC.put(SESSION_ID_PARAM_NAME, sessionId);
        request.setAttribute(SESSION_ID_PARAM_NAME, sessionId); // to be able to retrieve in access log pattern
        String requestId = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID_PARAM_NAME, requestId);
        request.setAttribute(REQUEST_ID_PARAM_NAME, requestId); // to be able to retrieve in access log pattern

        // print all headers
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            logger.info("{}:{}", headerName, req.getHeader(headerName));
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
        MDC.clear();
    }

    public static String getHostUrl(HttpServletRequest req) {

        StringBuilder url = new StringBuilder();
        String hostHeader = "x-forwarded-host";
        String protocolHeader="x-forwarded-proto";
        String proto    = req.getHeader(protocolHeader);
        String host     = req.getHeader(hostHeader);

        if ( StringUtils.isNotBlank( host )   &&  StringUtils.isNotBlank( proto )  ) {
            url.append(proto)
                    .append("://")
                    .append(host)
                    .append("/sys/" + ApiPaths.API_V1);

        } else {
            String scheme = req.getScheme();             // http/https
            String serverName = req.getServerName();     // hostname.com
            int serverPort = req.getServerPort();        // 8000

            url.append(scheme)
                    .append("://")
                    .append(serverName)
                    .append(":")
                    .append(serverPort)
                    .append("/sys/" + ApiPaths.API_V1);
        }
        return url.toString();
    }
}