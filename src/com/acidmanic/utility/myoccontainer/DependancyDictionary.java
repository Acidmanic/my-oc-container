/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author diego
 */
public class DependancyDictionary {
    
    private HashMap<String, Class> map;
    private ArrayList<TaggedClass> keys;
    public DependancyDictionary() {
        this.map = new HashMap<>();
        this.keys= new ArrayList<>();
    }
    
    private String getUniqueString(TaggedClass t){
        return getUniqueString(t.getType(),t.getTag());
    }
    
    private String getUniqueString(Class c,String t){
        return c.getName()+";;;"+t;
    }
    
    public Class put(TaggedClass tfrom,Class tto){
        this.keys.add(tfrom);
        return this.map.put(getUniqueString(tfrom), tto);
    }
    
    @SuppressWarnings("element-type-mismatch")
    public Class remove(TaggedClass key){
        this.keys.remove(key);
        return this.map.remove(key);
    }
    
    public boolean containsKey(TaggedClass key){
        return this.map.containsKey(getUniqueString(key));
    }
    
    
    public Class get(TaggedClass key){
        return this.map.get(getUniqueString(key));
    }
    
    
    public List<TaggedClass> keySet(){
        return this.keys;
    }
    
    public TaggedClass searchForAKey(Class key){
        for(TaggedClass t: this.keys){
            if(key.getName().compareTo(t.getType().getName())==0){
                return t;
            }
        }
        return null;
    }
    
    public Class get(Class key){
        TaggedClass closestKey = searchForAKey(key);
        if(closestKey!=null){
            return this.get(closestKey);
        }
        return null;
    }
    
    public boolean containesAny(Class key){
        return searchForAKey(key)!=null;
    }
    
    
    @Override
    @SuppressWarnings({"CloneDoesntCallSuperClone", "CloneDeclaresCloneNotSupported"})
    public DependancyDictionary clone(){
        DependancyDictionary ret = new DependancyDictionary();
        ret.keys = (ArrayList<TaggedClass>) this.keys.clone();
        ret.map = (HashMap<String, Class>) this.map.clone();
        return ret;
    }
}