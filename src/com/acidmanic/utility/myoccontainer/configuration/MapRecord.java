/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.TaggedClass;
import com.acidmanic.utility.myoccontainer.resolvearguments.ResolveArguments;

/**
 *
 * @author diego
 */
public class MapRecord {
    private TaggedClass keyObject;
    private ResolveArguments valueObject;

    public MapRecord(TaggedClass keyObject, ResolveArguments valueObject) {
        this.keyObject = keyObject;
        this.valueObject = valueObject;
    }

    public MapRecord() {
    }

    public TaggedClass getKeyObject() {
        return keyObject;
    }

    public void setKeyObject(TaggedClass keyObject) {
        this.keyObject = keyObject;
    }

    public ResolveArguments getValueObject() {
        return valueObject;
    }

    public void setValueObject(ResolveArguments valueObject) {
        this.valueObject = valueObject;
    }
    
    
}
