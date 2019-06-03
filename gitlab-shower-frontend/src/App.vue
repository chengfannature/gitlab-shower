<style scoped>
    .layout {
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }

    .layout-logo {
        width: 100px;
        height: 30px;
        background: #5b6270;
        border-radius: 3px;
        float: left;
        position: relative;
        top: 15px;
        left: 20px;
    }

    .layout-desc {
        height: 30px;
        float: left;
        top: 15px;
        left: 20px;
    }

    .layout-nav {
        width: 200px;
        margin: 0 auto;
        margin-right: 20px;
    }

    .layout-footer-center {
        text-align: center;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <Header>
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-desc">
                        <MenuItem name="1">
                            <Icon type="ios-navigate"></Icon>
                            武汉绿网
                        </MenuItem>
                    </div>
                    <div class="layout-nav">
                        <MenuItem name="8">
                            <Icon type="ios-keypad"></Icon>
                            打星评分
                        </MenuItem>
                    </div>
                </Menu>
            </Header>
            <Layout :style="{padding: '0 50px'}">
                <Breadcrumb :style="{margin: '16px 0'}">
                    <BreadcrumbItem>Home</BreadcrumbItem>
                    <BreadcrumbItem>Components</BreadcrumbItem>
                    <BreadcrumbItem>Layout</BreadcrumbItem>
                </Breadcrumb>
                <Content :style="{padding: '24px 0', minHeight: '280px', background: '#fff'}">
                    <Layout>
                        <Sider hide-trigger :style="{background: '#fff'}">
                            <Menu active-name="1-2" theme="light" width="auto" :open-names="['1']">
                                <Submenu name="8">
                                    <template slot="title">
                                        <Icon type="ios-analytics"></Icon>
                                        打分
                                    </template>
                                    <MenuItem name="3-1">
                                        <a href="#">
                                            <router-link to="/gitlab-insight">打星评分</router-link>
                                        </a>
                                    </MenuItem>
                                    <MenuItem name="3-2">Option 2</MenuItem>
                                </Submenu>

                            </Menu>
                        </Sider>
                        <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                                <h1 class="h2">NISP Dashboard</h1>
                                <div class="btn-toolbar mb-2 mb-md-0">
                                    <div class="btn-group mr-2">
                                        <button class="btn btn-sm btn-outline-secondary">Share</button>
                                        <button class="btn btn-sm btn-outline-secondary">Export</button>
                                    </div>
                                    <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                                        <span data-feather="calendar"></span>
                                        This week
                                    </button>
                                </div>
                            </div>

                            <router-view/>
                            <canvas class="my-4" id="myChart" width="900" height="380"></canvas>

                        </Content>
                    </Layout>
                </Content>
            </Layout>
            <Footer class="layout-footer-center">2011-2016 &copy; TalkingData</Footer>
        </Layout>
    </div>
</template>
<script>
    export default {}
</script>