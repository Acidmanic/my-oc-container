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
import com.acidmanic.utility.myoccontainer.configuration.data.Builder;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveArguments;
import com.acidmanic.utility.myoccontainer.configuration.data.MapRecord;
import com.acidmanic.utility.myoccontainer.configuration.data.TaggedClass;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;

/**
 *
 * @author diego
 */
public class ResolvationMapRecordBuilder {

    private Class resolvingType;
    private Class resolvedType;
    private String tag;
    private LifetimeType lifetimeType;
    private Builder builder=Builder.NULL;
    
    public ResolvationMapRecordBuilder() {
        this.resolvingType = Object.class;
        this.resolvedType = Object.class;
        this.tag = TaggedClass.DEFAULT_TAG;
        this.lifetimeType = LifetimeType.Transient;
    }

    public ResolvationMapRecordBuilder bind(Class type) {
        this.resolvingType = type;
        return this;
    }

    public ResolvationMapRecordBuilder to(Class type) {
        this.resolvedType = type;
        return this;
    }

    public ResolvationMapRecordBuilder tagged(String tag) {
        this.tag = tag;
        return this;
    }

    public ResolvationMapRecordBuilder livesAsA(LifetimeType lifetime) {
        this.lifetimeType = lifetime;
        return this;
    }
    
    public ResolvationMapRecordBuilder withBuilder(Builder builder){
        this.builder=builder;
        return this;
    }

    public MapRecord build() throws Exception {
        return new MapRecord(
                new TaggedClass(this.tag, this.resolvingType), 
                new ResolveArguments(this.lifetimeType,
                        this.resolvedType,this.builder));
    }
}
