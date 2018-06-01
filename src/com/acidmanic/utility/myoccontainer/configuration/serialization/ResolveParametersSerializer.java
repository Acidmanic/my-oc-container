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

import com.acidmanic.utility.myoccontainer.configuration.data.Builder;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveParameters;

/**
 *
 * @author diego
 */
public class ResolveParametersSerializer  extends SimpleSerializerOf<ResolveParameters>{

    @Override
    public String serialize(ResolveParameters object) {
        return object.getTargetType().getName()
                + SEP
                + object.getLifetime().toString()
                + SEP 
                + getBuilderClassName(object);
    }

    @Override
    public ResolveParameters deserialize(String[] fields, int from) {
        try {
            Class targetType = Class.forName(fields[from]);
            LifetimeType lifetimeType = LifetimeType.valueOf(fields[from+1].trim());
            Builder builder = tryCreteBuilder(fields[from+2]);
            return new ResolveParameters(lifetimeType, targetType,builder);
        } catch (Exception e) {
        }
        
        return null;
    }

    @Override
    public int getFieldsnumber() {
        return 2;
    }

    private String getBuilderClassName(ResolveParameters object) {
        try {
            if (object.hasConcreteBuilder()){
                return object.getBuilder().getClass().getName();
            }
        } catch (Exception e) {
        }
        return Builder.NULL.getClass().getName();
    }

    private Builder tryCreteBuilder(String field) {
        try {
            return (Builder) Class.forName(field).newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Builder.NULL;
    }
    
}
