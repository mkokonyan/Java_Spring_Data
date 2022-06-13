import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P07_AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_db");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        entityManager
                .createQuery("SELECT a FROM Address a" +
                        " ORDER BY a.employees.size DESC ",
                        Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
