/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer;

import com.acidmanic.utility.myoccontainer.configuration.ResolvationMapRecordBuilder;
import com.acidmanic.utility.myoccontainer.lifetimemanagement.LifetimeType;

/**
 *
 * @author diego
 */
public interface Registerer {

    void register(Class resolving, Class resolved);

    void register(Class resolving, Class resolved, String tag) throws Exception;

    void register(Class resolving, Class resolved, LifetimeType lifetime) throws Exception;

    void register(Class resolving, Class resolved, String tag, LifetimeType lifetime) throws Exception;

    ResolvationMapRecordBuilder register();
    
}
