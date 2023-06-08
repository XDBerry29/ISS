package com.example.bibliotecaiss.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

import static com.example.bibliotecaiss.model.CarteDbRepo.sessionFactory;

public class AbonatDbRepo{

    static SessionFactory sessionFactory;

    public AbonatDbRepo() {
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


    public Abonat findByEmail(String email) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Abonat as a WHERE a.email = :email";
        Abonat abonat = session.createQuery(hql, Abonat.class)
                .setParameter("email", email)
                .uniqueResult();
        session.close();
        return abonat;
    }

    public void save(Abonat abonat) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(abonat);
        transaction.commit();
        session.close();
    }

    public Abonat findByCNP(String cnp) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Abonat as a WHERE a.cnp = :cnp";
        Abonat abonat = session.createQuery(hql, Abonat.class)
                .setParameter("cnp", cnp)
                .uniqueResult();
        session.close();
        return abonat;
    }
}
