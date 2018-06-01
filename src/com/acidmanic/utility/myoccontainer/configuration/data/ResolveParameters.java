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
package com.acidmanic.utility.myoccontainer.configuration.data;

import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;

/**
 *
 * @author diego
 */
public class ResolveParameters {

    private LifetimeType lifetime;
    private Class targetType;
    private Builder builder = Builder.NULL;

    public ResolveParameters(Class targetType) {
        this.targetType = targetType;
        this.lifetime = LifetimeType.Transient;
    }

    public ResolveParameters(LifetimeType lifetime, Class targetType) {
        this.lifetime = lifetime;
        this.targetType = targetType;
    }

    public ResolveParameters(LifetimeType lifetime,
            Class targetType,
            Builder builder) {
        this.lifetime = lifetime;
        this.targetType = targetType;
        this.builder = builder;
    }

    public Class getTargetType() {
        return targetType;
    }

    public void setTargetType(Class targetType) {
        this.targetType = targetType;
    }

    public LifetimeType getLifetime() {
        return lifetime;
    }

    public void setLifetime(LifetimeType lifetime) {
        this.lifetime = lifetime;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public boolean hasBuilder() {
        if (this.builder == null) {
            return false;
        }
        if (this.builder.equals(Builder.NULL)) {
            return false;
        }
        return true;
    }

    public boolean hasConcreteBuilder() {
        if (hasBuilder()) {
            return !builder.getClass().isAnonymousClass();
        }
        return false;
    }

    //TODO: isAnonymousClass() does not work correctly in java
    // replace this with a better code whenever possible
    public boolean hasAnonymousBuilder() {
        if (hasBuilder()) {
            return builder.getClass().isAnonymousClass();
        }
        return false;
    }

}
