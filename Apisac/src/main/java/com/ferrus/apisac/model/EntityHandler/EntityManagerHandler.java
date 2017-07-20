/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferrus.apisac.model.EntityHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Ramiro
 */
public enum EntityManagerHandler {

    INSTANCE;

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("user-persistence-unit");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();

    public void open() {
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityTransaction getEntityTransaction() {
        return entityTransaction;
    }

    public void shutdown() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
