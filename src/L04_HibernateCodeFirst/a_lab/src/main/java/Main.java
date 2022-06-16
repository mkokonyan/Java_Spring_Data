import entities.shampoo.BasicIngredient;
import entities.shampoo.BasicLabel;
import entities.shampoo.BasicShampoo;
import entities.shampoo.ProductionBatch;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni_db");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        ProductionBatch batch = new ProductionBatch(LocalDate.now());
        BasicLabel label = new BasicLabel("blue");
        BasicShampoo shampoo = new BasicShampoo("Shower", label, batch);

        BasicIngredient basicIngredient = new BasicIngredient(100, "B12");
        BasicIngredient basicIngredient2 = new BasicIngredient(2, "Violet");

        shampoo.addIngredient(basicIngredient);
        shampoo.addIngredient(basicIngredient2);

        entityManager.persist(basicIngredient);
        entityManager.persist(basicIngredient);

        entityManager.persist(batch);
        entityManager.persist(label);
        entityManager.persist(shampoo);


        ProductionBatch productionBatch = entityManager.find(ProductionBatch.class, 1);
        Set<BasicShampoo> shampoos = productionBatch.getShampoos();

        shampoos.forEach(System.out::println);


        entityManager.getTransaction().commit();
        entityManager.close();

    }

}
