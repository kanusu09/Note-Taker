package com.fecpro;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {

    private static SessionFactory sf;

    // Static method to get the SessionFactory
    public static SessionFactory getFactory() {
        // Check if the SessionFactory is null (not initialized)
        if (sf == null) {
            try {
                // Create Configuration object and configure it
                Configuration cf = new Configuration();
                cf.configure("hibernate.cfg.xml"); // Ensure the correct path to your configuration file

                // Build the SessionFactory from the Configuration
                sf = cf.buildSessionFactory();
            } catch (Exception e) {
                // Log the exception (you can use a logging framework like Log4j or SLF4J)
                e.printStackTrace();
                throw new RuntimeException("Failed to create session factory", e);
            }
        }
        return sf;
    }

    // Static method to close the SessionFactory
    public static void closeFactory() {
        if (sf != null && sf.isOpen()) {
            sf.close();
        }
    }
}
