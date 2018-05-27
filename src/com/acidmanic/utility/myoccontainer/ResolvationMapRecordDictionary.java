/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer;

import com.acidmanic.utility.myoccontainer.configuration.MapRecord;
import com.acidmanic.utility.myoccontainer.configuration.TaggedClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 */
public class ResolvationMapRecordDictionary implements ResolvationMapRecordDictionaryInterface {

    private ArrayList<MapRecord> mappingRecords;
    private HashMap<String, MapRecord> tagIndexes;
    private HashMap<String,ArrayList<MapRecord>> recordsPerClass;
    public ResolvationMapRecordDictionary() {
        this.mappingRecords = new ArrayList<>();
        this.tagIndexes = new HashMap<>();
        this.recordsPerClass = new HashMap<>();
    }

    private String getUniqueString(MapRecord record) {
        return getUniqueString(record.getTaggedClass());
    }

    private String getUniqueString(TaggedClass t) {
        return getUniqueString(t.getType(), t.getTag());
    }

    private String getUniqueString(Class c, String t) {
        if (c == null) {
            return "NULL;;;" + t;
        } else {
            return c.getName() + ";;;" + t;
        }
    }

    @Override
    public void put(MapRecord record) {
        this.mappingRecords.add(record);
        this.tagIndexes.put(getUniqueString(record), record);
        String classname = record.getTaggedClass().getType().getName();
        if(this.recordsPerClass.containsKey(classname)==false){
            this.recordsPerClass.put(classname, new ArrayList<>());
        }
        this.recordsPerClass.get(classname).add(record);
    }

    @Override
    public MapRecord get(Class type, String tag) {
        return this.tagIndexes.get(getUniqueString(type, tag));
    }

    @Override
    public MapRecord searchForAKey(Class key) {
        for (MapRecord record : this.mappingRecords) {
            if (key.getName().compareTo(record.getTaggedClass().getType().getName()) == 0) {
                return record;
            }
        }
        return null;
    }

    @Override
    public boolean containesAny(Class key) {
        return searchForAKey(key) != null;
    }

    @Override
    public MapRecord remove(Class type, String tag) {
        MapRecord record = get(type, tag);
        String key = getUniqueString(record);
        this.mappingRecords.remove(record);
        this.tagIndexes.remove(key);
        return record;
    }

    @Override
    @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneDeclaresCloneNotSupported"})
    public ResolvationMapRecordDictionary clone() {
        ResolvationMapRecordDictionary ret = new ResolvationMapRecordDictionary();
        ret.mappingRecords = (ArrayList<MapRecord>) this.mappingRecords.clone();
        ret.tagIndexes = (HashMap<String, MapRecord>) this.tagIndexes.clone();
        ret.recordsPerClass = (HashMap<String, ArrayList<MapRecord>>) this.recordsPerClass.clone();
        return ret;
    }

    

    @Override
    public List<MapRecord> toList() {
        return (List<MapRecord>) this.mappingRecords.clone();
    }

    @Override
    public void putAll(ResolvationMapRecordDictionary dictionary) {
        dictionary.mappingRecords.forEach((record)-> this.put(record));
    }

    @Override
    public void subtract(ResolvationMapRecordDictionary dictionary) {
        this.mappingRecords.removeAll(dictionary.mappingRecords);
    }

    @Override
    public List<MapRecord> getAll(Class type) {
        return this.recordsPerClass.get(type.getName());
    }
}
