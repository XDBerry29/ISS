package com.example.bibliotecaiss.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class ExemplarDbRepo {
    static SessionFactory sessionFactory;

    public ExemplarDbRepo() {
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

    public void save(Exemplar e) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(e);
        transaction.commit();
        session.close();
    }


    public List<Exemplar> findAll() {
        Session session = sessionFactory.openSession();
        List<Exemplar> exemplare = session.createQuery("FROM Exemplar", Exemplar.class).list();
        session.close();
        return exemplare;
    }

    public Exemplar findByID(Long id) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Exemplar as e WHERE e.cod_unic = :id";
        Exemplar exemplar = session.createQuery(hql, Exemplar.class)
                .setParameter("id", id)
                .uniqueResult();
        session.close();
        return exemplar;
    }
}
