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
public class JaxbTester {

    private final String path = "./dist/config.xml";

    private class TaggedClassProxy extends TaggedClass {

        public TaggedClassProxy() {
        }

        public TaggedClassProxy(String tag, Class type) throws Exception {
            super(tag, type);
        }

        
        
        @XmlElement
        @Override
        public String getTag() {
            return super.getTag(); //To change body of generated methods, choose Tools | Templates.
        }

        @XmlElement
        public String getClassName() {
            return this.getType().getName();
        }

        public void setClassName(String name) throws ClassNotFoundException {
            Class type = Class.forName(name);
            this.setType(type);
        }

    }

    private class ResolveArgumentsProxy extends ResolveArguments {

        public ResolveArgumentsProxy(Class targetType) {
            super(targetType);
        }

        public ResolveArgumentsProxy(){
            super(null);
        }

        public ResolveArgumentsProxy(LifetimeType lifetime, Class targetType) {
            super(lifetime, targetType);
        }
        
        
        
        
        @XmlElement
        @Override
        public LifetimeType getLifetime() {
            return super.getLifetime(); //To change body of generated methods, choose Tools | Templates.
        }

        @XmlElement
        public String getTargetTypeName() {
            return this.getTargetType().getName();
        }

        public void setTargetTypeName(String name) throws ClassNotFoundException {
            Class type = Class.forName(name);
            this.setTargetType(type);
        }

    }

    private class Record {

        private TaggedClassProxy taggedClass;
        private ResolveArgumentsProxy resolveArguments;

        public Record() {
        }

        public Record(TaggedClassProxy taggedClass, ResolveArgumentsProxy resolveArguments) {
            this.taggedClass = taggedClass;
            this.resolveArguments = resolveArguments;
        }

        @XmlElement
        public TaggedClassProxy getTaggedClass() {
            return taggedClass;
        }

        public void setTaggedClass(TaggedClassProxy taggedClass) {
            this.taggedClass = taggedClass;
        }

        @XmlElement
        public ResolveArgumentsProxy getResolveArguments() {
            return resolveArguments;
        }

        public void setResolveArguments(ResolveArgumentsProxy resolveArguments) {
            this.resolveArguments = resolveArguments;
        }

    }

    @XmlRootElement
    private class DataList {

        private ArrayList<Record> records;

        public DataList(ArrayList<Record> records) {
            this.records = records;
        }

        public DataList() {
            records = new ArrayList<>();
        }

        public ArrayList<Record> getRecords() {
            return records;
        }

        public void setRecords(ArrayList<Record> records) {
            this.records = records;
        }

    }

    private DataList data = new DataList();

    public JaxbTester() throws Exception {
        data.getRecords().add(new Record(new TaggedClassProxy("DEFAULT", Car.class),
                new ResolveArgumentsProxy(LifetimeType.Singleton, Car.class)));
        data.getRecords().add(new Record(new TaggedClassProxy("DEFAULT", Body.class),
                new ResolveArgumentsProxy(LifetimeType.Singleton, BlueCarBody.class)));
        data.getRecords().add(new Record(new TaggedClassProxy("Kitty", Body.class),
                new ResolveArgumentsProxy(LifetimeType.Singleton, RedCarBody.class)));

    }

    @Test
    public void toFileTest() {
        System.out.println("--- toFileTest -----");

        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        JAXB.marshal(data, file);
        file = new File(path);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void fromFileTest() {
        System.out.println("--- fromFileTest -----");
        DataList instance = JAXB.unmarshal(new File("obj.xml"), DataList.class);
        Assert.assertEquals(instance.getRecords().size(), data.getRecords().size());
        Assert.assertEquals(instance.getRecords().get(2).getTaggedClass().getTag(),
                data.getRecords().get(2).getTaggedClass().getTag());
    }

}
