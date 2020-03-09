const path = require("path");

function resolve(dir) {
  return path.join(__dirname, dir);
}
module.exports = {
  transpileDependencies: ["vuetify"],

  devServer: {
    disableHostCheck: true
  },

  chainWebpack: config => {
    config.resolve.symlinks(true);
    config.plugins.delete("fork-ts-checker"); // 禁用fork-ts-checker
    config.resolve.alias
      .set("@", resolve("src")) // key,value自行定义，比如.set('@@', resolve('src/components'))
      .set("_c", resolve("src/components"));
    config.entry("index").add("babel-polyfill");
  },

  runtimeCompiler: true
};