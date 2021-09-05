package com.example.kotlininpractice.t;

public class TestPS implements Cloneable {
    public static void main(String[] args) {
        Animal dog = new Dog();
        checkAnimal((Animal) dog);
        TestPS testPS = new TestPS();
        try {
            testPS.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void checkAnimal(Animal animal) {
        animal.doSth();
    }

    public static void checkAnimal(Dog dog) {
        dog.doSth();
    }

}


