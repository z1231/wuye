const path = require('path')

function resolve (dir) {
  return path.join(__dirname, dir)
}

module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:4000',
        changOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
  configureWebpack: {
    resolve: {
      alias: {
        '@': resolve('src'),
        'public': resolve('public'),
        'components': resolve('src/components'),
        'utils': resolve('src/utils'),
        'store': resolve('src/store'),
        'router': resolve('src/router')
      }
    }
  }
}