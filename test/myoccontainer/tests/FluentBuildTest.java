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
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import java.util.logging.Level;
import java.util.logging.Logger;
import myoccontainer.models.BlueCarBody;
import myoccontainer.models.Body;
import myoccontainer.models.Car;
import myoccontainer.models.CarMotor;
import myoccontainer.models.ClassicWheel;
import myoccontainer.models.Electrics;
import myoccontainer.models.FastElectrics;
import myoccontainer.models.HeavySilanders;
import myoccontainer.models.LightSilanders;
import myoccontainer.models.Motor;
import myoccontainer.models.PowerElectrics;
import myoccontainer.models.RedCarBody;
import myoccontainer.models.Silanders;
import myoccontainer.models.SportWheel;
import myoccontainer.models.Wheel;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class FluentBuildTest {

    private final Resolver resolver = new Resolver();
    private final String tag = "Kitty";

    public FluentBuildTest() throws Exception {

        resolver.register().bind(Car.class).to(Car.class).livesAsA(LifetimeType.Transient);
        resolver.register().bind(Car.class).to(Car.class).tagged(tag).livesAsA(LifetimeType.Singleton);
        resolver.register().bind(Body.class).to(BlueCarBody.class);
        resolver.register().bind(Body.class).to(RedCarBody.class).tagged(tag);
        resolver.register().bind(Wheel.class).to(ClassicWheel.class);
        resolver.register().bind(Wheel.class).to(SportWheel.class).tagged(tag);
        resolver.register().bind(Silanders.class).to(HeavySilanders.class);
        resolver.register().bind(Silanders.class).to(LightSilanders.class).tagged(tag);
        resolver.register().bind(Electrics.class).to(PowerElectrics.class);
        resolver.register().bind(Electrics.class).to(FastElectrics.class).tagged(tag);
        resolver.register().bind(Motor.class).to(CarMotor.class);
        
        
        
    }

    @Test
    public void defaultCarShoulbeTransient() throws Exception {
        System.out.println("defaultCarShoulbeTransient");
        Car instance1 = (Car) resolver.resolve(Car.class);
        Car instance2 = (Car) resolver.resolve(Car.class);
        instance1.move();
        Assert.assertNotEquals(instance1.getPassedDistance(), 
                instance2.getPassedDistance());
    }
    
    
    @Test
    public void kittyCarShoulbeSingleton() throws Exception {
        System.out.println("kittyCarShoulbeSingleton");
        Car instance1 = (Car) resolver.resolve(Car.class,tag);
        Car instance2 = (Car) resolver.resolve(Car.class,tag);
        instance1.move();
        Assert.assertEquals(instance1.getPassedDistance(), 
                instance2.getPassedDistance());
    }

    @Test
    public void testSave() {
        try {
            ConfigurationFile.save("config.config", resolver.getRegisteredDependancies());
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Logger.getLogger(FluentBuildTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
        }
    }

}
