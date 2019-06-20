<template>
    <div>
        <div class="form form-group">
            <Row>
                <Col span="12">
                    <label>开始时间：</label>
                    <DatePicker type="daterange" :options="options2" :value="dateValue" @on-change="timeChange"
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
        <div class="form form-group" v-if="commitStatics.length!=0">
            <div class="card mb-4">
                <Card>
                    <div>
                        <a class="link-gray text-normal">{{commitStatics.contributionDto.commitCount}} commits </a>
                        <a class="text-normal" style="color: green">{{commitStatics.contributionDto.additionSum}}++ </a>
                        <a class="text-normal" style="color: red">{{commitStatics.contributionDto.deletionSum}}--</a>
                    </div>
                </Card>
                <div class="card-body">
                    <div id="main" style="width: 850px;height: 250px;"></div>
                </div>
            </div>
        </div>
        <div class="row" v-if="peoples2.length!=0">
            <div class="col-md-6" v-for="people in peoples2">
                <div class="card mb-4">
                    <Card>
                        <p slot="title">
                            <Icon type="ios-film-outline"></Icon>
                            {{people.contributionDto.userName}}
                        </p>
                        <a slot="extra">
                            <Icon type="ios-loop-strong"></Icon>
                            {{people.style}}
                        </a>
                        <div>
                            <a class="link-gray text-normal">{{people.contributionDto.commitCount}} commits </a>
                            <a class="text-normal" style="color: green">{{people.contributionDto.additionSum}}++ </a>
                            <a class="text-normal" style="color: red">{{people.contributionDto.deletionSum}}--</a>
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
                dateValue: [new Date(), new Date()],
                peoples: [],
                peoples2: [],
                commitStatics: [],
                dataAxis: [],
                yMax: 500,
                dataShadow: [],
                oneDay: 24 * 3600 * 1000,
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
        beforeCreated() {
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
                    }).catch(e => {
                    e.toString();
                });
                vm.$http.get("/commitStatistic/all").then(
                    function (contributions) {
                        this.peoples2 = contributions.body;
                        for (let i = 0; i <= this.peoples2.length; i++) {
                            this.peoples2[i].style = '#' + i;
                        }
                    }).catch(e => {
                    e.toString();
                });
                vm.$http.get("/commitStatistic").then(
                    function (contributions) {
                        this.commitStatics = contributions.body;
                    }).catch(e => {
                    e.toString();
                });
            },
            lineChart(id, commitStatics) {
                let date = [];
                let data = [];
                let base = +new Date(2019, 3, 2);
                for (let i = 1; i < 1000; i++) {
                    let now = new Date(base += this.oneDay);
                    let tmpData = 0;
                    let tmpDate = [now.getFullYear(), now.getMonth() < 10 ? "0" + now.getMonth() : now.getMonth(),
                        now.getDate() > 9 ? now.getDate() : "0" + now.getDate()].join('-');
                    if (commitStatics != null) {
                        for (let j = 0; j < commitStatics.length; j++) {
                            if (tmpDate === commitStatics[j].createAt) {
                                tmpData = commitStatics[j].commitCount;
                                break;
                            }
                        }
                    }
                    date.push(tmpDate);
                    data.push(tmpData);
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
                        data: date
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
                        start: 0,
                        end: 10,
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
                            data: data
                        }
                    ]
                })
            }
        },
        updated() {
            if (this.commitStatics !== []) {
                this.lineChart('main', this.commitStatics.commitStatisticDto);
            }
            for (var i = 0; i < this.peoples2.length; i++) {
                this.lineChart('#' + i, this.peoples2[i].commitStatisticDto);
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