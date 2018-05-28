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
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.configuration.data.MapRecord;
import java.util.List;

/**
 *
 * @author diego
 */
public interface ResolvationMapRecordDictionaryInterface {

    
    boolean containesAny(Class key);

    MapRecord get(Class type, String tag);

    void put(MapRecord record);

    void putAll(ResolvationMapRecordDictionary dictionary);

    MapRecord remove(Class type, String tag);

    MapRecord searchForAKey(Class key);

    void subtract(ResolvationMapRecordDictionary dictionary);

    List<MapRecord> toList();
    
    List<MapRecord> getAll(Class type);
    
}
