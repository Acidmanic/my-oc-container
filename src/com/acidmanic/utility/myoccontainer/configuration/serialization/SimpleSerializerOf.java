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
