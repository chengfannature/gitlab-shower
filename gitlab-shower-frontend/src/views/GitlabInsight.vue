<template>
    <div>
        <div class="form form-group">
            <Row>
                <Col span="12">
                    <label>开始时间：</label>
                    <DatePicker type="daterange" :options="options2" :value="values2" @on-change="timeChange"
                                format="yyyy-MM-dd"
                                placeholder="选择时间" style="width: 200px">
                    </DatePicker>
                </Col>
                <Col span="4" offset="8">
                    <Button type="default" style="text-align: right" v-on:click="queryCommit()">查询</Button>
                </Col>
            </Row>
        </div>
        <div class="form form-group">
            <Table :columns="columnDeveloper" :data="peoples" size="small" ref="table"></Table>
        </div>
        <div class="form form-group">
            <Card>
                <div id="main" style="width: 850px;height: 250px;"></div>
            </Card>
        </div>
        <div class="row" v-if="peoples.length!=0">
            <div class="col-md-6" v-for="people in peoples">
                <div class="card mb-4">
                    <Card>
                        <p slot="title">
                            <Icon type="ios-film-outline"></Icon>
                            {{people.userName}}
                        </p>
                        <a slot="extra">
                            <Icon type="ios-loop-strong"></Icon>
                            {{people.style}}
                        </a>
                        <div>
                            <a class="link-gray text-normal">{{people.commitCount}} commits </a>
                            <a class="text-normal" style="color: green">{{people.additionSum}}++ </a>
                            <a class="text-normal" style="color: red">{{people.deletionSum}}--</a>
                        </div>
                    </Card>
                    <div class="card-body">
                        <div :id="people.style" style="width: 400px;height: 200px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import {DatePicker, Button} from 'iview';

    export default {
        name: "gitlab-insight",
        components: {
            DatePicker, Button
        },
        data() {
            return {
                values2: [new Date(), new Date()],
                peoples: [],
                dataAxis: [],
                yMax: 500,
                dataShadow: [],
                base: +new Date(2010, 9, 3),
                oneDay: 24 * 3600 * 1000,
                date: [],
                data: [Math.random() * 300],
                startTime: '',
                endTime: '',
                columnDeveloper: [
                    {
                        "title": "研发",
                        "key": "userName",
                        "fixed": "left",
                        "width": 150
                    },
                    {
                        "title": "提交总次数",
                        "key": "commitCount",
                        "width": 200,
                        "sortable": true,
                    },
                    {
                        "title": "添加总行数",
                        "key": "additionSum",
                        "width": 200,
                        "sortable": true
                    }, {
                        "title": "删除总行数",
                        "key": "deletionSum",
                        "width": 200,
                        "sortable": true
                    }, {
                        "title": "评论条数",
                        "key": "noteCount",
                        "width": 300,
                        "sortable": true
                    }
                ],
                dataDeveloper: [],
                options2: {
                    shortcuts: [
                        {
                            text: '1 week',
                            value() {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                                return [start, end];
                            }
                        },
                        {
                            text: '1 month',
                            value() {
                                const end = new Date();
                                const start = new Date();
                                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                                return [start, end];
                            }
                        }
                    ]
                }
            }
        },
        ready: function () {
            this.getPeoples();
        },
        created() {
            this.getPeoples();
        },
        methods: {
            queryCommit() {
                this.$http.get("/contributions/byDate?" +
                    "orderBy=created_at&startTime=" + this.startTime + "&endTime=" + this.endTime).then(
                    function (contributions) {
                        this.peoples = contributions.body;
                    }).catch(e => {
                    e.toString();
                })
            },

            timeChange(w) {
                this.startTime = w[0];
                this.endTime = w[1];
            },
            getPeoples: function () {
                var vm = this;
                vm.$http.get("/contributions/history?orderBy=created_at").then(
                    function (contributions) {
                        this.peoples = contributions.body;
                        for (var i = 0; i <= this.peoples.length; i++) {
                            this.peoples[i].style = '#' + i;
                        }
                    }).catch(e => {
                    e.toString();
                })
                vm.$http.get
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
    .text-normal {
        font-weight: 400 !important;
        font-size: small;
    }

    .link-gray {
        color: #586069 !important;
    }

</style>