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
public class TaggedRegisterTest {

    private final Resolver resolver = new Resolver();
    private final String tag = "Kitty";

    public TaggedRegisterTest() throws Exception {

        resolver.getRegistery().register(Car.class, Car.class);
        resolver.getRegistery().register(Body.class, BlueCarBody.class);
        resolver.getRegistery().register(Body.class, RedCarBody.class, tag);
        resolver.getRegistery().register(Wheel.class, ClassicWheel.class);
        resolver.getRegistery().register(Wheel.class, SportWheel.class, tag);
        resolver.getRegistery().register(Silanders.class, LightSilanders.class, tag);
        resolver.getRegistery().register(Silanders.class, HeavySilanders.class);
        resolver.getRegistery().register(Electrics.class, PowerElectrics.class);
        resolver.getRegistery().register(Electrics.class, FastElectrics.class, tag);
        resolver.getRegistery().register(Motor.class, CarMotor.class);

    }

    @Test
    public void resolveDefaultCarTest() {
        System.out.println("resolveDefaultCarTest");
        try {
            Car car = (Car) resolver.resolve(Car.class);
            car.print();
            Assert.assertTrue(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    @Test
    public void resolveTaggedCarTest() {
        System.out.println("resolveTaggedCarTest");
        try {
            Car car = (Car) resolver.resolve(Car.class, tag);
            car.print();
            Assert.assertTrue(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Assert.fail(ex.getMessage());
        }

    }

    @Test
    public void testSave() {
        try {
            ConfigurationFile.save("config.config", resolver.getRegisteredDependancies());
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Logger.getLogger(LifetimeManagementTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
        }
    }
    
}
