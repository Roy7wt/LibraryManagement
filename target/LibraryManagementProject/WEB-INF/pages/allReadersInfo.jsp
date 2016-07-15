<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 16/5/18
  Time: 09:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title> 读者信息 </title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>上海交通大学馆藏图书</title>

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
                            <li><a href="/admin/borrow-transaction"> 预约图书处理<span class="badge">${borrowRequest} </a></li>
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
        <h1> 用户详情 </h1>
        <hr/>
        <c:if test="${!empty readerList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>联系方式</th>
                    <th>违规次数</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${readerList}" var="reader">
                    <%--<c:if test="${reader.breakRules > 2 and }">--%>
                        <%--<tr style="background-color:rgb(255, 211, 155)">--%>
                            <%--<td>${reader.readerNo}</td>--%>
                            <%--<td>${reader.readerName}</td>--%>
                            <%--<td>${reader.readerPhoneNumber}</td>--%>
                            <%--<td>${reader.breakRules}</td>--%>
                            <%--<td>--%>
                                <%--<a href="/book/borrow/${book.bookNo}" type="button"--%>
                                   <%--class="btn btn-sm btn-warning"> 限制权限 </a>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</c:if>--%>
                    <c:if test="${reader.readerStatus == 'n'}">
                        <tr >
                            <td>${reader.readerNo}</td>
                            <td>${reader.readerName}</td>
                            <td>${reader.readerPhoneNumber}</td>
                            <td>${reader.breakRules}</td>
                            <td>
                                <form:form action="/admin/status" >
                                    <button type="submit"class="btn btn-sm btn-primary"> 恢复权限 </button>
                                    <input type="hidden" name="readerNo" value=${reader.readerNo}>
                                    <input type="hidden" name="setStatus" value="y">
                                </form:form>
                                <%--<a href="#" type="button"--%>
                                   <%--class="btn btn-sm btn-warning"> 恢复权限 </a>--%>
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${reader.readerStatus == 'y'}">
                        <c:choose>
                            <c:when test="${reader.breakRules < 3}">
                                <tr>
                                    <td>${reader.readerNo}</td>
                                    <td>${reader.readerName}</td>
                                    <td>${reader.readerPhoneNumber}</td>
                                    <td>${reader.breakRules}</td>
                                    <td>
                                        <a href="/book/borrow/${book.bookNo}" type="button"
                                           class="btn btn-sm btn-success disabled"> 正常使用 </a>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr style="background-color:rgb(255, 211, 155)">
                                    <td>${reader.readerNo}</td>
                                    <td>${reader.readerName}</td>
                                    <td>${reader.readerPhoneNumber}</td>
                                    <td>${reader.breakRules}</td>
                                    <td>
                                        <form:form action="/admin/status" >
                                            <button type="submit"class="btn btn-sm btn-warming"> 注销权限 </button>
                                            <input type="hidden" name="readerNo" value=${reader.readerNo}>
                                            <input type="hidden" name="setStatus" value="n">
                                        </form:form>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

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
