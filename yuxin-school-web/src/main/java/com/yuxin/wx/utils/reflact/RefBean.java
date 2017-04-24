package com.yuxin.wx.utils.reflact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
  *关于注解：
  *注解@Retention可以用来修饰注解，是注解的注解，称为元注解。
  *Retention注解有一个属性value，是RetentionPolicy类型的，Enum RetentionPolicy是一个枚举类型，
  *这个枚举决定了Retention注解应该如何去保持，也可理解为Rentention 搭配 RententionPolicy使用。
  *RetentionPolicy有3个值：CLASS  RUNTIME   SOURCE
  *用@Retention(RetentionPolicy.CLASS)修饰的注解，
  *表示注解的信息被保留在class文件(字节码文件)中当程序编译时，
  *但不会被虚拟机读取在运行的时候；
  *用@Retention(RetentionPolicy.SOURCE )修饰的注解,表示注解的信息会被编译器抛弃，
  *不会留在class文件中，注解的信息只会留在源文件中；
  *用@Retention(RetentionPolicy.RUNTIME )修饰的注解，
  *表示注解的信息被保留在class文件(字节码文件)中当程序编译时，会被虚拟机保留在运行时，
  *所以他们可以用反射的方式读取。
  *RetentionPolicy.RUNTIME 可以让你从JVM中读取Annotation注解的信息，以便在分析程序的时候使用.
  *@See http://blog.csdn.net/liuwenbo0920/article/details/7290586
  */
@Retention(RetentionPolicy.RUNTIME)

/**
  * ＠Target里面的ElementType是用来指定Annotation类型可以用在哪一些元素上的.
  * 说明一下：TYPE(类型), FIELD(属性), METHOD(方法),
  * PARAMETER(参数), CONSTRUCTOR(构造函数),LOCAL_VARIABLE(局部变量), ANNOTATION_TYPE,PACKAGE(包),
  * 其中的TYPE(类型)是指 可以用在Class,Interface,Enum和Annotation类型上.
  */
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface RefBean {
	
	//指向实体Name
	String beanName();
	
	//指向实体属性
	String filed();
	
	String[] paramsType();
	
	boolean isPrimaryKey() default false;
	
	
}
