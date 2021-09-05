package com.example.kotlininpractice.t;

public class Dog extends Animal {

    {
        System.out.println("Dog init");
    }

    public Dog(){
        System.out.println("Dog constructor");
    }

    public void doSth() {
        System.out.println("run");
    }

}