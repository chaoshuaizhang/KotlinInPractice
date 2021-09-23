package com.example.kotlininpractice.daily.inaction.泛型;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class Animal {

    public void animalEat() {
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

class People extends Animal {
    public void peopleEat() {
    }

    @Override
    public int hashCode() {
        return 2;
    }
}

class Tiger extends Animal {
    public void tigerEat() {
    }

    @Override
    public int hashCode() {
        return 3;
    }
}

class Boy extends People {
    public void boyEat() {
    }

    @Override
    public int hashCode() {
        return 4;
    }
}

class SmallBoy<T extends Boy> {

    public T t;

    public void eat() {
        t.boyEat();
    }
}

public class TestJava {
    List<String> stringList = new ArrayList<>();
    // 擦除到第一个边界：也就是People
    List<Boy> boyList = new ArrayList<>();

    List<List<Boy>> boyListList = new ArrayList<>();

    public static void main(String[] args) {
        // execute1Bound();
        execute2Bound();
    }

    private static void execute1Bound() {
        TestJava testJava = new TestJava();
        testJava.stringList.add("a");
        testJava.stringList.add("b");
        testJava.stringList.add("c");
        testJava.test1Bound();
    }

    private static void execute2Bound() {
        TestJava testJava = new TestJava();
        testJava.boyList.add(new Boy());
        testJava.boyList.add(new Boy());
        testJava.boyList.add(new Boy());
        testJava.boyList.add(new Boy());
        testJava.test2Bound();
    }

    private static void execute3Bound() {
        TestJava testJava = new TestJava();
        List<Boy> list1 = new ArrayList<>();
        List<Boy> list2 = new ArrayList<>();
        List<Boy> list3 = new ArrayList<>();
        testJava.boyListList.add(list1);
        testJava.boyListList.add(list2);
        testJava.boyListList.add(list3);
        testJava.test2Bound();
    }

    private void test1Bound() {
        try {
            Field stringList = getClass().getDeclaredField("stringList");
            stringList.setAccessible(true);
            List list = (List) stringList.get(this);
            list.add(5);
            list.forEach(o -> {
                System.out.println(o);
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void test2Bound() {
        try {
            Field boyList = getClass().getDeclaredField("boyList");
            boyList.setAccessible(true);
            List list = (List) boyList.get(this);
            list.add(500);
            list.forEach(o -> {
                System.out.println(o);
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void test3Bound() {
        try {
            Field boyList = getClass().getDeclaredField("boyListList");
            boyList.setAccessible(true);
            List list = (List) boyList.get(this);
            list.add(500);
            list.forEach(o -> {
                System.out.println(o);
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
