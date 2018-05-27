/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.tests;

import myoccontainer.models.animals.Horse;
import myoccontainer.models.animals.Animal;
import myoccontainer.models.animals.Chicken;
import myoccontainer.models.animals.Frog;
import myoccontainer.models.animals.Dog;
import myoccontainer.models.animals.Cat;
import com.acidmanic.utility.myoccontainer.Resolver;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class ResolveAllTest {
    
    
    
    
    
    
    
    private final Resolver resolver = new Resolver();

    public ResolveAllTest() {
    
        resolver.register().bind(Animal.class).to(Cat.class).tagged("cute");
        resolver.register().bind(Animal.class).to(Dog.class).tagged("loyal");
        resolver.register().bind(Animal.class).to(Frog.class).tagged("funny");
        resolver.register().bind(Animal.class).to(Horse.class).tagged("nobel");
        resolver.register().bind(Animal.class).to(Chicken.class).tagged("food");
    }
    
    
    
    @Test
    public void resolveAllTest(){
        System.out.println("--- resolveAllTest ---");
        Object[] allAnimals = resolver.resolveAll(Animal.class);
        for(Object aobject:allAnimals){
            Animal animal = (Animal) aobject;
            System.out.println("Animal: " + animal.getName());
        }
        Assert.assertEquals(5, allAnimals.length);
    }
    
    
    
}
