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

import com.acidmanic.utility.myoccontainer.configuration.DependencyDictionary;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveSource;
import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import com.acidmanic.utility.myoccontainer.exceptions.UnableToResolveException;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeManagerInterceptor;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveParameters;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import com.acidmanic.utility.myoccontainer.resolvestrategies.DefaultOrAnyResolveStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import com.acidmanic.utility.myoccontainer.resolvestrategies.ResolveStrategy;
import com.acidmanic.utility.myoccontainer.resolvestrategies.TagOnlyResolveStrategy;
import com.acidmanic.utility.myoccontainer.resolvestrategies.TagOrDefaultResolveStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Resolver {


    private final LifetimeManagerInterceptor lifetimeManager = new LifetimeManagerInterceptor();
    private final Registery registery;

    public Resolver() {
        this(new Registery());
    }

    public Resolver(Registery registery) {
        this.registery = registery;
        
        this.registery.register().bind(DependencyDictionary.class)
                .withBuilder(() -> registery.getDependencyMap())
                .livesAsA(LifetimeType.Singleton);
        
    }

    public <T> T resolve(Class type) throws Exception {
        return (T) Resolver.this.resolve(type, ResolveSource.DEFAULT_TAG,
                new DefaultOrAnyResolveStrategy(
                        this.registery.getDependencyMap()));
    }

    public <T> T resolveByTagOnly(Class type, String tag) throws Exception {
        return (T) Resolver.this.resolve(type, tag,
                new TagOnlyResolveStrategy(this.registery.getDependencyMap()));
    }

    public <T> T resolve(Class type, String tag) throws Exception {
        return (T) Resolver.this.resolve(type, tag,
                new TagOrDefaultResolveStrategy(this.registery.getDependencyMap()));
    }

    public <T> T tryResolve(Class type) {
        return (T) tryResolve(type, null);
    }

    public <T> T tryResolve(Class type, T def) {
        try {
            return resolve(type);
        } catch (Exception e) {
        }
        return def;
    }

    public <T> T tryResolve(Class type, String tag) {
        return (T) tryResolve(type, tag, null);
    }

    public <T> T tryResolve(Class type, String tag, T def) {
        try {
            return resolve(type, tag);
        } catch (Exception e) {
        }
        return def;
    }

    private Object resolve(Class resolving, String tagIfAny, ResolveStrategy strategy) throws Exception {
        ResolveParameters resolved = strategy.search(resolving, tagIfAny);
        if (resolved == null) {
            throw new UnableToResolveException();
        }

        return lifetimeManager.makeObject(resolved,
                () -> createObject(resolved.getTargetType(), tagIfAny, strategy));

    }

    public void install(Installer installer) {
        installer.configure(this.registery);
    }

    private void tryCreateObject(ArrayList<Object> list,
            Dependency record,
            ResolveStrategy strategy) {
        try {
            list.add(
                    createObject(record.getResolveArguments().getTargetType(),
                            record.getTaggedClass().getTag(), strategy)
            );
        } catch (Exception e) {
        }
    }

    public <T> T[] resolveAll(Class resolving) {
        List<Dependency> allrecords = this.registery.getDependencyMap().getAll(resolving);
        ArrayList<Object> allObjects = new ArrayList<>();
        ResolveStrategy strategy = new TagOnlyResolveStrategy(this.registery.getDependencyMap());
        allrecords.forEach((record) -> tryCreateObject(allObjects, record, strategy));
        return (T[]) allObjects.toArray();
    }


    private Object createObject(Class resolvedType, String tagIfAny, ResolveStrategy strategy) throws Exception {
        Constructor[] constructors = resolvedType.getConstructors();
        Constructor ctor = constructors[0];
        if (ctor.getParameterCount() == 0) {
            return ctor.newInstance();
        }
        Object[] parameters = new Object[ctor.getParameterCount()];
        Parameter[] parameterTypes = ctor.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = Resolver.this.resolve(parameterTypes[i].getType(),
                    tagIfAny, strategy);
        }
        return ctor.newInstance(parameters);
    }
    
    public Registerer getRegistery(){
        return this.registery;
    }

    public DependencyDictionary getRegisteredDependancies() {
        return this.registery.getRegisteredDependencies();
    }

}
