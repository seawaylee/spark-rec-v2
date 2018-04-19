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
    <script src="http://echarts.baidu.com/asset/map/js/china.js"></script>
</head>
<body>
<div class="cp_title">用户-书籍评分比例</div>
<div id="ratingDom" style="width: 100%;height:100%;"></div>
<div class="cp_title">用户评论总量榜单</div>
<div id="userCommentCountSortDom" style="width: 100%;height:100%;"></div>
<div class="cp_title">用户所在地域分布</div>
<div id="userLocationDom" style="width: 100%;height:100%;margin: auto;"></div>


<script type="text/javascript">
    draw();

    function draw() {
        <%-- 用户评分分布 --%>
        var ratingChart = echarts.init(document.getElementById('ratingDom'));
        $.post("/spider/analysis/getRating", function (ratingData) {
            var ratingOption = {
                title: {
                    text: '用户书评分值比例',
                    subtext: '评分星级1-5  (-1表示有评论无评分)',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ratingData.xAxis
                },
                series: [
                    {
                        name: '评分值',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: ratingData.yAxis[0], name: ratingData.xAxis[0]},
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
            ratingChart.hideLoading();
            ratingChart.setOption(ratingOption);
        });

        // 用户评论数量排序
        var userCommentCountSortChart = echarts.init(document.getElementById('userCommentCountSortDom'));
        $.post("/spider/analysis/getUserCommentCountSort", function (resMap) {
            var userCommentCountSortOption = {
                title: {
                    text: '用户评论数量排序',
                    subtext: '取评论总量最高的10个用户',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: resMap.users
                },
                series: [
                    {
                        name: '书评总量',
                        type: 'pie',
                        selectedMode: 'single',
                        radius: [0, '30%'],

                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: resMap.commentCountStatisticResultInner
                    },
                    {
                        name: '书评总量',
                        type: 'pie',
                        radius: ['40%', '55%'],
                        label: {
                            normal: {
                                formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                                backgroundColor: '#eee',
                                borderColor: '#aaa',
                                borderWidth: 1,
                                borderRadius: 4,
                                rich: {
                                    a: {
                                        color: '#999',
                                        lineHeight: 22,
                                        align: 'center'
                                    },
                                    hr: {
                                        borderColor: '#aaa',
                                        width: '100%',
                                        borderWidth: 0.5,
                                        height: 0
                                    },
                                    b: {
                                        fontSize: 16,
                                        lineHeight: 33
                                    },
                                    per: {
                                        color: '#eee',
                                        backgroundColor: '#334455',
                                        padding: [2, 4],
                                        borderRadius: 2
                                    }
                                }
                            }
                        },
                        data: resMap.commentCountStatisticResultOuter
                    }
                ]
            };
            userCommentCountSortChart.hideLoading();
            userCommentCountSortChart.setOption(userCommentCountSortOption);
        });

        // 用户所在地
        var userLocationChart = echarts.init(document.getElementById('userLocationDom'));
        $.post("/spider/analysis/getLocationStatus", function (locationData) {
            console.log(locationData)
            var userLocationOption = {
                    title: {
                        text: '用户所在地',
                        subtext: '豆瓣读书论坛',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    visualMap: {
                        min: 0,
                        max: 20000,
                        left: 'left',
                        top: 'bottom',

                        text: ['高', '低'],           // 文本，默认为数值文本
                        calculable: true
                    },
                    toolbox:
                        {
                            show: true,
                            orient:
                                'vertical',
                            left:
                                'right',
                            top:
                                'center',
                            feature:
                                {
                                    dataView: {
                                        readOnly: false
                                    }
                                    ,
                                    restore: {}
                                    ,
                                    saveAsImage: {}
                                }
                        }
                    ,
                    series: [
                        {
                            name: '所在地',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            width: 800,
                            height: 600,
                            zoom: 1,
                            label: {
                                normal: {
                                    show: true
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: locationData,
                        }
                    ]
                }
            ;
            userLocationChart.hideLoading();
            userLocationChart.setOption(userLocationOption);
            userLocationChart.resize();
        });
    }
</script>
</body>
</html>
