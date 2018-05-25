/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration.serialization;

/**
 *
 * @author diego
 * @param <T> type to be seiralized and deserialized
 */
public abstract class SimpleSerializerOf<T> {
    protected static final String SEP = "\t\t";
    public abstract String serialize(T object);
    public abstract T deserialize(String[] fields,int from);
    public abstract int getFieldsnumber();
    
    
    public T deserialize(String line){
        return deserialize(line.split(SEP),0);
    }
}
