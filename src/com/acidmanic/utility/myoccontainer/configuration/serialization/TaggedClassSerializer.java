/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration.serialization;

import com.acidmanic.utility.myoccontainer.configuration.TaggedClass;

/**
 *
 * @author diego
 */
public class TaggedClassSerializer extends SimpleSerializerOf<TaggedClass>{

    @Override
    public String serialize(TaggedClass object) {
        return object.getType().getName() + 
                SEP + object.getTag();
    }

    @Override
    public int getFieldsnumber() {
        return 2;
    }

    @Override
    public TaggedClass deserialize(String[] fields, int from) {
        try {
            Class type = Class.forName(fields[from]);
            String tag = fields[from+1].trim();
            return new TaggedClass(tag, type);
        } catch (Exception e) {
        }
        return null;
    }


   
    
}