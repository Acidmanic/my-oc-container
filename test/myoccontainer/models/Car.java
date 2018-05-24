/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.models;

/**
 *
 * @author diego
 */
public class Car {

    private final Body body;
    private final Motor motor;
    private final Wheel wheel;

    public Car(Body body,Motor motor,Wheel wheel) {
        this.body = body;
        this.motor = motor;
        this.wheel = wheel;
    }
    
    public void print(){
        System.out.println(this.body.getColor());
        System.out.println(this.motor.description());
        System.out.println(this.wheel.info());
    }
    
    
    
}
