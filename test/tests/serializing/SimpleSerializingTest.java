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

import com.acidmanic.utility.myoccontainer.configuration.data.TaggedClass;
import com.acidmanic.utility.myoccontainer.configuration.data.MapRecord;
import com.acidmanic.utility.myoccontainer.configuration.serialization.MapRecordSerializer;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveArguments;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import myoccontainer.models.BlueCarBody;
import myoccontainer.models.Body;
import myoccontainer.models.Car;
import myoccontainer.models.RedCarBody;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author diego
 */
public class SimpleSerializingTest {

    private final MapRecord record;

    public SimpleSerializingTest() throws Exception {
        record = new MapRecord(new TaggedClass("Default", Car.class), 
                new ResolveArguments(LifetimeType.Singleton, Car.class));
    }

    @Test
    public void conversionTest() {
        System.out.println("--- conversionTest -----");

        String line = new MapRecordSerializer().serialize(record);
        Assert.assertNotNull(line);
        System.out.println(line);
        MapRecord converted = new MapRecordSerializer()
                .deserialize(line);
        Assert.assertEquals(converted.getTaggedClass().getTag(), record.getTaggedClass().getTag());
        Assert.assertEquals(converted.getTaggedClass().getType().getName(), 
                record.getTaggedClass().getType().getName());
        
    }

}
