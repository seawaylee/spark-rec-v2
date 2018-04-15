<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../commons/taglibs.jsp" %>
<%@ include file="../commons/meta.jsp" %>
<html>
<head>
    <title>数据状态</title>
    <script src="${ctx}/static/js/echarts.min.js"></script>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/sentiment/category.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/sentiment/Iframe.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" type="text/css" media="screen"/>
</head>
<body>

<script type="text/javascript">
    setRowColor();
</script>
<div class="cp_title">数据状态</div>
<div class="add_cp">
    <form action="${ctx}/similar/calcSimilar" method="POST">
        <div id="queryDiv">
            <select id="userA-select" style="width:110px" name="userA">
                <c:forEach items="${userSet}" var="user">
                    <option value="${user}"
                            <c:if test="${user == userA}">
                                selected="selected"
                            </c:if>
                            style="width:100px">${user}
                    </option>
                </c:forEach>
            </select>
            <select id="userB-select" style="width:110px" name="userB">
                <c:forEach items="${userSet}" var="user">
                    <option value="${user}"
                            <c:if test="${user == userB}">
                                selected="selected"
                            </c:if>
                            style="width:100px">${user}
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
            <td width="20%"><strong>user</strong></td>
            <td width="20%"><strong>collect-feature</strong></td>
            <td width="20%"><strong>do-feature</strong></td>
            <td width="20%"><strong>wish-feature</strong></td>
            <td width="20%"><strong>similar</strong></td>
        </tr>
        <tr>
            <td width="20%"><c:out value="${userA} - ${userB}"/></td>
            <td width="20%"><c:out value="${collectSim}"/></td>
            <td width="20%"><c:out value="${doSim}"/></td>
            <td width="20%"><c:out value="${wishSim}"/></td>
            <td width="20%"><c:out value="${similarRes}"/></td>
        </tr>
    </table>
</div>
</body>
</html>
