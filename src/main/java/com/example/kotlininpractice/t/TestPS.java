package com.example.kotlininpractice.t;

public class TestPS {
    public static void main(String[] args) {
        Animal dog = new Dog();
        checkAnimal((Animal) dog);

    }

    public static void checkAnimal(Animal animal) {
        animal.doSth();
    }

    public static void checkAnimal(Dog dog) {
        dog.doSth();
    }

}


