<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title> 上海交通大学管理员小组自习室处理 </title>
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
                <li><a href="/room/info"> 小组自习室 </a></li>
                <c:if test="${!empty admin}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"> 管理员操作 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/readers/info"> 用户信息 <span class="badge">${breakerNumber}</span></a></li>
                            <li><a href="/book/insert"> 图书添加 </a></li>
                            <li><a href="/admin/borrow-transaction"> 预约图书处理 <span class="badge">${borrowRequest}</span></a>
                            </li>
                            <li><a href="/admin/borrow-room-transaction"> 预约自习室处理 <span
                                    class="badge">${borrowRoomRequest}</span> </a></li>
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
        <h1> 自习室预约处理 </h1>
        <hr/>
        <c:if test="${!empty borrowRoomEntityList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th> 小组自习室</th>
                    <th> 借阅人身份验证</th>
                    <th> 借阅时间</th>
                    <th> 操作</th>
                </tr>

                <c:forEach items="${borrowRoomEntityList}" var="transaction">
                    <tr>
                        <td>${transaction.borrowRoomLocation}</td>
                        <td>${transaction.borrowRoomReaderNo}</td>
                        <c:if test="${transaction.borrowTimePeriod == 1}">
                            <td>8:00 - 10:00</td>
                        </c:if>
                        <c:if test="${transaction.borrowTimePeriod == 2}">
                            <td>10:00 - 12:00</td>
                        </c:if>
                        <c:if test="${transaction.borrowTimePeriod == 3}">
                            <td>12:00 - 14:00</td>
                        </c:if>
                        <c:if test="${transaction.borrowTimePeriod == 4}">
                            <td>14:00 - 16:00</td>
                        </c:if>
                        <c:if test="${transaction.borrowTimePeriod == 5}">
                            <td>16:00 - 18:00</td>
                        </c:if>
                        <td>
                            <form:form action="/admin/borrow-room-agree" method="post">
                                <button type="submit" class="btn btn-sm btn-success"> 同意借阅请求</button>
                                <input type="hidden" name="roomLocation" value=${transaction.borrowRoomLocation}>
                                <input type="hidden" name="readerNo" value=${transaction.borrowRoomReaderNo}>
                                <input type="hidden" name="timePeriod" value=${transaction.borrowTimePeriod}>
                            </form:form>
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
</body>
<%--</html>--%>
<%--</title>--%>
<%--</head>--%>
<%--<body>--%>
<%----%>
<%--</body>--%>
</html>
</title>
</head>
<body>

</body>
</html>
