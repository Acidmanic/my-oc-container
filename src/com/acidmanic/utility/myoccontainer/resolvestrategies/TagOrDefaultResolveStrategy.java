/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.resolvestrategies;

import com.acidmanic.utility.myoccontainer.DependancyDictionary;
import com.acidmanic.utility.myoccontainer.Resolver;
import com.acidmanic.utility.myoccontainer.TaggedClass;

/**
 *
 * @author diego
 */
public class TagOrDefaultResolveStrategy extends ResolveStrategyBase {

    public TagOrDefaultResolveStrategy(DependancyDictionary dependancyDictionary) {
        super(dependancyDictionary);
    }

    @Override
    public Class search(Class resolving, String tagIfAny) {
        Class ret = null;
        try {
            ret = this.dependancyDictionary.get(new TaggedClass(tagIfAny, resolving));
        } catch (Exception e) {
        }
        if (ret == null) {
            try {
                ret = this.dependancyDictionary.get(new TaggedClass(
                        Resolver.DEFAULT_TAG, resolving));
            } catch (Exception e) {
            }
        }
        return ret;
    }

}
