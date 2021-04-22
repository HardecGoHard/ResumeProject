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
<!--Создаём таблицу контейнер, которой задаём следующее
оформление:
border="1" - рамка вокруг контейнера. Увеличив число, можно увеличить толщину рамки.
align="center" - размещаем контейнер по центру экрана.
rules="rows" - убираем двойную рамку.
style="width:60%;" - добавляем стилевое свойства, делающее
контейнер и весь сайт "резиновым".
Сделать полноценный адаптивный дизайн, этим способом невозможно.-->
<table
        border="1"
        align="center"
        rules="rows"
        style="width:60%;">
    <!--Создаём строку-->
    <tr>
        <!--Создаём ячейку строки-->
        <td>
            <!--ШАПКА САЙТА-->

            <!--В ячейке строки создаём ещё одну таблицу для шапки сайта.
            Оформление:
            border="1" - двойная рамка толщиной в 1px
            background="images/168.png" - картинка в шапке сайта, если требуется.
            Адрес картинки вы должны вставить свой.
            bgcolor="#7FFFD4" - фоновый цвет в шапке, если нет картинки.
            cellpadding="10" - отступ содержимого от рамки не менее 10px.
            style="width:100%; border-radius:5px;" - добавляем "резиновость"
            и закругляем уголки рамки-->
            <table
                    border="1"
                    bgcolor="#CD5C5C"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку таблицы-->
                <tr>
                    <!--Создаём столбец таблицы-->
                    <th>
                        <!--Содержание ячейки столбца-->
                        <h1>Java resume project</h1>
                        <h3>с ипользованием Java servelets, JSP, JDBC,</h3>
                        <!--Закрываем таблицу-->
                    </th>
                </tr>
            </table>

            <!--ОСНОВНОЙ КОНТЕНТ-->

            <!--В этой же ячейке контейнера создаём ещё одну таблицу
            для основного контента.
            Оформление как и в предыдущей таблице-->

            <table
                    border="1"
                    bgcolor="#e6e6fa"
                    cellpadding="10"
                    style="width:100%; border-radius:5px;">
                <!--Создаём строку-->
                <tr>
                    <!--Создаём ячейку
                    Оформление:
                    rowspan="2" - объединяем две ячейки в одну.
                    Число объединяемых ячеек по числу ячеек в сайдбаре.
                    style="width:80%" - основной контент занимает 80% всей площади,
                    оставшиеся 20% для сайдбара-->
                    <td>
                        <table border="1" cellpadding="8" cellspacing="0">
                            <tr>
                                <th>Имя</th>
                                <th>Email</th>
                                <th>Телефон</th>
                            </tr>
                            <%
                                for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {
                            %>
                            <tr>
                                <td><a href="resume?uuid=<%=resume.getUuid()%>"><%= resume.getFullName()%></a></td>
                            <td><%=resume.getContact(ContactType.EMAIL)%></td>
                            <td><%=resume.getContact(ContactType.PHONE)%></td>

                            </tr>
                            <%
                                }
                            %>

                        </table>

                    </td>


            </table>

            <!--ПОДВАЛ-->

            <!--Создаём таблицу подвала-->
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
                        <h6>Resume Project</h6>
                        <!--Закрываем таблицу подвала. При желании в подвале можно
                        сделать несколько строк и столбцов-->
                    </th>
                </tr>
            </table>
            <!--Закрываем таблицу контейнера-->
        </td>
    </tr>
</table>
</body>
</html>
