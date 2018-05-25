/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.resolvestrategies;

/**
 *
 * @author diego
 */
public interface ResolveStrategy {
    Class search(Class resolving,String tagIfAny);
}
