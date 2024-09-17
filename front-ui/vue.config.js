const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'https://fanyi-api.baidu.com', // 目标 API 服务器的地址
        changeOrigin: true,
        pathRewrite: { '^/api': '' }, // 重写路径，去掉 `/api` 前缀
      },
    },
  },
});
