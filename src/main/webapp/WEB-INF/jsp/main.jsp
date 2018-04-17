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

    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript" src="${stc}/static/js/tendina.min.js"></script>
    <script type="text/javascript" src="${stc}/static/js/AL-index.js"></script>
</head>
<body>

<div class="top">
    <div style="text-align:center;padding-top:15px;"><span>Spark混合推荐系统监控平台</span></div>
</div>

<!--菜单-->
<div class="left-menu">
    <ul id="menu">

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon jggl"></i>数据采集<span class="sz"></span></a>
            <ul>
                <li class="menu-list"><a href="${ctx}/spider/monitor/showSysStatus" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right1"></i>爬虫监控<span class="sz"></span></a></li>
                <li class="menu-list"><a href="${ctx}/spider/monitor/showDataAnalysis" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right3"></i>数据分析<span class="sz"></span></a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon jggl"></i>情感分析<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/sentiment/getComments" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>语料数据源<span class="sz"></span></a></li>
                <li><a href="${ctx}/sentiment/getPrepareData" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>提取极性情感语料<span class="sz"></span></a></li>
                <li><a href="${ctx}/sentiment/getVocabData" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>构造情感词典<span class="sz"></span></a></li>
                <li><a href="${ctx}/sentiment/getBayesRes" target="menuFrame" class="firsta"><i class="glyph-icon icon-chevron-right2"></i>朴素贝叶斯分类<span class="sz"></span></a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>协同过滤推荐<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/cf/showDataStatus" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>用户-图书关系模型</a></li>
                <li><a href="${ctx}/cf/showCfData" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>训练集数据统计</a></li>
                <li><a href="${ctx}/cf/getRatingData" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>训练集数据表单</a></li>
                <li><a href="${ctx}/cf/getRecommend" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>CF模型测试</a></li>
            </ul>
        </li>

        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>相似度算法<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/similar/showData" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>原始数据集</a></li>
                <li><a href="${ctx}/similar/showCalcSimilar" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>相似度计算</a></li>
                <li><a href="${ctx}/similar/getSimilarResData?getSimilarResData?pageNum=1" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>相似度计算结果</a></li>
            </ul>
        </li>
        <li class="menu-list">
            <a target="menuFrame" class="firsta"><i class="glyph-icon zzgl"></i>混合推荐集成<span class="sz"></span></a>
            <ul>
                <li><a href="${ctx}/hybrid/showSimilarDataStatus" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>相似度产出数据状态</a></li>
                <li><a href="${ctx}/hybrid/cfDataStatus" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>协同过滤产出数据状态</a></li>
                <li><a href="${ctx}/hybrid/getRecommend" target="menuFrame"><i class="glyph-icon icon-chevron-right1"></i>混合推荐测试</a></li>
            </ul>
        </li>
    </ul>
</div>
<!--菜单右边的iframe页面-->
<div id="right-content" class="right-content">
    <div class="content">
        <div id="page_content">
            <iframe id="menuFrame" name="menuFrame" style="overflow:visible;"
                    width="100%" height="100%" src="${ctx}/query/showRegionMap"></iframe>
        </div>
    </div>
</div>

</body>
</html>