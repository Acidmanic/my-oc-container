/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;

import com.acidmanic.utility.myoccontainer.resolvearguments.ResolveArguments;

/**
 *
 * @author diego
 */
public class MapRecord {
    private TaggedClass taggedClass;
    private ResolveArguments resolveArguments;

    public MapRecord(TaggedClass taggedClass, ResolveArguments resolveArguments) {
        this.taggedClass = taggedClass;
        this.resolveArguments = resolveArguments;
    }

    public MapRecord() {
    }

    public TaggedClass getTaggedClass() {
        return taggedClass;
    }

    public void setTaggedClass(TaggedClass taggedClass) {
        this.taggedClass = taggedClass;
    }

    public ResolveArguments getResolveArguments() {
        return resolveArguments;
    }

    public void setResolveArguments(ResolveArguments resolveArguments) {
        this.resolveArguments = resolveArguments;
    }
    
    
}
