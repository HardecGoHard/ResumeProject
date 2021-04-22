package org.Java.servlets;

import org.Java.Model.Resume;
import org.Java.Storage.IStorage;
import org.Java.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private IStorage storage =Config.get().getStorage();;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("resumes", storage.getSortedAll());
        request.getRequestDispatcher("/WEB-INF/JSP/ListResumes.jsp").forward(request,response);
       // response.getWriter().write("storage.get(.toString()");
    }
}
