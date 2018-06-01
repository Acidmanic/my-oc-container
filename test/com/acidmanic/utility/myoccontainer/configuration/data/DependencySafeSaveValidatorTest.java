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
package com.acidmanic.utility.myoccontainer.configuration.data;

import com.acidmanic.utility.myoccontainer.configuration.DependencyBuilder;
import myoccontainer.models.Car;
import myoccontainer.models.CarMotor;
import myoccontainer.models.ClassicWheel;
import myoccontainer.models.FastElectrics;
import myoccontainer.models.HeavySilanders;
import myoccontainer.models.RedCarBody;
import myoccontainer.tests.CarBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DependencySafeSaveValidatorTest {

    private final String INLINE_TAG="Inline";
    private final String INLINE_CARNAME="Inline Car";
    private final String BUILDER_TAG="BuilderClass";
    
    public DependencySafeSaveValidatorTest() {
    }

    @Test
    public void anInlineBuilderIsNotSaveSafe() throws Exception {
        System.out.println("isSaveSafe");
        Dependency mapRecord = new DependencyBuilder()
                .bind(Car.class).to(Car.class)
                .tagged(INLINE_TAG)
                .withBuilder(
                        ()->{
                            Car car = new Car(new RedCarBody(), 
                                    new CarMotor(new FastElectrics(), 
                                            new HeavySilanders()), 
                                    new ClassicWheel());
                            car.setCarName(INLINE_CARNAME);
                            return car;
                        }
                ).build();
                
        DependencySafeSaveValidator instance = new DependencySafeSaveValidator();
        boolean expResult = false;
        boolean result = instance.isSaveSafe(mapRecord);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void aCarBuilderIsSaveSafe() throws Exception {
        System.out.println("isSaveSafe");
        Dependency mapRecord = new DependencyBuilder()
                .bind(Car.class).to(Car.class)
                .tagged(BUILDER_TAG)
                .withBuilder(new CarBuilder()).build();
        DependencySafeSaveValidator instance = new DependencySafeSaveValidator();
        boolean expResult = true;
        boolean result = instance.isSaveSafe(mapRecord);
        assertEquals(expResult, result);
    }
}
