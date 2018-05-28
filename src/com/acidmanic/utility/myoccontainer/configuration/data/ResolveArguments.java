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
public class ResolveArguments {
    
    private LifetimeType lifetime;
    private Class targetType;

    public ResolveArguments(Class targetType) {
        this.targetType = targetType;
        this.lifetime = LifetimeType.Transient;
    }

    public ResolveArguments(LifetimeType lifetime, Class targetType) {
        this.lifetime = lifetime;
        this.targetType = targetType;
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
    
    
}
