<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户详情</title>

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
        <h1>用户详情</h1>
        <hr/>

        <table class="table table-bordered table-striped">
            <%--<table>--%>
            <tr>
                <th>学号</th>
                <td>${reader.readerNo}</td>
            </tr>
            <tr>
                <th>姓名</th>
                <td>${reader.readerName}</td>
            </tr>
            <tr>
                <th>电话</th>
                <td>${reader.readerPhoneNumber}</td>
            </tr>
            <tr>
                <th>剩余借阅数量</th>
                <td>${reader.borrowCount}</td>
            </tr>
        </table>

        <h1>借阅图书情况</h1>
        <hr/>
        <c:if test="${empty bookMap}">
            <p>暂无借阅图书</p>
        </c:if>

        <c:if test="${!empty bookMap}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>归还时间</th>
                    <th>操作</th>
                </tr>

                <c:forEach items="${bookMap}" var="entry">


                    <c:if test="${overdueBookList.contains(entry.key.bookNo)}">
                        <tr style="background-color:rgb(255, 211, 155)">
                            <td>${entry.key.bookName}</td>
                            <td>${entry.key.author}</td>
                            <td>${entry.key.publishName}</td>
                            <td>${entry.value}</td>
                            <td>
                                <a href="/book/return/${entry.key.bookNo}" type="button" class="btn btn-sm btn-success disabled"> 归还 </a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!overdueBookList.contains(entry.key.bookNo)}">
                        <c:choose>
                            <c:when test="${borrowedBookStatusMap.get(entry.key.bookNo) == 'W'}">
                                <tr>
                                    <td>${entry.key.bookName}</td>
                                    <td>${entry.key.author}</td>
                                    <td>${entry.key.publishName}</td>
                                    <td> 预约中 </td>
                                    <td>
                                        <form:form action="/book/borrow-cancle" method="post">
                                            <button type="submit"class="btn btn-sm btn-primary"> 预约解除 </button>
                                            <input type="hidden" name="bookNo" value=${entry.key.bookNo}>
                                            <input type="hidden" name="readerNo" value=${reader.readerNo}>
                                        </form:form>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>${entry.key.bookName}</td>
                                    <td>${entry.key.author}</td>
                                    <td>${entry.key.publishName}</td>
                                    <td>${entry.value}</td>
                                    <td>
                                        <a href="/book/return/${entry.key.bookNo}" type="button" class="btn btn-sm btn-success"> 归还图书 </a>
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
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
