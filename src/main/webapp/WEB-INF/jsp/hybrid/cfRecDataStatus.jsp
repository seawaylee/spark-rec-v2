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
<div class="cp_title">CF预测得分榜单</div>
<div class="add_cp">
    <form action="${ctx}/hybrid/cfRecDataStatus" method="get">
        <div id="queryDiv">
            <select name="userno" style="width:110px">
                <c:forEach items="${userSet}" var="curr_user">
                    <option value="${curr_user}"
                            <c:if test="${curr_user == user}">
                                selected="selected"
                            </c:if>
                            style="width:100px">${curr_user}
                    </option>
                </c:forEach>
            </select>
            <input value="查询" type="submit"/>
        </div>
    </form>
</div>
<div class="table_con">
    <table>
        <tr class="tb_title">
            <td width="20%"><strong>Id</strong></td>
            <td width="50%"><strong>书名</strong></td>
            <td width="30%"><strong>预测得分</strong></td>
        </tr>
        <c:forEach items="${predictList}" var="predictObj">
            <tr>
                <td width="20%"><c:out value="${predictObj.bookId}"/></td>
                <td width="50%"><c:out value="${predictObj.title}"/></td>
                <td width="30%"><c:out value="${predictObj.score}"/></td>
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
        window.location = "${ctx}/hybrid/cfRecDataStatus?pageNum=" + (currentPage - 1) + "&userno=" + '${user}';
    }

    function nextPage(currentPage) {
        window.location = "${ctx}/hybrid/cfRecDataStatus?pageNum=" + (currentPage + 1) + "&userno=" + '${user}';
    }
</script>
</body>
</html>
