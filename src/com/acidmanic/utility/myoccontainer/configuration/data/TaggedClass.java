/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration.data;

import com.acidmanic.utility.myoccontainer.exceptions.InvalidTagStringException;

/**
 *
 * @author diego
 */
public class TaggedClass {

    public static final String DEFAULT_TAG = "Default";
    
    private String tag;
    private Class type;

    public TaggedClass() {
    }

    public TaggedClass(String tag, Class type) throws Exception {
        this.type = type;
        this.setTag(tag);
    }

    
    
    public String getTag() {
        return tag;
    }

    public final void setTag(String tag) throws Exception {
        if (isValidTag(tag))
            this.tag = tag;
        else{
            throw new InvalidTagStringException();
        }
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
    
    
    public static boolean isValidTag(String tag){
        if(tag==null) return false;
        if(tag.length()==0) return false;
        if(tag.replaceAll("\\s+", "").length()!=tag.length()) return false;
        if(Character.isDigit(tag.charAt(0))) return false;
        return tag.replaceAll("\\w|\\d|_|\\.|-", "").length() == 0;
    }
}
