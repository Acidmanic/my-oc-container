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

import com.acidmanic.utility.myoccontainer.configuration.data.Dependency;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class DependencyDictionaryFluentBuilderAdapter
        implements DependencyDictionaryInterface {
    
    private final DependencyDictionary dependancyDictionary;
    private final ArrayList<DependencyBuilder> builders;
    
    public DependencyDictionaryFluentBuilderAdapter() {
        this.dependancyDictionary = new DependencyDictionary();
        this.builders = new ArrayList<>();
    }
    
    
    public synchronized DependencyDictionary getDictionary(){
        if(isAnyUnbuiltRecord()){
            buildUnbuiltRecords();
        }
        return this.dependancyDictionary;
    }

    private boolean isAnyUnbuiltRecord() {
        return this.builders.size()>0;
    }

    private void buildUnbuiltRecords() {
        for(DependencyBuilder builder:this.builders){
            try {
                this.dependancyDictionary
                    .put(builder.build());
            } catch (Exception e) {
                //Log for build failures
            }
        }
        builders.clear();
    }

    
    public DependencyBuilder fluent(){
        DependencyBuilder builder = new DependencyBuilder();
        this.builders.add(builder);
        return builder;
    }
    
    

    @Override
    public boolean containesAny(Class key) {
        return this.getDictionary().containesAny(key);
    }

    @Override
    public Dependency get(Class type, String tag) {
        return this.getDictionary().get(type, tag);
    }

    @Override
    public void put(Dependency record) {
        this.getDictionary().put(record);
    }

    @Override
    public void putAll(DependencyDictionary dictionary) {
        this.getDictionary().putAll(dictionary);
    }

    @Override
    public Dependency remove(Class type, String tag) {
        return this.getDictionary().remove(type, tag);
    }

    @Override
    public Dependency searchForAKey(Class key) {
        return this.getDictionary().searchForAKey(key);
    }

    @Override
    public void subtract(DependencyDictionary dictionary) {
        this.getDictionary().subtract(dictionary);
    }

    @Override
    public List<Dependency> toList() {
        return this.getDictionary().toList();
    }

    @Override
    public List<Dependency> getAll(Class type) {
        return this.getDictionary().getAll(type);
    }
    
    
    
    
    
    
    
    
    
}
