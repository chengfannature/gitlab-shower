<template>
    <div>
        <div id="main" style="width: 900px;height: 250px;"></div>
        <div class="row">
            <div class="card mb-4" v-for="style in styles">
                <div class="card mb-4">
                    <h3><a class="url">彭小刚</a>
                        <span class="f5 text-normal text-gray-light float-right">#4</span>
                        <div><a href="https://github.com/vuejs/vue/commits?author=kazupon"
                                class="link-gray text-normal">32 commits</a>
                        </div>
                    </h3>
                    <hr/>
                    <div class="card-body">
                        <div :id="style" style="width: 300px;height: 200px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import echarts from 'echarts'

    export default {
        name: "gitlab-insight",
        data() {
            return {
                peoples: [],
                dataAxis: [],
                yMax: 500,
                dataShadow: [],
                base: +new Date(2010, 9, 3),
                oneDay: 24 * 3600 * 1000,
                date: [],
                data: [Math.random() * 300],
                styles: ['main1', 'main2', 'main3']
            }
        },
        ready: function () {
            this.getPeoples();
        },
        methods: {
            getPeoples: function () {
                var vm = this;
                vm.$http.get("http://localhost:8081/api/gitlab/star").then(
                    function (commits) {
                        this.peoples = commits.body;
                        this.dataAxis = this.peoples;
                        this.lineChart('main');
                        this.lineChart('main1');
                        this.lineChart('main2');
                        this.lineChart('main3');
                    }).catch(e => {
                    e.toString();
                })
            },
            lineChart(id) {
                for (var i = 1; i < 4000; i++) {
                    let now = new Date(this.base += this.oneDay);
                    let date = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('-');
                    this.date.push(date);
                    this.data.push(Math.round((Math.random() - 0.5) * 20 + this.data[i - 1]));
                }
                this.charts = echarts.init(document.getElementById(id))
                this.charts.setOption({
                    tooltip: {
                        trigger: 'axis',
                        position: function (pt) {
                            return [pt[0], '10%'];
                        }
                    },
                    title: {
                        left: 'center',
                        text: '代码贡献图',
                    },
                    toolbox: {
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: this.date
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%']
                    },
                    dataZoom: [{
                        type: 'inside',
                        start: 0,
                        end: 20
                    }, {
                        start: 1000,
                        end: 1010,
                        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                        handleSize: '80%',
                        handleStyle: {
                            color: '#fff',
                            shadowBlur: 3,
                            shadowColor: 'rgba(0, 0, 0, 0.6)',
                            shadowOffsetX: 2,
                            shadowOffsetY: 2
                        }
                    }],
                    series: [
                        {
                            name: '提交总量',
                            type: 'line',
                            smooth: true,
                            symbol: 'none',
                            sampling: 'average',
                            itemStyle: {
                                color: 'rgb(255, 70, 131)'
                            },
                            areaStyle: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgba(52, 185, 86, 0.49)'
                                }, {
                                    offset: 1,
                                    color: 'rgba(52, 185, 86, 0.49)'
                                }])
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
            })
        }
    }
</script>

<style scoped>
    .test_demo_clip {
        text-overflow: clip;
        overflow: hidden;
        white-space: nowrap;
        width: 200px;
        background: #ccc;
    }

    .test_demo_ellipsis {
        text-overflow: ellipsis;
        overflow: hidden;
        white-space: nowrap;
        height: 100px;
        width: 200px;
        background: rgba(249, 249, 249, 0.01);
    }

    .text-normal {
        font-weight: 400 !important;
        font-size: small;
    }

    .link-gray {
        color: #586069 !important;
    }

</style>