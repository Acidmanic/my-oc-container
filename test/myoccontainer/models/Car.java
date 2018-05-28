/* 
 * Copyright (C) 2018 Mani Moayedi (acidmanic.moayedi@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

    private int passedDistance=0;
    
    public Car(Body body,Motor motor,Wheel wheel) {
        this.body = body;
        this.motor = motor;
        this.wheel = wheel;
    }
    
    public void print(){
        System.out.println(this.body.getColor());
        System.out.println(this.motor.description());
        System.out.println(this.wheel.info());
        System.out.println(Integer.toString(this.passedDistance));
    }
    
    public void move(){
        this.passedDistance+=100;
    }

    public int getPassedDistance() {
        return passedDistance;
    }

    public void setPassedDistance(int passedDistance) {
        this.passedDistance = passedDistance;
    }
    
    
    
}
