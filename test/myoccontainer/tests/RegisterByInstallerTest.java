/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.tests;

import com.acidmanic.utility.myoccontainer.Resolver;
import java.util.ArrayList;
import java.util.List;
import myoccontainer.models.Car;
import myoccontainer.models.animals.Animal;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class RegisterByInstallerTest {
    
    
    private final Resolver resolver;

    public RegisterByInstallerTest() {
        this.resolver = new Resolver();
        
        
        this.resolver.install(new TestInstaller());
        
        
    }
    
    @Test
    public void allModelsShouldBeInstalled(){
        System.out.println("--- allModelsShouldBeInstalled ---");
        
        Object[] cars = this.resolver.resolveAll(Car.class);
        Object[] animals = this.resolver.resolveAll(Animal.class);
        
        Assert.assertNotNull(cars);
        Assert.assertNotNull(animals);
        
       Assert.assertTrue(cars.length>=1);
       Assert.assertTrue(animals.length>=5);
        
    }
    
    
    
}
