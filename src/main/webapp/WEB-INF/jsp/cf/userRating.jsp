<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../commons/taglibs.jsp" %>
<%@ include file="../commons/meta.jsp" %>
<html>
<head>
    <title>爬虫状态监控</title>
    <script src="${ctx}/static/js/echarts.min.js"></script>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/sentiment/Iframe.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/sentiment/category.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" type="text/css" media="screen"/>
</head>

<body>
<div class="cp_title">评分表单</div>
<div class="add_cp">
    <form id = "ratingForm" action="${ctx}/cf/getRatingData" method="POST">
        <input value="查询" type="hidden"/>
    </form>
</div>
<div class="table_con">
    <table>
        <tr class="tb_title">
            <td width="33%"><strong>用户</strong></td>
            <td width="33%"><strong>书籍</strong></td>
            <td width="33%"><strong>评分</strong></td>
        </tr>
        <c:forEach items="${userRatingObjList}" var="ratingObj">
            <tr>
                <td width="33%"><c:out value="${ratingObj.userId}"/></td>
                <td width="33%"><c:out value="${ratingObj.bookId}"/></td>
                <td width="33%"><c:out value="${ratingObj.rating}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="page_control">
    <span class="prePage" style="line-height: 21px;"><a href="javascript:void(0);" onclick="prePage(${pageNum})">上一页</a></span>
    <span class="s_space"></span>
    <span class="s_space"></span>
    第<span class="pageNo"><c:out value="${pageNum}"/></span>
    页/共<span class="totalPage"><fmt:formatNumber type="number" value="${totalPage}" maxFractionDigits="0"/></span>页
    <span class="s_space"></span>
    <span class="nextPage" style="line-height: 21px;"><a href="javascript:void(0);" onclick="nextPage(${pageNum})">下一页</a></span>
    <span class="s_space"></span>
</div>


<script type="text/javascript">
    setRowColor();

    function setRowColor() {
        $("tr:even").css("background-color", "#eff3f5");
    }

    function prePage(currentPage) {
        window.location = "${ctx}/cf/getRatingData?pageNum=" + (currentPage - 1);
    }

    function nextPage(currentPage) {
        window.location = "${ctx}/cf/getRatingData?pageNum=" + (currentPage + 1);
    }
</script>
</body>
</html>
