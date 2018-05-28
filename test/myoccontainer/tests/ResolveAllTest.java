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
