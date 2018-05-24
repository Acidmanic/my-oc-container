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
public class PowerElectrics implements Electrics{

    @Override
    public String Charachtristics() {
        return "Slow, Highpower. get hot";
    }
    
}
