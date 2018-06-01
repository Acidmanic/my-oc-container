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

import com.acidmanic.utility.myoccontainer.configuration.data.Builder;
import myoccontainer.models.Car;
import myoccontainer.models.CarMotor;
import myoccontainer.models.LightSilanders;
import myoccontainer.models.PowerElectrics;
import myoccontainer.models.RedCarBody;
import myoccontainer.models.SportWheel;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CarBuilder implements Builder {
    
    public CarBuilder() {
    }

    @Override
    public Object build() {
        Car car = new Car(new RedCarBody(), new CarMotor(new PowerElectrics(), new LightSilanders()), new SportWheel());
        car.setCarName(BuilderInstaller.CARNAME_CUSTOME);
        return car;
    }
    
}
