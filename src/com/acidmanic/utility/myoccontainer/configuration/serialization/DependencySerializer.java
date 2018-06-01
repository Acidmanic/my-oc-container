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
import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveParameters;

/**
 *
 * @author diego
 */
public class DependencySerializer extends SimpleSerializerOf<Dependency>{

    @Override
    public String serialize(Dependency object) {
        return new ResolveSourceSerializer().serialize(object.getTaggedClass()) +
                SEP + new ResolveParametersSerializer().serialize(object.getResolveArguments());
    }

    @Override
    public Dependency deserialize(String[] fields, int from) {
        int index=from;
        SimpleSerializerOf serializer = new ResolveSourceSerializer(); 
        ResolveSource tclass = (ResolveSource) serializer.deserialize(fields, index);
        index += serializer.getFieldsnumber();
        serializer = new ResolveParametersSerializer();
        ResolveParameters arguments = (ResolveParameters) serializer.deserialize(fields, index);
        return new Dependency(tclass, arguments);
    }

    @Override
    public int getFieldsnumber() {
        return new ResolveSourceSerializer().getFieldsnumber() +
                new ResolveParametersSerializer().getFieldsnumber();
    }
    
}
