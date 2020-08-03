package com.astralife.employee.base;

import javax.servlet.http.HttpServletRequest;

public class BaseController extends com.astralife.employee.base.BaseComponent {
    protected static final String COMPLETED = " Completed";

    public String taskname(HttpServletRequest request) {
        String method     = null != request && null != request.getMethod() ? request.getMethod() : "method";
        String requestUri = null != request && null != request.getRequestURI() ? request.getRequestURI() : "requestUri";
        return method + " " + requestUri;
    }

    public String taskname(HttpServletRequest request, Boolean done) {
        String method     = null != request && null != request.getMethod() ? request.getMethod() : "method";
        String requestUri = null != request && null != request.getRequestURI() ? request.getRequestURI() : "requestUri";
        return method + " " + requestUri + COMPLETED;
    }
}
