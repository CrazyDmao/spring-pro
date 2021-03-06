# app_springbootclient

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

##<<搭建环境>>
``` bash
1、搭建node环境
2、安装淘宝镜像安装相关依赖：npm install -g cnpm --registry=http://registry.npm.taobao.org
3、安装vue-cli脚手架构建工具:npm install -g vue-cli / cnpm i -g vue-cli  [vue -v查看vue的版本]
``` 

##<<使用vue-cli创建项目>>
``` bash
1、选择项目所在的位置，通过命令行进入该目录（或者直接在该目录，右键，打开命令行）。
2、使用脚手架安装项目： vue init webpack demo 项目是基于webpack的
3、Project name（工程名）:回车
4、Project description（工程介绍）：回车
5、Author：作者名
6、Vue build（是否安装编译器）:回车
7、Install vue-router（是否安装Vue路由）：回车
8、Use ESLint to lint your code（是否使用ESLint检查js代码）：n
9、Set up unit tests（安装单元测试工具）：n
10、Setup e2e tests with Nightwatch（是否安装端到端测试工具）：n
11、Should we run npm install for you after the project has been created? (recommended)：回车。
```

##<<启动项目>>
``` bash
1、进入项目目录：cd demo
2、安装项目所需要的依赖：npm install
3、启动项目：npm run dev
```

##<<安装异常解决办法>>
``` 
Unexpected end of JSON input while parsing near '...,"version":"0.5.1","d'
1、使用如下指令，清除npm编译的一些缓存 ：npm cache clean --force
2、重新执行安装依赖命令：npm install
3、如果不行：换个镜像重来（npm set registry https://registry.npmjs.org/）

```

##<<其他>>
``` bash
vue-cli安装: https://cn.vuejs.org/v2/guide/installation.html
npm换源: http://www.jianshu.com/p/0deb70e6f395
webpack配置: https://webpack.js.org/configuration/
axios文档: https://github.com/mzabriskie/axios
vue-router文档: https://router.vuejs.org/zh-cn/
vuex文档: https://vuex.vuejs.org/zh-cn/
element-ui文档: http://element.eleme.io/#/zh-CN/component/quickstart
mint-ui文档: http://mint-ui.github.io/docs/#!/zh-cn2
ssh2文档: https://github.com/mscdex/ssh2
ssh2方法封装: http://blog.csdn.net/llmys/article/details/52860816?locationNum=2&fps=1
js-cookie文档: https://github.com/js-cookie/js-cookie
```
created:html加载完成之前，执行。执行顺序：父组件-子组件

mounted:html加载完成后执行。执行顺序：子组件-父组件

methods：事件方法执行

watch：watch是去监听一个值的变化，然后执行相对应的函数。

computed：computed是计算属性，也就是依赖其它的属性计算所得出最后的值

activated：在vue对象存活的情况下，进入当前存在activated()函数的页面时，一进入页面就触发；可用于初始化页面数据等
beforeUpdate()
updated()

父组件给子组件传值（props和$attrs）
和父组件触发子组件的事件（$emit）

父组调用子组件函数 this.$refs.pagetable.refData(this.param);//调用子组件的函数进行传值
用法： 子组件上定义ref="refName",  父组件的方法中用 this.$refs.refName.method 去调用子组件方法
详解： 父组件里面调用子组件的函数，父组件先把函数/方法以属性形式传给子组件；那么就需要先找到子组件对象 ，即  this.$refs.refName.
然后再进行调用，也就是 this.$refs.refName.method
