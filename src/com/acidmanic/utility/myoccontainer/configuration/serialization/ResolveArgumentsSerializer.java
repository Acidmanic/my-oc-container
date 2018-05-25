/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.configuration.serialization;

import com.acidmanic.utility.myoccontainer.resolvearguments.LifetimeType;
import com.acidmanic.utility.myoccontainer.resolvearguments.ResolveArguments;

/**
 *
 * @author diego
 */
public class ResolveArgumentsSerializer  extends SimpleSerializerOf<ResolveArguments>{

    @Override
    public String serialize(ResolveArguments object) {
        return object.getTargetType().getName()
                + SEP
                + object.getLifetime().toString();
    }

    @Override
    public ResolveArguments deserialize(String[] fields, int from) {
        try {
            Class targetType = Class.forName(fields[from]);
            LifetimeType lifetimeType = LifetimeType.valueOf(fields[from+1].trim());
            return new ResolveArguments(lifetimeType, targetType);
        } catch (Exception e) {
        }
        
        return null;
    }

    @Override
    public int getFieldsnumber() {
        return 2;
    }
    
}
