import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P03_ContainsEmployee {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_db");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] searchFor = scanner.nextLine().split("\\s+");

        Long employeeCount = entityManager
                .createQuery("SELECT COUNT(e) FROM Employee e" +
                                " WHERE e.firstName = :first_name" +
                                " AND e.lastName = :last_name",
                        Long.class)
                .setParameter("first_name", searchFor[0])
                .setParameter("last_name", searchFor[1])
                .getSingleResult();

        System.out.println(employeeCount > 0 ? "Yes" : "No");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
