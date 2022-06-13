import entities.Town;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class P02_ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_db");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query from_town = entityManager
                .createQuery("SELECT t FROM Town t", Town.class);
        List<Town> resultList = from_town.getResultList();

        for (Town town : resultList) {
            String name = town.getName();
            if (name.length() <= 5) {
                String toUpper = name.toUpperCase();
                town.setName(toUpper);

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}