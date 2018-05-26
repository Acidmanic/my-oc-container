/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;

/**
 *
 * @author diego
 */
public class MapRecordBuilder {

    private Class resolvingType;
    private Class resolvedType;
    private String tag;
    private LifetimeType lifetimeType;

    public MapRecordBuilder() {
        this.resolvingType = Object.class;
        this.resolvedType = Object.class;
        this.tag = TaggedClass.DEFAULT_TAG;
        this.lifetimeType = LifetimeType.Transient;
    }

    public MapRecordBuilder bind(Class type) {
        this.resolvingType = type;
        return this;
    }

    public MapRecordBuilder to(Class type) {
        this.resolvedType = type;
        return this;
    }

    public MapRecordBuilder tagged(String tag) {
        this.tag = tag;
        return this;
    }

    public MapRecordBuilder livesAsA(LifetimeType lifetime) {
        this.lifetimeType = lifetime;
        return this;
    }

    public MapRecord build() throws Exception {
        return new MapRecord(
                new TaggedClass(this.tag, this.resolvingType), 
                new ResolveArguments(this.lifetimeType, this.resolvedType));
    }
}
