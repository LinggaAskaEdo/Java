package com.transactional.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExampleMain
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyServiceBean bean = context.getBean(MyServiceBean.class);
        System.out.println("-- calling doSomething() --");
        bean.doSomething();
        System.out.println("-- calling doSomething2() --");
        bean.doSomething2();
        System.out.println("-- calling doSomething3() --");
        bean.doSomething3();
        System.out.println("-- calling bank() --");
        bean.bank();
    }
}