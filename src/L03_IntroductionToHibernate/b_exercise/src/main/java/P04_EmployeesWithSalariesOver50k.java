import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class P04_EmployeesWithSalariesOver50k {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_db");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();


        List<String> resultList = entityManager
                .createQuery("SELECT e.firstName FROM Employee e" +
                                " WHERE e.salary > 50000",
                        String.class)
                .getResultList();

        System.out.println(String.join("\n", resultList));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
