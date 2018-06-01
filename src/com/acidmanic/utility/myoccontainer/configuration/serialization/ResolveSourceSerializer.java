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

import com.acidmanic.utility.myoccontainer.configuration.data.ResolveSource;

/**
 *
 * @author diego
 */
public class ResolveSourceSerializer extends SimpleSerializerOf<ResolveSource>{

    @Override
    public String serialize(ResolveSource object) {
        return object.getType().getName() + 
                SEP + object.getTag();
    }

    @Override
    public int getFieldsnumber() {
        return 2;
    }

    @Override
    public ResolveSource deserialize(String[] fields, int from) {
        try {
            Class type = Class.forName(fields[from]);
            String tag = fields[from+1].trim();
            return new ResolveSource(tag, type);
        } catch (Exception e) {
        }
        return null;
    }


   
    
}