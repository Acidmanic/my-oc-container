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
package com.acidmanic.utility.myoccontainer.configuration.serialization;

import com.acidmanic.utility.myoccontainer.configuration.data.TaggedClass;
import com.acidmanic.utility.myoccontainer.configuration.data.MapRecord;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveArguments;

/**
 *
 * @author diego
 */
public class MapRecordSerializer extends SimpleSerializerOf<MapRecord>{

    @Override
    public String serialize(MapRecord object) {
        return new TaggedClassSerializer().serialize(object.getTaggedClass()) +
                SEP + new ResolveArgumentsSerializer().serialize(object.getResolveArguments());
    }

    @Override
    public MapRecord deserialize(String[] fields, int from) {
        int index=from;
        SimpleSerializerOf serializer = new TaggedClassSerializer(); 
        TaggedClass tclass = (TaggedClass) serializer.deserialize(fields, index);
        index += serializer.getFieldsnumber();
        serializer = new ResolveArgumentsSerializer();
        ResolveArguments arguments = (ResolveArguments) serializer.deserialize(fields, index);
        return new MapRecord(tclass, arguments);
    }

    @Override
    public int getFieldsnumber() {
        return new TaggedClassSerializer().getFieldsnumber() +
                new ResolveArgumentsSerializer().getFieldsnumber();
    }
    
}
