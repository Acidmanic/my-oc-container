/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import myoccontainer.models.Electrics;
import myoccontainer.models.FastElectrics;
import myoccontainer.models.HeavySilanders;
import myoccontainer.models.Motor;
import myoccontainer.models.Silanders;
import myoccontainer.models.SportWheel;
import myoccontainer.models.Wheel;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class ManualRegister {
    
    
    
    private final Resolver resolver = new Resolver();

    public ManualRegister() {
    
        resolver.register(Car.class, Car.class);
        resolver.register(Body.class, BlueCarBody.class);
        resolver.register(Wheel.class, SportWheel.class);
        resolver.register(Silanders.class, HeavySilanders.class);
        resolver.register(Electrics.class, FastElectrics.class);
        resolver.register(Motor.class, CarMotor.class);
    
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
    
    @Test
    public void testSave(){
        try {
            ConfigurationFile.save("config.config", resolver.getRegisteredDependancies());
            Assert.assertTrue(true);
        } catch (Exception ex) {
            Logger.getLogger(ManualRegister.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail();
        }
    }
    
    
    
}
