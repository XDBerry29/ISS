package com.example.bibliotecaiss.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class ImprumutDbRepo {
    static SessionFactory sessionFactory;

    public ImprumutDbRepo() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    public void save(Imprumut i) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(i);
        transaction.commit();
        session.close();
    }

    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Imprumut imprumut = findByID(id);
        if (imprumut != null) {
            session.delete(imprumut);
            transaction.commit();
        }
        session.close();
    }


    public Imprumut findByID(Long id) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Imprumut as i WHERE i.id = :id";
        Imprumut imprumut = session.createQuery(hql, Imprumut.class)
                .setParameter("id", id)
                .uniqueResult();
        session.close();
        return imprumut;
    }

    public Imprumut findByCodEx(Long cod_ex) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Imprumut as i WHERE i.exemplar.cod_unic = :cod_ex";
        Imprumut imprumut = session.createQuery(hql, Imprumut.class)
                .setParameter("cod_ex", cod_ex)
                .uniqueResult();
        session.close();
        return imprumut;
    }
}
