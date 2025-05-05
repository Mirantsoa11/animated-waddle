package com.entreprise.assurance.beneficiaireservice.security;

public class TenantContext {
    private static final ThreadLocal<Long> current = new ThreadLocal<>();
    
    public static void setCurrentTenant(Long tenant) { 
        current.set(tenant); 
    }
    
    public static Long getCurrentTenant() { 
        return current.get(); 
    }
    
    public static void clear() {
        current.remove(); // Retire la valeur associée à ce thread
    }
}
