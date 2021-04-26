package org.Java.servlets;

import org.Java.Model.*;
import org.Java.Storage.IStorage;
import org.Java.util.Config;
import org.Java.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
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

        for (ResumeSectionType resumeSectionType : ResumeSectionType.values()) {
            switch (resumeSectionType) {
                case QUALIFICATIONS:
                case OBJECTIVE:
                    resume.setResumeSection(resumeSectionType,
                            new TextSection(request.getParameter(resumeSectionType.toString())));
                    break;
                case ACHIEVEMENT:
                case PERSONAL:
                    resume.setResumeSection(resumeSectionType,
                            new ListSection(request.getParameterValues(resumeSectionType.toString())));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    resume.setResumeSection(resumeSectionType,
                            new OrganisationSection(new Organisation(
                                    request.getParameter(resumeSectionType.toString() + ".ORGANISATION.NAME"),
                                    request.getParameter(resumeSectionType.toString() + ".ORGANISATION.URL"),
                                    new Organisation.Position(DateUtil.ParseDate(request.getParameter(
                                            resumeSectionType.toString() + ".POSITION.DATESTART")),
                                            DateUtil.ParseDate(request.getParameter(resumeSectionType.toString() + ".POSITION.DATEEND")),
                                            request.getParameter(resumeSectionType.toString() + ".POSITION.NAME"),
                                            request.getParameter(resumeSectionType.toString() + "..POSITION.DESCRIPTION")))));
                    break;
            }
        }
        if (storage.get(uuid) != null) {
            storage.update(resume);
        }
        else{
            storage.save(resume);
        }

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
            case "edit":
                resume = storage.get(uuid);
                break;
            case "add":
                resume = new Resume();
                break;
            default:
                throw new IllegalArgumentException("Action " + action + "is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(("view".equals(action) ? "/WEB-INF/JSP/View.jsp" : "/WEB-INF/JSP/Edit.jsp")).forward(request, response);
    }
}
