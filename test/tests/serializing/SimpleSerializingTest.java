/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
