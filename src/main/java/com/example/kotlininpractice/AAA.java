package com.example.kotlininpractice;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AAA {

    private static Class<?>[] matchClazz = {String.class, int.class};

    public static void main(String[] args) {

        Person p1 = new Person();
        Person p2 = new Person2();
//        check(p1);
//        check(p2);
//        PropertyChangeSupport changeSupport = new PropertyChangeSupport("");
//        changeSupport.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                System.out.println(evt);
//            }
//        });
//
//        changeSupport.firePropertyChange("p", "123", "abc");
//        Person p1 = new Person();
//        Person2 p2 = new Person2();
//        System.out.println(p2 instanceof IA);
//        System.out.println(p2 instanceof Person);
//
//        match(Person.class);
//        try {
//            Person person = Person.class.newInstance();
//            System.out.println(person.name);
//            Person instance = Person.class.getConstructor(String.class).newInstance("hehehe");
//            System.out.println(instance.name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private static void match(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (Arrays.equals(parameterTypes, matchClazz)) {
                System.out.print("---");
                for (Class<?> type : parameterTypes) {
                    System.out.print("  " + type.getName());
                }
                System.out.println();
            }
        }
    }



}

interface IA {
}

class Person implements IA {

    int age = 0;
    String name = null;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public void doSth(){
        System.out.println("p");
    }

}

class Person2 extends Person {

    public void doSth(){
        System.out.println("s");
    }

}
