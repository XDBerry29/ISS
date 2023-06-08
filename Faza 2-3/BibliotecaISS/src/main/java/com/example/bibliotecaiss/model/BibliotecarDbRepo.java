package com.example.bibliotecaiss.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BibliotecarDbRepo{

    static SessionFactory sessionFactory;

    public BibliotecarDbRepo() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exceptie " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    public Bibliotecar findByUsername(String username) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Bibliotecar as b WHERE b.username = :username";
        Bibliotecar bibliotecar = session.createQuery(hql, Bibliotecar.class)
                .setParameter("username", username)
                .uniqueResult();
        session.close();
        return bibliotecar;
    }
}
