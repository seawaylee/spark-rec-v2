<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../commons/taglibs.jsp" %>
<%@ include file="../commons/meta.jsp" %>
<html>
<head>
    <title>爬虫状态监控</title>
    <script src="${ctx}/static/js/echarts.min.js"></script>
    <script src="${ctx}/static/js/jquery.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<table>
    <tr>
        <td>
            <div id="ratingDom" style="width: 600px;height:400px;"></div>
        </td>
</table>


<script type="text/javascript">
    draw();

    function draw() {
        <%-- 用户评分分布 --%>
        var ratingChart = echarts.init(document.getElementById('ratingDom'));
        $.post("/spider/analysis/getRating", function (ratingData) {
            var ratingOption = {
                title : {
                    text: '用户书评分值比例',
                    subtext: '评分星级1-5  (-1表示有评论无评分)',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ratingData.xAxis
                },
                series : [
                    {
                        name: '评分值',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
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
            ;
            ratingChart.hideLoading();
            ratingChart.setOption(ratingOption);
        });
    }
</script>
</body>
</html>
