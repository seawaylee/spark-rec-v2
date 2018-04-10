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
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
    <style>
        .g-sd2 {
            position: relative;
            float: right;
            width: 50%;
            height: 90%;
        }

        .g-mn2 {
            float: left;
            width: 50%;
            height: 90%;
        }
    </style>
</head>

<body>
<div class="g-sd2">
    <div class="cp_title">用户图书评价模型</div>
    <div id="_3DRatingDom" style="width: 100%;height: 100%;"></div>
</div>
<div class="g-mn2">
    <div class="cp_title">协同过滤推荐训练集</div>
    <div id="ratingDom" style="width: 100%;height: 100%;"></div>
</div>


<script type="text/javascript">
    draw();

    function draw() {
        <%-- 用户评分分布 --%>
        var ratingChart = echarts.init(document.getElementById('ratingDom'));
        $.post("/cf/getCfStatistic", function (ratingData) {
            var ratingOption = {
                title: {
                    text: 'User-Book-Rating',
                    subtext: 'Rating(1-5)',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                // legend: {
                //     orient: 'vertical',
                //     left: 'left',
                //     data: ratingData.xAxis
                // },
                series: [
                    {
                        name: '评分值',
                        type: 'pie',
                        radius: '80%',
                        center: ['50%', '60%'],
                        data: [
                            {value: ratingData.yAxis[1], name: ratingData.xAxis[1]},
                            {value: ratingData.yAxis[2], name: ratingData.xAxis[2]},
                            {value: ratingData.yAxis[3], name: ratingData.xAxis[3]},
                            {value: ratingData.yAxis[4], name: ratingData.xAxis[4]},
                            {value: ratingData.yAxis[5], name: ratingData.xAxis[5]}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            ;
            ratingChart.hideLoading();
            ratingChart.setOption(ratingOption);
        });

        // 3D评价模型

        var _3DChart = echarts.init(document.getElementById('_3DRatingDom'));

        var valMin = 5;
        var valMax = -1;

        function generateData(theta, min, max) {
            var data = [];
            $.ajax({
                url: '/cf/getUserRating',
                async: false,
                success: function (res) {
                    for (var i = 0; i < res.length; i++) {
                        obj = res[i]
                        var value = obj.rating
                        valMax = Math.max(valMax, value);
                        valMin = Math.min(valMin, value);
                        data.push([obj.userId, obj.bookId, value]);
                    }
                }
            });
            return data;
        }

        var data = generateData(2, -5, 5);
        console.log(valMin, valMax);

        _3DChart.setOption(option = {
            visualMap: {
                show: false,
                min: 2,
                max: 6,
                inRange: {
                    color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                }
            },
            xAxis3D: {
                type: 'value'
            },
            yAxis3D: {
                type: 'value'
            },
            zAxis3D: {
                type: 'value',
                max: 5,
                min: 0
            },
            grid3D: {
                axisLine: {
                    lineStyle: {color: '#000'}
                },
                axisPointer: {
                    lineStyle: {color: '#000'}
                },
                viewControl: {
                    autoRotate: true,
                    grid3D: true
                },
                light: {
                    main: {
                        shadow: true,
                        quality: 'ultra',
                        intensity: 1.5
                    }
                }
            },
            series: [{
                type: 'bar3D',
                data: data,
                shading: 'lambert',
                label: {
                    formatter: function (param) {
                        return param.value[2].toFixed(1);
                    }
                }
            }]
        });
    }

</script>
</body>
</html>
