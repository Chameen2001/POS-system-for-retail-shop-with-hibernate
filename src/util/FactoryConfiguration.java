package util;

import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configure = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class);
        sessionFactory=configure.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration=factoryConfiguration==null?new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
