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
package com.acidmanic.utility.myoccontainer.configuration.data;

import com.acidmanic.utility.myoccontainer.exceptions.InvalidTagStringException;

/**
 *
 * @author diego
 */
public class ResolveSource {

    public static final String DEFAULT_TAG = "Default";
    
    private String tag;
    private Class type;

    public ResolveSource() {
    }

    public ResolveSource(String tag, Class type) throws Exception {
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
