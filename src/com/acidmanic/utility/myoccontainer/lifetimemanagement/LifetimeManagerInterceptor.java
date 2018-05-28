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
