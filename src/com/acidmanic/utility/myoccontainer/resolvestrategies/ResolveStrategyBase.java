/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.resolvestrategies;

import com.acidmanic.utility.myoccontainer.DependancyDictionary;

/**
 *
 * @author diego
 */
public abstract class ResolveStrategyBase implements ResolveStrategy{
    
    
    protected DependancyDictionary dependancyDictionary;

    public ResolveStrategyBase(DependancyDictionary dependancyDictionary) {
        this.dependancyDictionary = dependancyDictionary;
    }
    
    
}
