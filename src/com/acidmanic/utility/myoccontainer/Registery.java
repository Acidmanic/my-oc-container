package com.acidmanic.utility.myoccontainer;

import com.acidmanic.utility.myoccontainer.configuration.ConfigurationFile;
import com.acidmanic.utility.myoccontainer.configuration.DependencyBuilder;
import com.acidmanic.utility.myoccontainer.configuration.DependencyDictionary;
import com.acidmanic.utility.myoccontainer.configuration.DependencyDictionaryFluentBuilderAdapter;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;
import java.util.logging.Level;
import java.util.logging.Logger;

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
/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Registery implements Registerer {

    private final DependencyDictionaryFluentBuilderAdapter dependanciesMap
            = new DependencyDictionaryFluentBuilderAdapter();
    private final DependencyDictionary primitives;

    public Registery() {
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
    public final void register(Class resolving, Class resolved, String tag, LifetimeType lifetime) throws Exception {
        this.dependanciesMap.put(new DependencyBuilder()
                .bind(resolving).to(resolved).tagged(tag).livesAsA(lifetime)
                .build());
    }

    @Override
    public final DependencyBuilder register() {
        return this.dependanciesMap.fluent();
    }

    public DependencyDictionary getDependencyMap() {
        return this.dependanciesMap.getDictionary();
    }

    public DependencyDictionary getRegisteredDependencies() {
        DependencyDictionary ret = this.dependanciesMap.getDictionary().clone();
        ret.subtract(primitives);
        return ret;
    }

    public Registery register(ConfigurationFile configuration) {
        this.dependanciesMap.putAll(configuration.getDependancyMap());
        return this;
    }

    public Registery register(String filepath) {
        this.register(new ConfigurationFile(filepath));
        return this;
    }
}
