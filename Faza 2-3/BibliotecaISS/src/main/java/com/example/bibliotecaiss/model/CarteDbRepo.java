package com.example.bibliotecaiss.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class CarteDbRepo{
    static SessionFactory sessionFactory;

    public CarteDbRepo() {
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

    public void save(Carte c) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(c);
        transaction.commit();
        session.close();
    }


    public List<Carte> findAll() {
        Session session = sessionFactory.openSession();
        List<Carte> carti = session.createQuery("FROM Carte", Carte.class).list();
        session.close();
        return carti;
    }


    public Carte findCarteByTitluAndAutor(String titlu, String autor) {
        Session session = sessionFactory.openSession();
        String hql = "FROM Carte as c WHERE c.titlu = :titlu AND c.autor = :autor";
        Carte carte = session.createQuery(hql, Carte.class)
                .setParameter("titlu", titlu)
                .setParameter("autor", autor)
                .uniqueResult();
        session.close();
        return carte;
    }

}
