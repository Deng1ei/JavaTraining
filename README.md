

# Order验证







![image-20201223093020751](https://github.com/Deng1ei/JavaTraining/blob/master/img/1.png)

为了验证Order数值对日志的影响，我设置了三个不同Order的切面同时打印日志到控制台，可以看到，Order数值越小，优先级越高，Order小的前置通知最先打出，返回通知和后置通知最后打出，将Order数值大的日志包裹在里面。



![image-20201223093348269](https://github.com/Deng1ei/JavaTraining/blob/master/img/2.png)

查看Order的接口类可以得到验证

低的Order数值MIN_VALUE对应高优先级HIGHEST_PRECEDENCE

高的Order数值MAX_VALUE对应低优先级LOWEST_PRECEDENCE







# 切点（pointcut）表达式

######  切点：切点就是我们我们配置的满足我们条件的目标方法。比如我们规定：名字前面是select开头的才执行我们自定义的通知方法。那么这些select开头的方法就是切点。

###### 切入点表达式：切入点表达式是用来描述切点的，用来指定你想要的切点

![image-20201223112015760](https://github.com/Deng1ei/JavaTraining/blob/master/img/4.png)



### 切入点指示符

​     **execution：**用于匹配符合的方法；

​     **within：**用于匹配指定的类及其子类中的所有方法。

​     **this：**匹配可以向上转型为this指定的类型的代理对象中的所有方法。

​     **target：**匹配可以向上转型为target指定的类型的目标对象中的所有方法。

​     **args：**用于匹配运行时传入的参数列表的类型为指定的参数列表类型的方法；

​     **@annotation：**用于匹配持有指定注解的方法；

​     **@within：**用于匹配持有指定注解的类的所有方法；

​     **@target：**用于匹配持有指定注解的目标对象的所有方法；

​     **@args：**用于匹配运行时 传入的参数列表的类型持有 注解列表对应的注解 的方法；

### 语法



![image-20201223094234735](https://github.com/Deng1ei/JavaTraining/blob/master/img/3.png)



## 1.静态切入点

静态切入点只限于给定的方法和目标类，而不考虑方法 的参数。Spring在调用静态切入点时只在第一次的时候计算静态切入点的位置，然后把它缓存起来，以后就不需要再进行计算。



## 2.动态切入点

动态切入点与静态切入点的区别是，它不仅限定于给点的方法和类，动态切入点还可以指定方法的参数。因为参数的变化性，所以动态切入点不能缓存，需要每次调用的时候都进行计算，因此使用动态切入点有很大的性能损耗。

当切入点需要在执行时根据参数值来调用通知时，就需要使用动态切入点。
