/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.lifetimemanagement;

import com.acidmanic.utility.myoccontainer.configuration.data.ResolveArguments;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class LifetimeManagerInterceptor {
    
    private final HashMap<String,Object> singletones=new HashMap<>();
    
    
    public interface ObjectCreator{
        Object create() throws Exception;
    }
    
    
    
    public Object makeObject(ResolveArguments arguments,ObjectCreator creator) throws Exception{
        String key = arguments.getTargetType().getName();
        if(arguments.getLifetime()==LifetimeType.Singleton){
            if(singletones.containsKey(key)){
               return singletones.get(key);
            }
        }
        Object result = creator.create();
        if(arguments.getLifetime()==LifetimeType.Singleton){
            this.singletones.put(key, result);
        }
        return result;
    }
}
