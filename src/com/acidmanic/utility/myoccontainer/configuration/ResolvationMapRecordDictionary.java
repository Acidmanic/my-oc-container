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
import com.acidmanic.utility.myoccontainer.configuration.data.TaggedClass;
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
