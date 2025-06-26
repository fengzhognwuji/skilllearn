# Java核心知识演示项目

## 包含的演示内容

### 1. 基础语法 (Main.java)

- 变量声明与初始化
- 控制流语句
- 方法定义与调用

运行命令:
javac java/Main.java
java -cp java Main

### 2. 集合框架 (CollectionsDemo.java)

- ArrayList/HashMap操作
- Collections工具类
- 集合遍历

运行命令:
javac java/CollectionsDemo.java
java -cp java CollectionsDemo

### 3. 类型转换 (TypeConversionDemo.java)

- 基本类型转换
- 字符串与数值转换
- 集合与数组转换

运行命令:
javac java/TypeConversionDemo.java
java -cp java TypeConversionDemo

### 4. 类加载机制 (ClassLoaderDemo.java)

- 类加载器层次
- 动态类加载
- 自定义类加载器

运行命令:
javac java/ClassLoaderDemo.java
java -cp java ClassLoaderDemo

### 5. 多线程 (ThreadDemo.java)

- 线程创建方式
- 线程池配置
- 线程同步

运行命令:
javac java/ThreadDemo.java
java -cp java ThreadDemo

### 6. 锁机制 (LockDemo.java)

- synchronized
- ReentrantLock
- ReadWriteLock
- StampedLock

运行命令:
javac java/LockDemo.java
java -cp java LockDemo

### 7. 线程池详解 (ThreadPoolDetailDemo.java)

- 线程池参数配置
- 拒绝策略
- 状态监控

运行命令:
javac java/ThreadPoolDetailDemo.java
java -cp java ThreadPoolDetailDemo

## 项目结构

java/
├── Main.java
├── CollectionsDemo.java
├── TypeConversionDemo.java
├── ClassLoaderDemo.java
├── ThreadDemo.java
├── LockDemo.java
└── ThreadPoolDetailDemo.java


## 快速运行所有演示

```bash
# 编译所有Java文件
javac java/*.java

# 运行指定演示
java -cp java Main
java -cp java CollectionsDemo
java -cp java TypeConversionDemo
java -cp java ClassLoaderDemo
java -cp java ThreadDemo
java -cp java LockDemo
java -cp java ThreadPoolDetailDemo
```
