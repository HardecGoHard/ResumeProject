<%@ page import="org.Java.Model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="org.Java.Model.ContactType" %><%--
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
    <title>Resume Project</title>
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
                        <h3>с ипользованием Java servelets, JSP, JDBC</h3>
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
                        <table border="1" cellpadding="8" cellspacing="0">
                            <tr>
                                <th>Имя</th>
                                <th>Email</th>
                                <th>Телефон</th>
                                <th></th>
                                <th></th>
                            </tr>
                            <%
                                for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {
                            %>
                            <tr>
                                <td><a href="ser?uuid=<%=resume.getUuid()%>&action=view"><%= resume.getFullName()%></a></td>
                            <td><%=resume.getContact(ContactType.EMAIL)%></td>
                            <td><%=resume.getContact(ContactType.PHONE)%></td>
                                <td><a href="ser?uuid=<%=resume.getUuid()%>&action=edit">Редактировать</a></td>
                                <td><a href="ser?uuid=<%=resume.getUuid()%>&action=delete">Удалить</a></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                        <a href="ser?action=add"><img src="image/add.png" width="50" height="50"></a>
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
