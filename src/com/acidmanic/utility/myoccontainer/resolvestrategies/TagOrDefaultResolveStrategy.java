/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.resolvestrategies;

import com.acidmanic.utility.myoccontainer.ResolvationMapRecordDictionary;
import com.acidmanic.utility.myoccontainer.configuration.TaggedClass;
import com.acidmanic.utility.myoccontainer.configuration.ResolveArguments;

/**
 *
 * @author diego
 */
public class TagOrDefaultResolveStrategy extends ResolveStrategyBase {

    public TagOrDefaultResolveStrategy(ResolvationMapRecordDictionary dependancyDictionary) {
        super(dependancyDictionary);
    }

    @Override
    public ResolveArguments search(Class resolving, String tagIfAny) {
        ResolveArguments ret = null;
        try {
            ret = this.dependancyDictionary.get(resolving, tagIfAny)
                    .getResolveArguments();
        } catch (Exception e) {
        }
        if (ret == null) {
            try {
                ret = this.dependancyDictionary.get(resolving, TaggedClass.DEFAULT_TAG)
                        .getResolveArguments();
            } catch (Exception e) {
            }
        }
        return ret;
    }

}
