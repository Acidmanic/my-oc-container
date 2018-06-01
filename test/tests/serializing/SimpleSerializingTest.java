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
package tests.serializing;

import com.acidmanic.utility.myoccontainer.configuration.data.ResolveSource;
import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import com.acidmanic.utility.myoccontainer.configuration.serialization.DependencySerializer;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveParameters;
import myoccontainer.models.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class SimpleSerializingTest {

    private final Dependency record;

    public SimpleSerializingTest() throws Exception {
        record = new Dependency(new ResolveSource("Default", Car.class), 
                new ResolveParameters(LifetimeType.Singleton, Car.class));
    }

    @Test
    public void conversionTest() {
        System.out.println("--- conversionTest -----");

        String line = new DependencySerializer().serialize(record);
        Assert.assertNotNull(line);
        System.out.println(line);
        Dependency converted = new DependencySerializer()
                .deserialize(line);
        Assert.assertEquals(converted.getTaggedClass().getTag(), record.getTaggedClass().getTag());
        Assert.assertEquals(converted.getTaggedClass().getType().getName(), 
                record.getTaggedClass().getType().getName());
        
    }

}
