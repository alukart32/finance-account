module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8082',
                ws: true,
                changeOrigin: true
            }
        },
        proxy: {
            '/person': {
                target: 'http://localhost:8082',
                ws: true,
                changeOrigin: true
            }
        }
    }
};