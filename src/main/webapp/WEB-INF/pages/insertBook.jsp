<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 16/5/18
  Time: 09:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title> 加入新图书 </title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.bootcss.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
                <c:if test="${!empty admin}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"> 管理员操作 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/readers/info"> 用户信息 </a></li>
                            <li><a href="/book/insert"> 图书添加 </a></li>
                            <li><a href="/admin/borrow-transaction"> 预约图书处理<span
                                    class="badge">${borrowRequest}</span></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </c:if>
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
                <c:if test="${empty admin}">
                    <li><a href="/register"><span class="glyphicon glyphicon-user"></span> 注册 </a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"><span class="glyphicon glyphicon-log-in"></span> 登陆 <span
                                class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/login/reader"> 读者登陆 </a></li>
                            <li><a href="/login/admin"> 管理员登陆 </a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${!empty admin}">
                    <li><a href="/admin/info"><span class="glyphicon glyphicon-user"
                                                    style="color: rgb(30, 144, 255)"></span> ${admin.adminName} </a>
                    </li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-remove"></span> 注销 </a></li>
                </c:if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div class="container">
    <div class="jumbotron">
        <h1> 图书项目 </h1>
        <hr/>

        <form:form action="/book/insert" method="post" modelAttribute="book">
            <div class="form-group">
                <label for="bookNo" class="col-sm-2 control-label"> 编号 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="bookNo"
                           name="bookNo" placeholder="Book's Number">
                </div>
                <label for="classNo" class="col-sm-2 control-label"> 类别 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="classNo"
                           name="classNo" placeholder="Book's Classification">
                </div>
                <label for="bookName" class="col-sm-2 control-label"> 书名 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="bookName"
                           name="bookName" placeholder="Book's Name">
                </div>
                <label for="author" class="col-sm-2 control-label"> 作者 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="author"
                           name="author" placeholder="Book's author">
                </div>
                <label for="publishName" class="col-sm-2 control-label"> 出版社 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="publishName"
                           name="publishName" placeholder="Book's publishName">
                </div>
                <label for="introduction" class="col-sm-2 control-label"> 简介 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="introduction"
                           name="introduction" placeholder="Book's introduction">
                </div>

                    <%--<label for="publishDate" class="col-sm-2 control-label"> 类别 </label>--%>
                    <%--<div class="col-sm-10">--%>
                    <%--<input type="text" class="form-control" id="publishDate"--%>
                    <%--name="publishDate" placeholder="Book's publishDate">--%>
                    <%--</div>--%>
                <label for="bookResidue" class="col-sm-2 control-label"> 存量 </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="bookResidue"
                           name="bookResidue" placeholder="Book's bookResidue">
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-sm btn-success"> 提交</button>
                </div>
            </div>
        </form:form>
    </div>

    <div class="jumbotron">
        <h1> 图书类别 </h1>
        <hr/>

        <c:if test="${!empty classList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>类别编号</th>
                <th>类别名称</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${classList}" var="bookClass">
            <tr>
                <td>${bookClass.classNo}</td>
                <td>${bookClass.className}</td>
                <td>
                    <form:form action="/book/classChange" method="post">
                        <button type="submit" class="btn btn-sm btn-primary"> 修改 </button>
                        <input type="hidden" name="classNo" value="${bookClass.classNo}">
                    </form:form>
                        <%--<a href="#" type="button"--%>
                        <%--class="btn btn-sm btn-warning"> 恢复权限 </a>--%>
                </td>
            </tr>
            </c:forEach>
            </c:if>
    </div>
</div>
</body>
</html>
