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

import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import java.util.List;

/**
 *
 * @author diego
 */
public interface DependencyDictionaryInterface {

    
    boolean containesAny(Class key);

    Dependency get(Class type, String tag);

    void put(Dependency record);

    void putAll(DependencyDictionary dictionary);

    Dependency remove(Class type, String tag);

    Dependency searchForAKey(Class key);

    void subtract(DependencyDictionary dictionary);

    List<Dependency> toList();
    
    List<Dependency> getAll(Class type);
    
}
