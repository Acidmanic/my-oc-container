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
package com.acidmanic.utility.myoccontainer;

import com.acidmanic.utility.myoccontainer.configuration.ResolvationMapRecordBuilder;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;

/**
 *
 * @author diego
 */
public interface Registerer {

    void register(Class resolving, Class resolved);

    void register(Class resolving, Class resolved, String tag) throws Exception;

    void register(Class resolving, Class resolved, LifetimeType lifetime) throws Exception;

    void register(Class resolving, Class resolved, String tag, LifetimeType lifetime) throws Exception;

    ResolvationMapRecordBuilder register();
    
}
