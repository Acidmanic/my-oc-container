/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer;

import com.acidmanic.utility.myoccontainer.configuration.ConfigurationFile;
import com.sun.jmx.snmp.SnmpDataTypeEnums;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class Resolver {

    private final HashMap<Class, Class> dependanciesMap = new HashMap<>();
    private final HashMap<Class, Class> primitives;
    public Resolver() {
        register(Long.class, Long.class);
        register(long.class, Long.class);
        register(Integer.class, Integer.class);
        register(int.class, Integer.class);
        register(Short.class, Short.class);
        register(short.class, Short.class);
        register(Double.class, Double.class);
        register(double.class, Double.class);
        register(Float.class, Float.class);
        register(float.class, Float.class);
        register(Byte.class,Byte.class);
        register(byte.class,Byte.class);
        register(String.class,String.class);
        primitives = (HashMap<Class, Class>) this.dependanciesMap.clone();
    }
    
    public Resolver(ConfigurationFile configuration){
        this();
        HashMap<Class,Class> types = configuration.getDependancyMap();
        for(Class tfrom:types.keySet()){
            this.dependanciesMap.put(tfrom, types.get(tfrom));
        }
    }
    
    
    public Resolver(String filepath){
        this(new ConfigurationFile(filepath));
    }

    public final void register(Class resolving, Class resolved) {
        this.dependanciesMap.put(resolving, resolved);
    }

    public Object resolve(Class type) throws Exception {
        Class resolvedType = null;

        try {
            resolvedType = this.dependanciesMap.get(type);
        } catch (Exception e) {
            throw new Exception(String.format("Unable to resolve type: {0}",
                    type.getName()));
        }
        Constructor[] constructors = resolvedType.getConstructors();
        Constructor ctor = constructors[0];
        if (ctor.getParameterCount() == 0) {
            return ctor.newInstance();
        }
        Object[] parameters = new Object[ctor.getParameterCount()];
        Parameter[] parameterTypes = ctor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = resolve(parameterTypes[i].getType());
        }
        return ctor.newInstance(parameters);
    }

    
    public HashMap<Class,Class> getRegisteredDependancies(){
         HashMap<Class, Class> ret =
                 (HashMap<Class, Class>) this.dependanciesMap.clone();
         for(Class key:primitives.keySet()){
             if(ret.containsKey(key)){
                 ret.remove(key);
             }
         }
         return ret;
    }
}
