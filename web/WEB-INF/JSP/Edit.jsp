<%@ page import="org.Java.Model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="org.Java.Model.ContactType" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.Java.Model.ResumeSectionType" %>
<%@ page import="org.Java.Model.Section" %><%--
  Created by IntelliJ IDEA.
  User: Hardec
  Date: 22.04.2021
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <% Resume resume =((Resume) request.getAttribute("resume"));%>
    <title><%=resume.getFullName()%></title>
</head>
<body>
<table
        border="1"
        align="center"
        rules="rows"
        style="width:60%;">
    <!--Создаём строку-->
    <tr>
        <!--Создаём ячейку строки-->
        <td>

            <table
                    border="1"
                    bgcolor="#CD5C5C"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку таблицы-->
                <tr>
                    <th>
                        <!--Содержание ячейки столбца-->
                        <h1>Java resume project</h1>
                        <h3>с ипользованием Java servelets, JSP, JDBC,</h3>
                        <h2><a href="ser">Управление резюме</a></h2>
                        <!--Закрываем таблицу-->
                    </th>
                </tr>
            </table>


            <table
                    border="1"
                    bgcolor="#e6e6fa"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку-->
                <tr>
                    <td>
                        <form method="post" action="ser" enctype="application/x-www-form-urlencoded">
                        <table border="1" cellpadding="8" cellspacing="0">
                            <input type="hidden" name="uuid" value="<%=resume.getUuid()%>">
                            <h1 align="center"><%=resume.getFullName()%></h1>
                            <dt><b>Имя:</b></dt>
                            <dd><input type="text" name="full_name" size="60" value="<%=resume.getFullName()%>"></dd>
                            <h2 align="center">Контакты:</h2>
                            <hr>
                            <dl>
                                <%for(ContactType contactType : ContactType.values() ){ %>
                                <p>
                                <dt><b><%= contactType.getTitle()+": "%></b></dt>
                                <dd><input type="text" name="<%=contactType.toString()%>" size="60" value="<%=resume.getContact(contactType)%>"></dd>
                                </p>
                                <%}%>
                            </dl>

                            <hr>
                            <h2 align="center">Секции резюме:</h2>
                            <dl>

                            </dl>
                            <hr>
                            <%for(ResumeSectionType sectionType : ResumeSectionType.values() ){ %>
                            <p>
                                <%=resume.getResumeSection(sectionType).toHtmlEdit(sectionType)%>
                            </p>
                            <%}%>
                        </table>

                            <button type = "submit">Сохранить</button>
                            <button onclick="window.history.back()">Отменить</button>
                        </form>

                    </td>


            </table>

            <table
                    border="1"
                    bgcolor="#CD5C5C"
                    height="100"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку.-->
                <tr>
                    <!--Создаём столбец-->
                    <th>
                        <h5>Resume Project</h5>
                    </th>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>

