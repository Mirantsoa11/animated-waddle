package com.entreprise.assurance.employeservice.security;

public class TenantContext {
    private static final ThreadLocal<Long> current = new ThreadLocal<>();
    public static void setCurrentTenant(Long tenant) { current.set(tenant); }
    public static Long getCurrentTenant() { return current.get(); }
}
