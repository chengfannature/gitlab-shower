<template>
    <div><h2>new info component</h2>
        <!--<button @click="getPeoples()">获取人口</button>-->
        <b-alert show>Default Alert</b-alert>

        <b-alert variant="success" show>Success Alert</b-alert>
        <div>人口数：{{this.peoples}}</div>
        <div>
            <table class="table table-striped">
                <tr>
                    <th>提交人</th>
                </tr>
                <tr v-for=" people in this.peoples">
                    <td>{{people}}</td>
                </tr>
            </table>
        </div>
        <div id="main2" style="width: 600px;height: 400px;"></div>
    </div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        data() {
            return {
                peoples: [],
                charts: '',
                opinion: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎'],
                opinionData: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ],
                dataAxis: [],
                data: [220, 182, 191, 123],
                yMax: 500,
                dataShadow: []
            }
        },

        name: "info",
        ready: function () {
            this.getPeoples();
        },
        methods: {
            getPeoples: function () {
                var vm = this;
                vm.$http.get("http://localhost:8081/api/gitlab/star").then(
                    function (data1) {
                        this.peoples = data1.body;
                        this.dataAxis = this.peoples;
                        this.barChart('main2');
                    }).catch(e => {
                    e.toString();
                })
            },
            lineChart(id) {
                this.charts = echarts.init(document.getElementById(id))
                this.charts.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a}<br/>{b}:{c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: this.opinion
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'blod'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data: this.opinionData
                        }
                    ]
                })
            },
            barChart(id) {
                this.chart2 = echarts.init(document.getElementById(id));
                this.chart2.setOption({
                    title: {
                        text: '特性示例：渐变色 阴影 点击缩放',
                        subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
                    },
                    xAxis: {
                        data: this.dataAxis,
                        axisLabel: {
                            inside: true,
                            textStyle: {
                                color: '#fff'
                            }
                        },
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false
                        },
                        z: 10
                    },
                    yAxis: {
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            textStyle: {
                                color: '#999'
                            }
                        }
                    },
                    dataZoom: [
                        {
                            type: 'inside'
                        }
                    ],
                    series: [
                        { // For shadow
                            type: 'bar',
                            itemStyle: {
                                normal: {color: 'rgba(0,0,0,0.05)'}
                            },
                            barGap: '-100%',
                            barCategoryGap: '40%',
                            data: this.dataShadow,
                            animation: false
                        },
                        {
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#83bff6'},
                                            {offset: 0.5, color: '#188df0'},
                                            {offset: 1, color: '#188df0'}
                                        ]
                                    )
                                },
                                emphasis: {
                                    color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#2378f7'},
                                            {offset: 0.7, color: '#2378f7'},
                                            {offset: 1, color: '#83bff6'}
                                        ]
                                    )
                                }
                            },
                            data: this.data
                        }
                    ]
                })
            }

        },
        mounted() {
            this.$nextTick(function () {
                this.getPeoples();
                this.lineChart('main');
            })
        }
    }
</script>

<style scoped>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }

</style>