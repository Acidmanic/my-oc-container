/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.DependancyDictionary;
import com.acidmanic.utility.myoccontainer.RecordmapDictionary;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class DependancyDictionaryFluentBuilderAdapter implements RecordmapDictionary {
    
    private final DependancyDictionary dependancyDictionary;
    private final ArrayList<MapRecordBuilder> builders;
    
    public DependancyDictionaryFluentBuilderAdapter() {
        this.dependancyDictionary = new DependancyDictionary();
        this.builders = new ArrayList<>();
    }
    
    
    public synchronized DependancyDictionary getDictionary(){
        if(isAnyUnbuiltRecord()==false){
            buildUnbuiltRecords();
        }
        return this.dependancyDictionary;
    }

    private boolean isAnyUnbuiltRecord() {
        return this.builders.size()>0;
    }

    private void buildUnbuiltRecords() {
        for(MapRecordBuilder builder:this.builders){
            try {
                this.dependancyDictionary
                    .put(builder.build());
            } catch (Exception e) {
                //Log for build failures
            }
        }
        builders.clear();
    }

    
    public MapRecordBuilder fluent(){
        MapRecordBuilder builder = new MapRecordBuilder();
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
    public void putAll(DependancyDictionary dictionary) {
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
    public void subtract(DependancyDictionary dictionary) {
        this.getDictionary().subtract(dictionary);
    }

    @Override
    public List<MapRecord> toList() {
        return this.getDictionary().toList();
    }
    
    
    
    
    
    
    
    
    
}
