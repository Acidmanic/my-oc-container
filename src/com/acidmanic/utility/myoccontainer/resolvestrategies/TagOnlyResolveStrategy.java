/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.resolvestrategies;

import com.acidmanic.utility.myoccontainer.DependancyDictionary;
import com.acidmanic.utility.myoccontainer.configuration.TaggedClass;
import com.acidmanic.utility.myoccontainer.resolvearguments.ResolveArguments;

/**
 *
 * @author diego
 */
public class TagOnlyResolveStrategy extends ResolveStrategyBase{

    public TagOnlyResolveStrategy(DependancyDictionary dependancyDictionary) {
        super(dependancyDictionary);
    }

    @Override
    public ResolveArguments search(Class resolving, String tagIfAny) {
        try {
            return this.dependancyDictionary.get(new TaggedClass(tagIfAny, resolving));
        } catch (Exception e) {
        }
        return null;
    }
    
}
