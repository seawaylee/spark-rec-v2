<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../commons/taglibs.jsp" %>
<%@ include file="../commons/meta.jsp" %>
<html>
<head>
    <title>爬虫状态监控</title>
    <script src="${ctx}/static/js/echarts.min.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<table>
    <tr>
        <td>
            <div id="netDelayDom" style="width: 600px;height:400px;"></div>
        </td>
        <td rowspan="2">
            <div id="bookCountDom" style="width: 1100px;height:800px;"></div>
        </td>
    </tr>
    <tr>
        <td>
            <div id="jvmStatDom" style="width: 600px;height:400px;"></div>
        </td>
    </tr>
</table>


<script type="text/javascript">
    <%-- 网络延迟监控 --%>
    var netDelayChart = echarts.init(document.getElementById('netDelayDom'));
    var netDelayOption = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        toolbox: {
            feature: {
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '网络延迟',
                type: 'gauge',
                detail: {formatter: '{value}ms'},
                data: [{value: 50, name: '网络延迟'}]
            }
        ]
    };
    <%-- JVM堆内存监控 --%>
    var jvmStatChart = echarts.init(document.getElementById('jvmStatDom'));
    var jvmStatOption = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        toolbox: {
            feature: {
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: 'JVM-Heap占用',
                type: 'gauge',
                detail: {formatter: '{value}%'},
                data: [{value: 50, name: 'JVM-Heap使用率'}]
            }
        ]
    };
    <%-- 书籍数量监控 --%>
    var bookCountChart = echarts.init(document.getElementById('bookCountDom'));
    var bookCountOption = {
        title: {
            text: '数据实时抓取量',
            subtext: '书籍&书评'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data: ['书籍数量', '书评数量']
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: {
            show: false,
            start: 0,
            end: 100
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/, ''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
            {
                type: 'category',
                boundaryGap: true,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push(len + 1);
                    }
                    return res;
                })()
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '书籍总数',
                max: 100000,
                min: 0,
                boundaryGap: [0.2, 0.2]
            },
            {
                type: 'value',
                scale: true,
                name: '书评总数',
                max: 10000000,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name: '书籍数量',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: (function () {
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push(0);
                    }
                    return res;
                })()
            },
            {
                name: '书评数量',
                type: 'line',
                data: (function () {
                    var res = [];
                    var len = 0;
                    while (len < 10) {
                        res.push(0);
                        len++;
                    }
                    return res;
                })()
            }
        ]
    };

    initCount = 11;
    setInterval(function () {
        axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');
        var data0 = bookCountOption.series[0].data;
        var data1 = bookCountOption.series[1].data;
        $.ajax({
            url: '/spider/monitor/getAmount',
            success: function (data) {
                console.log(data)
                data0.shift();
                data0.push(data.comment);
                data1.shift();
                data1.push(data.book);
            }
        });

        bookCountOption.xAxis[0].data.shift();
        bookCountOption.xAxis[0].data.push(axisData);
        bookCountOption.xAxis[1].data.shift();
        bookCountOption.xAxis[1].data.push(initCount++);

        bookCountChart.setOption(bookCountOption);
    }, 2100);

    // 定时系统监控刷新数据
    setInterval(function () {
        $.ajax({
            url: '/spider/monitor/getSysStatus',
            success: function (data) {
                console.log(data)
                netDelayOption.series[0].data[0].value = data.delay;
                jvmStatOption.series[0].data[0].value = (data.usedHeap / data.maxHeap * 100).toFixed(2);
            }
        });
        netDelayChart.setOption(netDelayOption, true);
        jvmStatChart.setOption(jvmStatOption, true);
    }, 2000);

    // 定时数据抓取量
    // setInterval(function () {
    //     $.ajax({
    //         url: '/spider/monitor/getAmount',
    //         success: function (data) {
    //             console.log(data)
    //             netDelayOption.series[0].data[0].value = data.delay;
    //             jvmStatOption.series[0].data[0].value = (data.usedHeap / data.maxHeap * 100).toFixed(2);
    //         }
    //     });
    //     netDelayChart.setOption(netDelayOption, true);
    //     jvmStatChart.setOption(jvmStatOption, true);
    // }, 2000);
</script>
</body>
</html>
