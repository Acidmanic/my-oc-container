/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.utility.myoccontainer.reflection;

/**
 *
 * @author diego
 */
public class ReflectionHelper {

    public boolean implemented(Class type, Class interfaceType) {

        Class current = type;

        while (current != null) {
            for (Class iface : current.getInterfaces()) {
                if (interfaceType.equals(iface)) {
                    return true;
                }
            }
            current = current.getSuperclass();
        }
        return false;
    }
}
