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

import com.acidmanic.utility.myoccontainer.configuration.data.MapRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ResolvationMapRecordDictionaryFluentBuilderAdapter
        implements ResolvationMapRecordDictionaryInterface {
    
    private final ResolvationMapRecordDictionary dependancyDictionary;
    private final ArrayList<ResolvationMapRecordBuilder> builders;
    
    public ResolvationMapRecordDictionaryFluentBuilderAdapter() {
        this.dependancyDictionary = new ResolvationMapRecordDictionary();
        this.builders = new ArrayList<>();
    }
    
    
    public synchronized ResolvationMapRecordDictionary getDictionary(){
        if(isAnyUnbuiltRecord()){
            buildUnbuiltRecords();
        }
        return this.dependancyDictionary;
    }

    private boolean isAnyUnbuiltRecord() {
        return this.builders.size()>0;
    }

    private void buildUnbuiltRecords() {
        for(ResolvationMapRecordBuilder builder:this.builders){
            try {
                this.dependancyDictionary
                    .put(builder.build());
            } catch (Exception e) {
                //Log for build failures
            }
        }
        builders.clear();
    }

    
    public ResolvationMapRecordBuilder fluent(){
        ResolvationMapRecordBuilder builder = new ResolvationMapRecordBuilder();
        this.builders.add(builder);
        return builder;
    }
    
    

    @Override
    public boolean containesAny(Class key) {
        return this.getDictionary().containesAny(key);
    }

    @Override
    public MapRecord get(Class type, String tag) {
        return this.getDictionary().get(type, tag);
    }

    @Override
    public void put(MapRecord record) {
        this.getDictionary().put(record);
    }

    @Override
    public void putAll(ResolvationMapRecordDictionary dictionary) {
        this.getDictionary().putAll(dictionary);
    }

    @Override
    public MapRecord remove(Class type, String tag) {
        return this.getDictionary().remove(type, tag);
    }

    @Override
    public MapRecord searchForAKey(Class key) {
        return this.getDictionary().searchForAKey(key);
    }

    @Override
    public void subtract(ResolvationMapRecordDictionary dictionary) {
        this.getDictionary().subtract(dictionary);
    }

    @Override
    public List<MapRecord> toList() {
        return this.getDictionary().toList();
    }

    @Override
    public List<MapRecord> getAll(Class type) {
        return this.getDictionary().getAll(type);
    }
    
    
    
    
    
    
    
    
    
}
