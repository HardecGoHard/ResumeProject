package org.Java.servlets;

import org.Java.Model.ContactType;
import org.Java.Model.Resume;
import org.Java.Model.ResumeSectionType;
import org.Java.Storage.IStorage;
import org.Java.util.Config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private IStorage storage = Config.get().getStorage();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("full_name");
        Resume resume = new Resume(fullName, uuid);
        for (ContactType contactType : ContactType.values()) {
            resume.setContact(contactType, request.getParameter(contactType.toString()));
        }

        storage.update(resume);
        response.sendRedirect("ser");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getSortedAll());
            request.getRequestDispatcher("/WEB-INF/JSP/ListResumes.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("ser");
                return;
            case "view":
                resume = storage.get(uuid);
                request.setAttribute("resume", resume);
                request.getRequestDispatcher("/WEB-INF/JSP/View.jsp").forward(request, response);
                response.sendRedirect("View.jsp");
                return;
            case "edit":
                resume = storage.get(uuid);
                request.setAttribute("resume", resume);
                request.getRequestDispatcher("/WEB-INF/JSP/Edit.jsp").forward(request, response);
                response.sendRedirect("Edit.jsp");
                return;
            default:
                throw new IllegalArgumentException("Action " + action + "is illegal");
        }
        // response.getWriter().write("storage.get(.toString()");
    }
}
