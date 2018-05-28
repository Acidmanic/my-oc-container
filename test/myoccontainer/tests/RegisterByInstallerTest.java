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
