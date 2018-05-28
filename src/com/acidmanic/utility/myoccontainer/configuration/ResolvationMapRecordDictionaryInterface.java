/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.configuration.data.MapRecord;
import java.util.List;

/**
 *
 * @author diego
 */
public interface ResolvationMapRecordDictionaryInterface {

    
    boolean containesAny(Class key);

    MapRecord get(Class type, String tag);

    void put(MapRecord record);

    void putAll(ResolvationMapRecordDictionary dictionary);

    MapRecord remove(Class type, String tag);

    MapRecord searchForAKey(Class key);

    void subtract(ResolvationMapRecordDictionary dictionary);

    List<MapRecord> toList();
    
    List<MapRecord> getAll(Class type);
    
}
