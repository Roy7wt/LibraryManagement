<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title> 管理员详情 </title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <%--<!--<%&#45;&#45;&lt;!&ndash;<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>&ndash;&gt;&#45;&#45;%>-->--%>
    <%--<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>--%>
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
                <li><a href="/room/info"> 小组自习室 </a></li>
                <c:if test="${!empty admin}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"> 管理员操作 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/readers/info"> 用户信息 <span class="badge">${breakerNumber}</span></a></li>
                            <li><a href="/book/insert"> 图书添加 </a></li>
                            <li><a href="/admin/borrow-transaction"> 预约图书处理<span class="badge">${borrowRequest}</span></a></li>
                            <li><a href="/admin/borrow-room-transaction"> 预约自习室处理 <span class="badge">${borrowRoomRequest}</span> </a></li>
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
                           aria-expanded="false"><span class="glyphicon glyphicon-log-in"></span> 登陆 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/login/reader"> 读者登陆 </a></li>
                            <li><a href="/login/admin"> 管理员登陆 </a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${!empty admin}">
                    <li><a href="/admin/info"><span class="glyphicon glyphicon-user" style="color: rgb(30, 144, 255)"></span> ${admin.adminName} </a></li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-remove"></span> 注销 </a></li>
                </c:if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>



<div class="container">
    <div class="jumbotron">
        <c:if test="${!empty admin}">
            <h1>用户详情</h1>
            <hr/>
            <table class="table table-bordered table-striped">
                    <%--<table>--%>
                <tr>
                    <th>编号</th>
                    <td>${admin.adminNo}</td>
                </tr>
                <tr>
                    <th>姓名</th>
                    <td>${admin.adminName}</td>
                </tr>
                <tr>
                    <th>电话</th>
                    <td>${admin.adminPhoneNumber}</td>
                </tr>
            </table>
        </c:if>
        <c:if test="${empty admin}">
            <h1>请先登录</h1>
            <hr/>
        </c:if>

    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>