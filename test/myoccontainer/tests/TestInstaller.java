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

import com.acidmanic.utility.myoccontainer.Installer;
import com.acidmanic.utility.myoccontainer.Registerer;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
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
import myoccontainer.models.animals.Animal;
import myoccontainer.models.animals.Cat;
import myoccontainer.models.animals.Chicken;
import myoccontainer.models.animals.Dog;
import myoccontainer.models.animals.Frog;
import myoccontainer.models.animals.Horse;

/**
 *
 * @author diego
 */
public class TestInstaller implements Installer{

    public static final String FANCY_TAG="fancy";
    
    @Override
    public void configure(Registerer myoc) {
        
        myoc.register().bind(Animal.class).to(Cat.class).tagged("cute");
        myoc.register().bind(Animal.class).to(Dog.class).tagged("loyal");
        myoc.register().bind(Animal.class).to(Frog.class).tagged("funny");
        myoc.register().bind(Animal.class).to(Horse.class).tagged("nobel");
        myoc.register().bind(Animal.class).to(Chicken.class).tagged("food");
        
        myoc.register().bind(Car.class).to(Car.class).livesAsA(LifetimeType.Transient);
        myoc.register().bind(Car.class).to(Car.class).tagged(FANCY_TAG).livesAsA(LifetimeType.Singleton);
        myoc.register().bind(Body.class).to(BlueCarBody.class);
        myoc.register().bind(Body.class).to(RedCarBody.class).tagged(FANCY_TAG);
        myoc.register().bind(Wheel.class).to(ClassicWheel.class);
        myoc.register().bind(Wheel.class).to(SportWheel.class).tagged(FANCY_TAG);
        myoc.register().bind(Silanders.class).to(HeavySilanders.class);
        myoc.register().bind(Silanders.class).to(LightSilanders.class).tagged(FANCY_TAG);
        myoc.register().bind(Electrics.class).to(PowerElectrics.class);
        myoc.register().bind(Electrics.class).to(FastElectrics.class).tagged(FANCY_TAG);
        myoc.register().bind(Motor.class).to(CarMotor.class);
        
    }
    
}
