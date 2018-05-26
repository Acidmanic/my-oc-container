/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer;

import com.acidmanic.utility.myoccontainer.configuration.TaggedClass;
import com.acidmanic.utility.myoccontainer.configuration.ConfigurationFile;
import com.acidmanic.utility.myoccontainer.configuration.DependancyDictionaryFluentBuilderAdapter;
import com.acidmanic.utility.myoccontainer.configuration.MapRecord;
import com.acidmanic.utility.myoccontainer.configuration.MapRecordBuilder;
import com.acidmanic.utility.myoccontainer.exceptions.UnableToResolveException;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeManagerInterceptor;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import com.acidmanic.utility.myoccontainer.configuration.ResolveArguments;
import com.acidmanic.utility.myoccontainer.resolvestrategies.DefaultOrAnyResolveStrategy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.acidmanic.utility.myoccontainer.resolvestrategies.ResolveStrategy;
import com.acidmanic.utility.myoccontainer.resolvestrategies.TagOnlyResolveStrategy;
import com.acidmanic.utility.myoccontainer.resolvestrategies.TagOrDefaultResolveStrategy;
import java.util.Dictionary;
import jdk.nashorn.internal.runtime.ArgumentSetter;

/**
 *
 * @author diego
 */
public class Resolver {


    private final DependancyDictionaryFluentBuilderAdapter dependanciesMap = 
            new DependancyDictionaryFluentBuilderAdapter();
    private final DependancyDictionary primitives;
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
        primitives = (DependancyDictionary) this.dependanciesMap.getDictionary().clone();
    }

    public Resolver(ConfigurationFile configuration) {
        this();
        this.dependanciesMap.putAll(configuration.getDependancyMap());
    }

    public Resolver(String filepath) {
        this(new ConfigurationFile(filepath));
    }

    public final void register(Class resolving, Class resolved) {
        try {
            this.dependanciesMap.put(new MapRecordBuilder()
                    .bind(resolving).to(resolved)
                    .build());
        } catch (Exception ex) {
            Logger.getLogger(Resolver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void register(Class resolving, Class resolved, String tag) throws Exception {
        this.dependanciesMap.put(new MapRecordBuilder()
        .bind(resolving).to(resolved).tagged(tag)
                .build());
    }
    
    public final void register(Class resolving, Class resolved, LifetimeType lifetime) throws Exception {
        this.dependanciesMap.put(new MapRecordBuilder()
        .bind(resolving).to(resolved).livesAsA(lifetime)
                .build());
    }
    
    public final void register(Class resolving, Class resolved,String tag, LifetimeType lifetime) throws Exception {
        this.dependanciesMap.put(new MapRecordBuilder()
        .bind(resolving).to(resolved).tagged(tag).livesAsA(lifetime)
                .build());
    }
    
    
    public Object resolve(Class type) throws Exception {
        return Resolver.this.resolve(type, TaggedClass.DEFAULT_TAG, 
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
        ResolveArguments resolved = strategy.search(resolving, tagIfAny);
        if (resolved == null) {
            throw new UnableToResolveException();
        }
        
        return lifetimeManager.makeObject(resolved, 
                () -> createObject(resolved.getTargetType(), tagIfAny, strategy));

    }

    public DependancyDictionary getRegisteredDependancies() {
        DependancyDictionary ret
                = (DependancyDictionary) this.dependanciesMap
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
