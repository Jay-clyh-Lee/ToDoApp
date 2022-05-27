<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ToDo App</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
    <main id="content">
        <div id="page_header">
            <img src="/images/logo.png">
            <a href="/todo/add">Add task</a>
        </div>

        <div id="active_area">
            <table>
                <c:forEach var="todo" items="${todos}">
                    <tr>
                        <td><c:out value="${todo.text}"/></td>
                        <td>
                            <a href="/todo/<c:out value='${todo.id}'/>">view</a>
                            <a href="/todo/update/<c:out value='${todo.id}'/>">update</a>
                            <a href="/todo/delete/<c:out value='${todo.id}'/>">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div id="buttons">
            <a href="/todo/add">Add Todo</a>
        </div>
    </main>  
</body>
</html>