<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
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
                <li><a href="/book/search/detail"> 详细查询 </a></li>
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
        </div>
    </div>
</nav>


<div class="container">
    <div class="jumbotron">
        <h1>查询结果</h1>
        <hr/>

        <c:if test="${!empty bookList}">
            <table class="table table-bordered table-striped">
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>出版社</th>
                    <th>简介</th>
                    <th>可外接数量</th>
                    <th>状态</th>
                </tr>

                <c:forEach items="${bookList}" var="book" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index == 0}">
                            <c:if test="${get == 'true'}">
                                <tr style="background-color:rgb(255, 211, 155)">
                                    <td>${book.bookName}</td>
                                    <td>${book.author}</td>
                                    <td>${book.publishName}</td>
                                    <td>${book.introduction}</td>
                                    <td>${book.bookResidue}</td>
                                    <td>
                                        <c:if test="${!empty borrowedList}">
                                            <c:if test="${borrowedList.contains(book)}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-warning disabled"> 已借阅 </a>
                                            </c:if>
                                            <c:if test="${!borrowedList.contains(book)}">
                                                <c:if test="${book.bookResidue != 0}">
                                                    <a href="/book/borrow/${book.bookNo}" type="button"
                                                       class="btn btn-sm btn-primary"> 借阅 </a>
                                                </c:if>
                                                <c:if test="${book.bookResidue == 0}">
                                                    <a href="/book/borrow/${book.bookNo}" type="button"
                                                       class="btn btn-sm btn-danger disabled"> 不可借阅 </a>
                                                </c:if>
                                            </c:if>
                                        </c:if>

                                        <c:if test="${empty borrowedList}">
                                            <c:if test="${book.bookResidue != 0}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-primary"> 借阅 </a>
                                            </c:if>
                                            <c:if test="${book.bookResidue == 0}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-warning disabled"> 不可借阅 </a>
                                            </c:if>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${get == 'false' or get == null}">
                                <tr>
                                    <td>${book.bookName}</td>
                                    <td>${book.author}</td>
                                    <td>${book.publishName}</td>
                                    <td>${book.introduction}</td>
                                    <td>${book.bookResidue}</td>
                                    <td>
                                        <c:if test="${!empty borrowedList}">
                                            <c:if test="${borrowedList.contains(book)}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-warning disabled"> 已借阅 </a>
                                            </c:if>
                                            <c:if test="${!borrowedList.contains(book)}">
                                                <c:if test="${book.bookResidue != 0}">
                                                    <a href="/book/borrow/${book.bookNo}" type="button"
                                                       class="btn btn-sm btn-primary"> 借阅 </a>
                                                </c:if>
                                                <c:if test="${book.bookResidue == 0}">
                                                    <a href="/book/borrow/${book.bookNo}" type="button"
                                                       class="btn btn-sm btn-danger disabled"> 不可借阅 </a>
                                                </c:if>
                                            </c:if>
                                        </c:if>

                                        <c:if test="${empty borrowedList}">
                                            <c:if test="${book.bookResidue != 0}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-primary"> 借阅 </a>
                                            </c:if>
                                            <c:if test="${book.bookResidue == 0}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-warning disabled"> 不可借阅 </a>
                                            </c:if>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>${book.bookName}</td>
                                <td>${book.author}</td>
                                <td>${book.publishName}</td>
                                <td>${book.introduction}</td>
                                <td>${book.bookResidue}</td>
                                <td>
                                    <c:if test="${!empty borrowedList}">
                                        <c:if test="${borrowedList.contains(book)}">
                                            <a href="/book/borrow/${book.bookNo}" type="button"
                                               class="btn btn-sm btn-warning disabled"> 已借阅 </a>
                                        </c:if>
                                        <c:if test="${!borrowedList.contains(book)}">
                                            <c:if test="${book.bookResidue != 0}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-primary"> 借阅 </a>
                                            </c:if>
                                            <c:if test="${book.bookResidue == 0}">
                                                <a href="/book/borrow/${book.bookNo}" type="button"
                                                   class="btn btn-sm btn-danger disabled"> 不可借阅 </a>
                                            </c:if>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${empty borrowedList}">
                                        <c:if test="${book.bookResidue != 0}">
                                            <a href="/book/borrow/${book.bookNo}" type="button"
                                               class="btn btn-sm btn-primary"> 借阅 </a>
                                        </c:if>
                                        <c:if test="${book.bookResidue == 0}">
                                            <a href="/book/borrow/${book.bookNo}" type="button"
                                               class="btn btn-sm btn-warning disabled"> 不可借阅 </a>
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>