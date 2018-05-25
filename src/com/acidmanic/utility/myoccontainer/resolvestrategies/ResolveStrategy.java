/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.resolvestrategies;

import com.acidmanic.utility.myoccontainer.resolvearguments.ResolveArguments;

/**
 *
 * @author diego
 */
public interface ResolveStrategy {
    ResolveArguments search(Class resolving,String tagIfAny);
}
