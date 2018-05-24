/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.tests;

import com.acidmanic.utility.myoccontainer.Resolver;
import myoccontainer.models.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class FromFileRegister {
    
    
    
    private final Resolver resolver;

    public FromFileRegister() {
    
        resolver = new Resolver("config.config");
    
    }
    
    
    
    @Test
    public void resolveCar(){
        try {
            Car car = (Car) resolver.resolve(Car.class);
            car.print();
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
        
    }
 
    
    
}
