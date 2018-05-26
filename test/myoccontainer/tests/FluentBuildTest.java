/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.tests;

import com.acidmanic.utility.myoccontainer.Resolver;
import com.acidmanic.utility.myoccontainer.configuration.ConfigurationFile;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import java.rmi.activation.Activatable;
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
