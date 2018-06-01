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
import com.acidmanic.utility.myoccontainer.configuration.ConfigurationFile;
import myoccontainer.models.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class WithBuilderTest {

    private final Resolver resolver = new Resolver();

    public WithBuilderTest() throws Exception {
           resolver.install(new BuilderInstaller());
    }


    public void assertCustomeCar(Car car) {
        Assert.assertNotEquals("Car", car.getCarName());
    }

    public void assertNotCustomeCar(Car car) {
        Assert.assertEquals("Car", car.getCarName());
    }

    @Test
    public void shouldCreateANormalCar() throws Exception {
        System.out.println("---shouldCreateANormalCar---");
        Car instance = (Car) resolver.resolve(Car.class);
        Assert.assertNotNull(instance);
        assertNotCustomeCar(instance);
        instance.print();
    }

    @Test
    public void shouldCreateAKittyCar() throws Exception {
        System.out.println("---shouldCreateAKittyCar---");
        Car instance = (Car) resolver.resolve(Car.class, BuilderInstaller.KITTY_TAG);
        Assert.assertNotNull(instance);
        assertNotCustomeCar(instance);
        instance.print();
    }

    @Test
    public void shouldCreateACustomeCar() throws Exception {
        System.out.println("---shouldCreateACustomeCar---");
        Car instance = (Car) resolver.resolve(Car.class, BuilderInstaller.CUSTOME_TAG);
        Assert.assertNotNull(instance);
        assertCustomeCar(instance);
        instance.print();
    }

    @Test
    public void shouldNotBeAbleToLoadInlineBuilderExpression() throws Exception {
        Resolver savingResolver = new Resolver();
        savingResolver.install(new BuilderInstaller());
        ConfigurationFile.save("dist/config.config", 
                savingResolver.getRegisteredDependancies());
        Resolver loadedResolver = new Resolver("dist/config.config");
        
        Object result = null;
        try {
            loadedResolver.resolveByTagOnly(Car.class,BuilderInstaller.CUSTOME_TAG);
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.assertNull(result);
        
    }
    
    
    @Test
    public void shouldBeAbleToLoadConcereteBuilder() throws Exception {
        Resolver savingResolver = new Resolver();
        savingResolver.install(new BuilderInstaller());
        ConfigurationFile.save("dist/config.config", 
                savingResolver.getRegisteredDependancies());
        Resolver loadedResolver = new Resolver("dist/config.config");
        
        Car result = (Car) loadedResolver.resolveByTagOnly(Car.class,BuilderInstaller.CUSTOME_CONCERETE);
        Assert.assertNotNull(result);
        Assert.assertEquals(BuilderInstaller.CARNAME_CUSTOME,result.getCarName());
        
    }

}
