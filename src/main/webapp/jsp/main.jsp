<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="commons/taglibs.jsp" %>
<%@ include file="commons/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Spark混合推荐</title>

    <link rel="stylesheet" href="${ctx}/static/css/authorLibrary/main.css" type="text/css" media="screen"/>

    <script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/tendina.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/AL-index.js"></script>
</head>
<body>
<h1>Welcome ${user}!</h1>

<!--菜单-->
<div class="left-menu">
    <ul id="menu">

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon jggl"></i>最新发布<span class="sz"></span></a>
            <ul>
                <li class="menu-list"><a href="${ctx}/query/showRegionMap" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right3"></i>区域地图<span class="sz"></span></a></li>
                <li class="menu-list"><a href="${ctx}/author/getwelcome" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right3"></i>最新发码<span class="sz"></span></a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon jggl"></i>信息查询<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/author/list" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>作者信息查询<span class="sz"></span></a></li>
                <li><a href="${ctx}/organization/list" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>组织机构信息查询<span class="sz"></span></a></li>
            </ul>
        </li>


        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>查询与统计<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>综合查询</a></li>
                <li><a href="${ctx}/query/authorStatistic" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>按作者统计</a></li>
                <li><a href="${ctx}/query/webProductStatistic" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>按网站统计</a></li>

                <li><a href="${ctx}/query/showWebAuthorStatistic" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>网站签约作者信息</a></li>
                <li><a href="${ctx}/query/showAuthorWebStatistic" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>作者签约网站统计</a></li>
                <!--li><a href="${ctx}/query/list1" target="menuFrame"><i class="glyph-icon icon-chevron-right2"></i>统计分析</a></li-->
                <li><a href="${ctx}/query/showWebsiteList" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>网站配码统计</a></li>
                <li><a href="${ctx}/query/surveyList" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>网站基本情况</a></li>
                <li><a href="${ctx}/query/getWebsitestatisticList" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>网站数据统计</a></li>
                <li><a href="${ctx}/query/getProductstatistic" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>作品情况统计</a></li>
            </ul>
        </li>
    </ul>
</div>

</body>
</html>