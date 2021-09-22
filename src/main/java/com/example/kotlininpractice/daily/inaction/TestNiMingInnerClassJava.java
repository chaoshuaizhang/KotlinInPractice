package com.example.kotlininpractice.daily.inaction;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestNiMingInnerClassJava {

    String createOrder(String orderNo) {
        return "1000";
    }

    static int createRefund(String refundNo) {
        return 1001;
    }

    public static void main(String[] args) {
        BiFunction<TestNiMingInnerClassJava, String, String> createDTO = TestNiMingInnerClassJava::createOrder;
        createDTO.apply(new TestNiMingInnerClassJava(), "order no");
        Function<String, Integer> createRefund = TestNiMingInnerClassJava::createRefund;
        int apply = createRefund.apply("refund no");
        new ArrayList<String>().forEach(System.out::print);
        int tag = 0;
        addService(new IService() {
            @Override
            public void query() {

            }

            @Override
            public void add() {
            }

            @Override
            public void remove() {
            }
        });
        addService4(() -> {

        });
    }


    static void addService(IService service) {
    }

    static void addService4(IService4 service) {
    }

    interface IService extends IService2, IService3 {
        void add();
    }

    interface IService2 {
        void remove();
    }

    interface IService3 {
        void query();
    }


}

interface IService4 {
    void query();
}