/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.models.animals;

/**
 *
 * @author diego
 */
public abstract class AnimalBase implements Animal {
    
    public AnimalBase() {
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    
}
