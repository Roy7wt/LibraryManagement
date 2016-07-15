<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 16/5/15
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="zh-CN">
<head>
    <title>上海交通大学图书馆用户登陆</title>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>Signin Template for Bootstrap</title>

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link type="text/css" rel="stylesheet" href="/css/signin.css">


        <script src="https://ajax.googleapis.bootcss.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    </head>

<body>

<div class="container">

    <c:if test="${'reader'.equals(loginType)}">
        <form:form class="form-signin" action="/login/post" method="post">
            <h2 class="form-signin-heading">登陆界面 </h2>
            <label for="readerNo" class="sr-only">Student Id</label>
            <input type="text" id="readerNo" class="form-control"
                   name="readerNo" placeholder="Student Id" required
                   autofocus>
            <label for="readerPassword" class="sr-only">Password</label>
            <input type="password" id="readerPassword" class="form-control"
                   name="readerPassword" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name ="type" value="reader"> 登陆 </button>
            <input type="hidden" name="adminNo" value="">
            <input type="hidden" name="adminPassword" value="">
        </form:form>

        <c:if test="${!empty login_error}">
            <div class="alert alert-danger">
                Login Failed! <strong>${login_error} Does Not exixts</strong>.
            </div>
        </c:if>
        <c:if test="${!empty password_error}">
            <div class="alert alert-danger">
                Login Failed! <strong>Wrong Password</strong>.
            </div>
        </c:if>
        <c:if test="${!empty status_error}">
            <div class="alert alert-danger">
                Please contact Administor! User <strong>${status_error}</strong> .
            </div>
        </c:if>
    </c:if>

    <c:if test="${'admin'.equals(loginType)}">
        <form:form class="form-signin" action="/login/post" method="post">
            <h2 class="form-signin-heading">登陆界面 </h2>
            <label for="adminNo" class="sr-only">Admin Id</label>
            <input type="text" id="adminNo" class="form-control"
                   name="adminNo" placeholder="Admin Id" required
                   autofocus>
            <label for="adminPassword" class="sr-only">Password</label>
            <input type="password" id="adminPassword" class="form-control"
                   name="adminPassword" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name ="type" value="admin"> 登陆 </button>
            <input type="hidden" name="readerNo" value="">
            <input type="hidden" name="readerPassword" value="">
        </form:form>
        <c:if test="${!empty login_error}">
            <div class="alert alert-danger">
                Login Failed! <strong>${login_error} Does Not exixts</strong>.
            </div>
        </c:if>
        <c:if test="${!empty password_error}">
            <div class="alert alert-danger">
                Login Failed! <strong>Wrong Password</strong>.
            </div>
        </c:if>
    </c:if>

</div> <!-- /container -->

</body>
</html>

</title>
</head>
<body>

</body>
</html>
