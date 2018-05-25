
package com.acidmanic.utility.myoccontainer.resolvearguments;

/**
 *
 * @author diego
 */
public class ResolveArguments {
    
    private LifetimeType lifetime;
    private Class targetType;

    public ResolveArguments(Class targetType) {
        this.targetType = targetType;
        this.lifetime = LifetimeType.Transient;
    }

    public ResolveArguments(LifetimeType lifetime, Class targetType) {
        this.lifetime = lifetime;
        this.targetType = targetType;
    }

    public Class getTargetType() {
        return targetType;
    }

    public void setTargetType(Class targetType) {
        this.targetType = targetType;
    }
    
    public LifetimeType getLifetime() {
        return lifetime;
    }

    public void setLifetime(LifetimeType lifetime) {
        this.lifetime = lifetime;
    }
    
    
}
