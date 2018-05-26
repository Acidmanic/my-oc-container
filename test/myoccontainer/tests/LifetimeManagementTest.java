/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class LifetimeManagementTest {

    private final Resolver resolver = new Resolver();
    private final String tag = "Kitty";

    public LifetimeManagementTest() throws Exception {

        resolver.register(Car.class, Car.class,LifetimeType.Transient);
        resolver.register(Car.class, Car.class,tag,LifetimeType.Singleton);
        resolver.register(Body.class, BlueCarBody.class);
        resolver.register(Body.class, RedCarBody.class, tag);
        resolver.register(Wheel.class, ClassicWheel.class);
        resolver.register(Wheel.class, SportWheel.class,tag);
        resolver.register(Silanders.class, LightSilanders.class,tag);
        resolver.register(Silanders.class, HeavySilanders.class);
        resolver.register(Electrics.class, PowerElectrics.class);
        resolver.register(Electrics.class, FastElectrics.class,tag);
        resolver.register(Motor.class, CarMotor.class);

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
            Logger.getLogger(LifetimeManagementTest.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
        }
    }

}
