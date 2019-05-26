<template>
    <div>
        <div id="main" style="width: 900px;height: 250px;"></div>
        <div class="row" v-if="peoples.length!=0">
            <div class="col-md-6" v-for="people in peoples">
                <div class="card mb-4">
                    <div class="card mb-4">
                        <h4><a class="url">{{people.username}}</a>
                            <span class="f5 text-normal text-gray-light float-right">{{people.style}}</span>
                            <div><a href="https://github.com/vuejs/vue/commits?author=kazupon"
                                    class="link-gray text-normal">{{people.commitCount}} commits   </a>
                                <a class="text-normal" style="color: green">{{people.additionSum}}++  </a>
                                <a class="text-normal" style="color: red">{{people.deletionSum}}--</a>
                            </div>
                        </h4>
                        <hr/>
                        <div class="card-body">
                            <div :id="people.style" style="width: 400px;height: 200px;"></div>
                        </div>
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
        beforeCreate() {
        },
        created() {
            this.getPeoples();
        },
        methods: {

            getPeoples: function () {
                var vm = this;
                vm.$http.get("http://localhost:8081/api/gitlab/star").then(
                    function (contributions) {
                        this.peoples = contributions.body;
                        // this.peoples = contributions.body;
                        this.peoples = [{
                            id: '1',
                            username: 'pengxg',
                            email: 'pengxg@greenet.net.cn',
                            commitCount: 20,
                            additionSum: 60,
                            deletionSum: 20
                        },
                            {
                                id: '2',
                                username: 'chengfan',
                                email: 'chengfan@greenet.net.cn',
                                commitCount: 30,
                                additionSum: 72,
                                deletionSum: 23
                            },
                            {
                                id: '3',
                                username: 'leigang',
                                email: 'leigang@greenet.net.cn',
                                commitCount: 10,
                                additionSum: 22,
                                deletionSum: 12
                            },
                            {
                                id: '4',
                                username: 'caojm',
                                email: 'caojm@greenet.net.cn',
                                commitCount: 35,
                                additionSum: 87,
                                deletionSum: 28
                            }];
                        for (var i = 0; i <= this.peoples.length; i++) {
                            this.peoples[i].style = '#' + i;
                        }

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
                this.charts = echarts.init(document.getElementById(id));
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
                this.lineChart('main');
            })
        },
        updated() {
            for (var i = 0; i <= this.peoples.length; i++) {
                this.lineChart('#' + i);
            }
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