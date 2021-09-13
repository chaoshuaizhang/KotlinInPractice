package com.example.kotlininpractice.daily.inaction;

import java.lang.reflect.Method;

public class TestTmp {
    public static void main(String[] args) {
        TestTmp tmp = new TestTmp();
        tmp.a();
    }

    public void a() {
        MyClazz2 clazz2 = new MyClazz2();
        A3 a3 = new A3();
        a3.test();
        a3.test_kotlin();
        a3.a0fun();
    }

    private class A0 {
        private final void a0fun() {
            System.out.println("A0");
        }
    }

    interface A1 {
        default void test() {
            System.out.println("a1");
        }
    }

    interface A2 {
        default void test() {
            System.out.println("a2");
        }
    }

    public class A3 extends A0 implements A1, A2, B1 {

        void a0fun() {
            System.out.println("A3 -> A0");
        }

        @Override
        public void test() {
            A1.super.test();
            A2.super.test();
        }

        @Override
        public void test_kotlin() {
            B1.DefaultImpls.test_kotlin(this);
        }
    }

//    public class CCCYYY extends ClassSealed {
//        public CCCYYY() {
//            super();
//        }
//    }

    abstract class A4 extends C0 {
        private A4() {
        }

        private String a5Tag2 = "";


        void a() {
            C0 c0 = new C0();
            // c0.c0fun$KotlinInPractice_main();
            try {
                Class cc = Class.forName("com.example.kotlininpractice.daily.inaction.C0");
                Method c0fun$KotlinInPractice_main = cc.getDeclaredMethod("c0fun$KotlinInPractice");
//                c0fun$KotlinInPractice_main.setAccessible(true);
                c0fun$KotlinInPractice_main.invoke(c0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class A5 extends A4 {
        private String a5Tag = "";

        {
//            a5Tag2 = "cbhds";
            a();
        }
    }
    class MyClazz{
        private MyClazz(){}
    }

    class MyClazz2 extends MyClazz{

    }

}