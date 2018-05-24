/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myoccontainer.models;

/**
 *
 * @author diego
 */
public class CarMotor implements Motor{

    private final Electrics electrics;

    private final Silanders silanders;

    public CarMotor(Electrics electrics,Silanders silanders) {
        this.electrics = electrics;
        this.silanders = silanders;
    }

    @Override
    public String description() {
        return silanders.volumeAndNumber() + "\n" + this.electrics.Charachtristics();
    }
    
    
    

    
}
