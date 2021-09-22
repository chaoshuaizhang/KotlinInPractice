package com.example.kotlininpractice.daily.inaction.在java中使用函数类;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class TestJava {
    private int tag;
    class PClazz{
        public PClazz(){
            tag = 9;
        }
        class A{
            {
                tag = 10;
            }
        }

    }

    public static void main(String[] args) {

        TestKotlinKt.kotlinCallback((s) -> {
            return Unit.INSTANCE;
        });
        TestKotlinKt.kotlinCallback(new Function1<String, Unit>() {
            @Override
            public Unit invoke(String s) {
                return Unit.INSTANCE;
            }
        });

        TestKotlinKt.kotlinCallback((i, s) -> {
            return 1.0d;
        });
    }
}
