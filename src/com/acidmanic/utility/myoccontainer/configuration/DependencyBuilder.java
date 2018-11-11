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
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveParameters;
import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveSource;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;

/**
 *
 * @author diego
 */
public class DependencyBuilder {

    private Class resolvingType;
    private Class resolvedType;
    private String tag;
    private LifetimeType lifetimeType;
    private Builder builder=Builder.NULL;
    
    public DependencyBuilder() {
        this.resolvingType = Object.class;
        this.resolvedType = Object.class;
        this.tag = ResolveSource.DEFAULT_TAG;
        this.lifetimeType = LifetimeType.Transient;
    }

    public DependencyBuilder bind(Class type) {
        this.resolvingType = type;
        return this;
    }

    public DependencyBuilder to(Class type) {
        this.resolvedType = type;
        return this;
    }

    public DependencyBuilder tagged(String tag) {
        this.tag = tag;
        return this;
    }

    public DependencyBuilder livesAsA(LifetimeType lifetime) {
        this.lifetimeType = lifetime;
        return this;
    }
    
    public DependencyBuilder withBuilder(Builder builder){
        this.builder=builder;
        return this;
    }

    public Dependency build() throws Exception {
        return new Dependency(
                new ResolveSource(this.tag, this.resolvingType), 
                new ResolveParameters(this.lifetimeType,
                        this.resolvedType,this.builder));
    }
}
