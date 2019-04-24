### MyModulesDemo (Mvvm开发模式 + dataBinding学习和基本使用方法)
#### UI参考了《玩Android》相关的app，此用于学习Mvvm+dataBinding结合的使用（已接入WMRouter美团路由）
##### WMRouter 美团路由  https://github.com/meituan/WMRouter
##### app使用的接口均来自《玩Android》张鸿洋的Api：https://www.wanandroid.com/blog/show/2
###### 项目架构版本1.0

1.项目使用了Mvvm+dataBinding开发模式，其中存在优缺点：
       - 优点：
          - 双向绑定技术，当Model变化时，View-Model会自动更新，View也会自动变化。很好做到数据的一致性。所以 MVVM模式有些时候又被称作：model-view-binder模式。
          - 逻辑和UI层分工明确
          - 在布局中可以进行简单的表达式,减少java逻辑代码
          - dataBinding可以判断null值,不会报空指针
          - 自动检测空指针,避免crash
       - 缺点：
          - 数据绑定使得 Bug 很难被调试。你看到界面异常了，有可能是你 View 的代码有 Bug，也可能是 Model 的代码有问题。数据绑定使得一个位置的 Bug 被快速传递到别的位置，要定位原始出问题的地方就变得不那么容易了。
          - 一个大的模块中，model也会很大，虽然使用方便了也很容易保证了数据的一致性，当长期持有，不释放内存，就造成了花费更多的内存。
          - 数据双向绑定不利于代码重用。客户端开发最常用的重用是View，但是数据双向绑定技术，一个View都绑定了一个model，不同模块的model都不同，那就不能简单重用View了。