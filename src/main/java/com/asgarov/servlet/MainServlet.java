package com.asgarov.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

@WebServlet(urlPatterns = {"/cool-servlet"})
public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        log("===> Method init =)");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        resp.getWriter().write("Method service\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String params = joinUrlParameters(req);
        resp.getWriter().write("Method doPost\nURI: " + uri + "\nParams:\n" + params + "\n");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String params = joinUrlParameters(req);
        resp.getWriter().write("Method doGet\nURI: " + uri + "\nParams:\n" + params + "\n");
    }

    private String joinUrlParameters(HttpServletRequest req) {
        return req.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + " => " + String.join(" and ", entry.getValue()))
                .collect(Collectors.joining(lineSeparator()));
    }

    @Override
    public void destroy() {
        super.destroy();
        log("===> Method destroy =)");
    }
}
