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
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/echarts-wordcloud.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/sentiment/category.css">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" type="text/css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/sentiment/Iframe.css"/>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div class="cp_title">相似度算法产出数据</div>
<div class="row" style="padding-left: 40%">
    <div class="input-group">
        <select id="user" style="width:110px" name="user">
            <c:forEach items="${userSet}" var="curr_user">
                <option value="${user}"
                        <c:if test="${curr_user == user}">
                            selected="selected"
                        </c:if>
                        style="width:100px">${curr_user}
                </option>
            </c:forEach>
        </select>
        <button class="btn btn-default" type="button" onclick="draw()">查询</button>
    </div>
</div>
<div id="collectWordCloudDom" style="width:100%;height:100%;"></div>
<div id="wishWordCloudDom" style="width:100%;height:100%;"></div>
<div id="doWordCloudDom" style="width:100%;height:100%;"></div>

<script type="text/javascript">

    function draw() {
        user = $("#user").find("option:selected").text();
        console.log(user)
        // 字符云
        var collectWordCloudChart = echarts.init(document.getElementById('collectWordCloudDom'));
        collectWordCloudChart.showLoading();
        $.get('/hybrid/similarDataStatus?user=' + user, function (cloudData) {
            collectWordCloudChart.hideLoading();
            collectWordCloudOption = {
                title: {
                    text: "用户偏好-读过",
                    subtext: 'Prefer-Collect',
                },
                tooltip: {},
                series: [{
                    type: 'wordCloud',
                    gridSize: 10,
                    sizeRange: [0, 20],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160)
                                ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    data: cloudData.simData.collect
                }]
            };
            collectWordCloudChart.setOption(collectWordCloudOption);
        });
        // 字符云
        var wishWordCloudChart = echarts.init(document.getElementById('wishWordCloudDom'));
        wishWordCloudChart.showLoading();
        $.get('/hybrid/similarDataStatus?user=' + user, function (cloudData) {
            wishWordCloudChart.hideLoading();
            wishWordCloudOption = {
                title: {
                    text: "用户偏好-想读",
                    subtext: 'Prefer-Collect',
                },
                tooltip: {},
                series: [{
                    type: 'wordCloud',
                    gridSize: 10,
                    sizeRange: [0, 20],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160)
                                ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            shadowBlur: 10,
                            shadowColor: '#333'
                        }
                    },
                    data: cloudData.simData.wish
                }]
            };
            wishWordCloudChart.setOption(wishWordCloudOption);
        });
        // 字符云
        var doWordCloudChart = echarts.init(document.getElementById('doWordCloudDom'));
        doWordCloudChart.showLoading();
        $.get('/hybrid/similarDataStatus?user=' + user, function (cloudData) {
            doWordCloudChart.hideLoading();
            doWordCloudOption = {
                title: {
                    text: "用户偏好-在读",
                    subtext: 'Prefer-Collect',
                },
                tooltip: {},
                series: [{
                    type: 'wordCloud',
                    gridSize: 10,
                    sizeRange: [0, 25],
                    rotationRange: [0, 0],
                    shape: 'circle',
                    textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160),
                                    Math.round(Math.random() * 160)
                                ].join(',') + ')';
                            }
                        },
                        emphasis: {
                            shadowBlur: 25,
                            shadowColor: '#333'
                        }
                    },
                    data: cloudData.simData.do
                }]
            };
            doWordCloudChart.setOption(doWordCloudOption);
        });
    }
</script>
</body>
</html>
