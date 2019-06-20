module.exports = { // 模块导出
    devServer: { // 开发服务器

        host: '127.0.0.1',

        port: 8082,

        https: false,

        hotOnly: false,

        proxy: {   // 代理
            '/': {
                target: 'http://127.0.0.1:8080',  // 代理路径
                changeOrigin: true, // 是否跨域
                pathRewrite: {
                    '^': '/',//重写,
                },
                 ws:false   // 全双工的系统可以用一般的双向车道形容。两个方向的车辆因使用不同的车道，因此不会互相影响。
            }
        }
    }
}