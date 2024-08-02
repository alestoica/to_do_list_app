package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed())
            sessionFactory = createNewSessionFactory();
        return sessionFactory;
    }

    private static SessionFactory createNewSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            return configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was an error building the factory");
        }
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
