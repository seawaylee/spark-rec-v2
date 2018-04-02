<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="commons/taglibs.jsp" %>
<%@ include file="commons/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>Spark混合推荐</title>

    <link rel="stylesheet" href="${stc}/static/css/main.css" type="text/css" media="screen"/>

    <script type="text/javascript" src="${stc}/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="${stc}/static/js/tendina.min.js"></script>
    <script type="text/javascript" src="${stc}/static/js/AL-index.js"></script>
</head>
<body>

<div class="top">
    <div style="text-align:center;padding-top:25px;"><span>Spark混合推荐系统监控平台</span></div>
</div>

<!--菜单-->
<div class="left-menu">
    <ul id="menu">

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon jggl"></i>数据采集<span class="sz"></span></a>
            <ul>
                <li class="menu-list"><a href="${ctx}/spider/monitor/showSysStatus" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right1"></i>爬虫监控<span class="sz"></span></a></li>
                <li class="menu-list"><a href="" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right3"></i>数据分析<span class="sz"></span></a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon jggl"></i>情感分析<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/author/list" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>数据集分类<span class="sz"></span></a></li>
                <li><a href="${ctx}/organization/list" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>数据预处理<span class="sz"></span></a></li>
                <li><a href="${ctx}/organization/list" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>朴素贝叶斯分类<span class="sz"></span></a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>协同过滤推荐<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>训练数据集状态</a></li>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>构建推荐模型</a></li>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>推荐结果测试</a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>相似度算法<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>数据状态</a></li>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>数据计算状态</a></li>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>数据计算结果</a></li>
            </ul>
        </li>
        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>混合推荐集成<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>数据状态</a></li>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>数据</a></li>
                <li><a href="${ctx}/query/list" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>数据计算结果</a></li>
            </ul>
        </li>
    </ul>
</div>
<!--菜单右边的iframe页面-->
<div id="right-content" class="right-content">
    <div class="content">
        <div id="page_content">
            <iframe id="menuFrame" name="menuFrame"  style="overflow:visible;"
                    width="100%" height="100%" src="${ctx}/query/showRegionMap"></iframe>
        </div>
    </div>
</div>

</body>
</html>