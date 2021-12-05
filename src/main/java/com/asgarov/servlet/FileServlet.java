package com.asgarov.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.lineSeparator;

@WebServlet(urlPatterns = {"/file-servlet"})
@MultipartConfig(location = "C:\\Users\\extre\\OneDrive\\Documents\\Projects\\learning\\servletapp")
public class FileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Part part : req.getParts()) {
            if ("author_name".equals(part.getName())) {
                InputStream inputStream = part.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                String authorName = new BufferedReader(inputStreamReader).lines().collect(Collectors.joining("\n"));
                log(authorName);
            } else if ("file_name".equals(part.getName())) {
                part.write(Optional.ofNullable(part.getSubmittedFileName()).orElse("dummy.pdf"));
            }
        }
    }
}
