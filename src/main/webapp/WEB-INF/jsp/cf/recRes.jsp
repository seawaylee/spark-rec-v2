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
<div class="cp_title">CF模型实时推荐</div>

<div class="add_cp">
    <form action="${ctx}/cf/getRecommend" method="POST">
        <div id="queryDiv">
            <select id="user-select" style="width:110px" name="userno">
                <c:forEach items="${userSet}" var="curr_user">
                    <option value="${curr_user}"
                            <c:if test="${curr_user == user}">
                                selected="selected"
                            </c:if>
                            style="width:100px">${curr_user}
                    </option>
                </c:forEach>
            </select>
            <input value="推荐" type="submit"/>
        </div>
    </form>
</div>
<div class="table_con">
    <c:forEach items="${predictList}" var="predictObj">
        <img layer-pid="3012933" layer-src="${predictObj.url}" src="${predictObj.url}" alt="${predictObj.title}" width="100" height="150">
        <c:if test="${predictObj.id % 10 == 0}">
            <br/>
        </c:if>
    </c:forEach>
</div>

<script type="text/javascript">
    setRowColor();

    function setRowColor() {
        $("tr:even").css("background-color", "#eff3f5");
    }
</script>
</body>
</html>
