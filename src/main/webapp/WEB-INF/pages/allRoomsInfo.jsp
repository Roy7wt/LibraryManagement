<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>上海交通大学小组自习室</title>

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
                            <li><a href="/readers/info"> 用户信息 </a></li>
                            <li><a href="/book/insert"> 图书添加 </a></li>
                            <li><a href="/admin/borrow-transaction"> 预约图书处理 <span class="badge">${borrowRequest}</span></a></li>
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
                <c:if test="${empty reader and empty admin}">
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

                <c:if test="${!empty reader}">
                    <li><a href="/reader/info"><span class="glyphicon glyphicon-user"></span> ${reader.readerName} </a>
                    </li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-remove"></span> 注销 </a></li>
                </c:if>

                <c:if test="${!empty admin}">
                    <li><a href="/admin/info"><span class="glyphicon glyphicon-user" style="color: rgb(30, 144, 255)"></span> ${admin.adminName} </a>
                    </li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-remove"></span> 注销 </a></li>
                </c:if>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


<div class="container">
    <div class="jumbotron">
        <h1> 小组自习室使用情况 </h1>
        <hr/>

        <c:if test="${!empty roomEntityList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>地点</th>
                    <th>8:00-10:00</th>
                    <th>10:00-12:00</th>
                    <th>12:00-14:00</th>
                    <th>14:00-16:00</th>
                    <th>16:00-18:00</th>
                </tr>

                <c:forEach items="${roomEntityList}" var="room">
                    <tr>
                        <td>${room.roomLocation}</td>
                        <c:forEach items="${map}" var="entry">
                            <c:choose>
                                <c:when test="${entry.key == room.roomLocation}">
                                    <c:forEach var="index" begin="1" end="5">
                                        <c:choose>
                                            <c:when test="${entry.value.contains('a'.concat(index)) or entry.value.contains('w'.concat(index))}">
                                                <c:if test="${entry.value.contains('a'.concat(index))}">
                                                    <td style="background-color:rgb(30, 144, 255)"></td>
                                                </c:if>
                                                <c:if test="${entry.value.contains('w'.concat(index))}">
                                                    <td style="background-color:rgb(60, 179, 113)"></td>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <td></td>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <%--<td style="background-color:rgb(255, 211, 155)"></td>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                        <%--<td></td>--%>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

<c:choose>
    <c:when test="${empty reader}"></c:when>
    <c:otherwise>
    <div class="container">
        <div class="jumbotron">
            <h1> 借阅小组自习室 </h1>
            <form:form class="form-signin" action="/room/borrow" method="post">
                <div class="form-group">
                    <label for="roomLocation"> 小组自习室 </label>
                    <input type="text" class="form-control" id="roomLocation" name="roomLocation" value=""/>
                </div>

                <div class="form-group">
                    <label for="timePeriod"> 借阅时间 </label>
                    <select id="timePeriod" name="timePeriod" class="selectpicker">
                        <option value="1">8:00-10:00</option>
                        <option value="2">10:00-12:00</option>
                        <option value="3">12:00-14:00</option>
                        <option value="4">14:00-16:00</option>
                        <option value="5">16:00-18:00</option>
                    </select>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit" name ="type" value="admin"> 借阅提交 </button>
            </form:form>
            <c:if test="${result == 'occupied'}">
                <div class="alert alert-danger">
                    借阅失败 <strong> 该小组自习室已被预约或已被借阅 </strong>.
                </div>
            </c:if>
            <c:if test="${result == 'booked'}">
                <div class="alert alert-success">
                    借阅成功 <strong> 等待管理员处理 </strong>.
                </div>
            </c:if>
        </div>
    </div>
    </c:otherwise>
</c:choose>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<%--<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<%--<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>--%>
</body>
</html>