/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration.serialization;

import com.acidmanic.utility.myoccontainer.TaggedClass;
import com.acidmanic.utility.myoccontainer.configuration.MapRecord;
import com.acidmanic.utility.myoccontainer.resolvearguments.ResolveArguments;

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
