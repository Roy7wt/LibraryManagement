<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>上海交通大学图书馆注册用户</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"> 首页 </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/book/info"> 图书信息 </a></li>
                <li><a href="/reader/infoChange"> 修改信息 </a></li>
                <%--<li class="dropdown">--%>
                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"--%>
                <%--aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                <%--<ul class="dropdown-menu">--%>
                <%--<li><a href="#">Action</a></li>--%>
                <%--<li><a href="#">Another action</a></li>--%>
                <%--<li><a href="#">Something else here</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a href="#">Separated link</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a href="#">One more separated link</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="/book/search" method="post" class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search"
                                   name="bookName" id="bookName">
                        </div>
                        <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i>
                        </button>
                    </form>
                </li>
                <c:if test="${empty reader}">
                    <li><a href="/register"><span class="glyphicon glyphicon-user"></span> 注册 </a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"><span class="glyphicon glyphicon-log-in"></span> 登陆 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/login/reader"> 读者登陆 </a></li>
                            <li><a href="/login/admin"> 管理员登陆 </a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${!empty reader}">
                    <li><a href="/reader/info"><span class="glyphicon glyphicon-user"></span> ${reader.readerName} </a></li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-remove"></span> 注销 </a></li>
                </c:if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="jumbotron">
        <h1> 用户信息修改 </h1>
        <hr/>
        <form:form action="/reader/infoChange" method="post" role="form">
            <div class="form-group">
                    <%--<label for="readerNo">StudentID:</label>--%>
                <input type="hidden" class="form-control" id="readerNo" name="readerNo" value="${reader.readerNo}"/>
            </div>
            <div class="form-group">
                <label for="readerName">Name:</label>
                <input type="text" class="form-control" id="readerName" name="readerName" value="${reader.readerName}"/>
            </div>
            <div class="form-group">
                    <%--<label for="readerPassword">readerPassword:</label>--%>
                <input type="hidden" class="form-control" id="readerPassword" name="readerPassword"
                       value="${reader.readerPassword}"/>
            </div>
            <div class="form-group">
                <label for="readerSex">Sex:</label>
                <input type="text" class="form-control" id="readerSex" name="readerSex" value="${reader.readerSex}"/>
            </div>
            <div class="form-group">
                <label for="readerPhoneNumber">Phone Number:</label>
                <input type="text" class="form-control" id="readerPhoneNumber" name="readerPhoneNumber"
                       value="${reader.readerPhoneNumber}"/>
            </div>
            <div class="form-group">
                <label for="institude">Insititude:</label>
                <input type="text" class="form-control" id="institude" name="institude" value="${reader.institude}"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-success"> 确认修改 </button>
            </div>
        </form:form>


    </div>

    <div class="jumbotron">
        <h1> 修改密码 </h1>
        <hr/>
        <form:form action="/reader/changePassword" method="post" role="form">
            <div class="form-group">
                    <%--<label for="readerNo">StudentID:</label>--%>
                <input type="hidden" class="form-control" id="readerNo" name="readerNo" value="${reader.readerNo}"/>
            </div>
            <div class="form-group">
                <label for="newpwd">New Password:</label>
                <input type="password" class="form-control" id="newpwd" name="newpwd"/>
            </div>
            <div class="form-group">
                <label for="confirmpwd">Confirm Password:</label>
                <input type="password" class="form-control" id="confirmpwd" name="confirmpwd"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-success"> 确认修改 </button>
            </div>
        </form:form>
        <c:if test="${check_type == 'error'}">
            <div class="alert alert-danger">
                密码修改失败 两次密码输入不同
            </div>
        </c:if>
        <c:if test="${check_type == 'right'}">
            <div class="alert alert-success">
                密码修改成功
            </div>
        </c:if>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>