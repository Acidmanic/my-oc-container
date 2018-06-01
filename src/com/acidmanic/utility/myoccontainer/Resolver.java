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
import com.acidmanic.utility.myoccontainer.configuration.ConfigurationFile;
import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import com.acidmanic.utility.myoccontainer.configuration.DependencyDictionaryFluentBuilderAdapter;
import com.acidmanic.utility.myoccontainer.configuration.DependencyBuilder;
import com.acidmanic.utility.myoccontainer.exceptions.UnableToResolveException;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeManagerInterceptor;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import com.acidmanic.utility.myoccontainer.configuration.data.ResolveParameters;
import com.acidmanic.utility.myoccontainer.resolvestrategies.DefaultOrAnyResolveStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.acidmanic.utility.myoccontainer.resolvestrategies.ResolveStrategy;
import com.acidmanic.utility.myoccontainer.resolvestrategies.TagOnlyResolveStrategy;
import com.acidmanic.utility.myoccontainer.resolvestrategies.TagOrDefaultResolveStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Resolver implements Registerer {


    private final DependencyDictionaryFluentBuilderAdapter dependanciesMap = 
            new DependencyDictionaryFluentBuilderAdapter();
    private final DependencyDictionary primitives;
    private final LifetimeManagerInterceptor lifetimeManager = new LifetimeManagerInterceptor();
    public Resolver() {
        register(Long.class, Long.class);
        register(long.class, Long.class);
        register(Integer.class, Integer.class);
        register(int.class, Integer.class);
        register(Short.class, Short.class);
        register(short.class, Short.class);
        register(Double.class, Double.class);
        register(double.class, Double.class);
        register(Float.class, Float.class);
        register(float.class, Float.class);
        register(Byte.class, Byte.class);
        register(byte.class, Byte.class);
        register(String.class, String.class);
        primitives = (DependencyDictionary) this.dependanciesMap.getDictionary().clone();
    }

    public Resolver(ConfigurationFile configuration) {
        this();
        this.dependanciesMap.putAll(configuration.getDependancyMap());
    }

    public Resolver(String filepath) {
        this(new ConfigurationFile(filepath));
    }

    @Override
    public final void register(Class resolving, Class resolved) {
        try {
            this.dependanciesMap.put(new DependencyBuilder()
                    .bind(resolving).to(resolved)
                    .build());
        } catch (Exception ex) {
            Logger.getLogger(Resolver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public final void register(Class resolving, Class resolved, String tag) throws Exception {
        this.dependanciesMap.put(new DependencyBuilder()
        .bind(resolving).to(resolved).tagged(tag)
                .build());
    }
    
    @Override
    public final void register(Class resolving, Class resolved, LifetimeType lifetime) throws Exception {
        this.dependanciesMap.put(new DependencyBuilder()
        .bind(resolving).to(resolved).livesAsA(lifetime)
                .build());
    }
    
    @Override
    public final void register(Class resolving, Class resolved,String tag, LifetimeType lifetime) throws Exception {
        this.dependanciesMap.put(new DependencyBuilder()
        .bind(resolving).to(resolved).tagged(tag).livesAsA(lifetime)
                .build());
    }
    
    @Override
    public final DependencyBuilder register(){
        return this.dependanciesMap.fluent();
    }
    
    public Object resolve(Class type) throws Exception {
        return Resolver.this.resolve(type, ResolveSource.DEFAULT_TAG, 
                new DefaultOrAnyResolveStrategy(dependanciesMap.getDictionary()));
    }

    public Object resolveByTagOnly(Class type, String tag) throws Exception {
        return Resolver.this.resolve(type, tag, 
                new TagOnlyResolveStrategy(dependanciesMap.getDictionary()));
    }

    public Object resolve(Class type, String tag) throws Exception {
        return Resolver.this.resolve(type, tag, 
                new TagOrDefaultResolveStrategy(dependanciesMap.getDictionary()));
    }

    private Object resolve(Class resolving, String tagIfAny, ResolveStrategy strategy) throws Exception {
        ResolveParameters resolved = strategy.search(resolving, tagIfAny);
        if (resolved == null) {
            throw new UnableToResolveException();
        }
        
        return lifetimeManager.makeObject(resolved, 
                () -> createObject(resolved.getTargetType(), tagIfAny, strategy));

    }
    
    public void install(Installer installer){
        installer.configure(this);
    }
    
    private void tryCreateObject(ArrayList<Object> list,
            Dependency record,
            ResolveStrategy strategy){
        try {
            list.add(
                    createObject(record.getResolveArguments().getTargetType(),
                            record.getTaggedClass().getTag(),strategy)
            );
        } catch (Exception e) {        }
    }
    
    public Object[] resolveAll(Class resolving){
        List<Dependency> allrecords = this.dependanciesMap.getAll(resolving);
        ArrayList<Object> allObjects = new ArrayList<>();
        ResolveStrategy strategy = new 
            TagOnlyResolveStrategy(this.dependanciesMap.getDictionary());
        allrecords.forEach((record) -> tryCreateObject(allObjects, record,strategy));
        return allObjects.toArray();
    }

    public DependencyDictionary getRegisteredDependancies() {
        DependencyDictionary ret
                = (DependencyDictionary) this.dependanciesMap
                        .getDictionary().clone();
        ret.subtract(primitives);
        return ret;
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
}
