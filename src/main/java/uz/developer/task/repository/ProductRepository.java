package uz.developer.task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.developer.task.models.entity.Product;
import uz.developer.task.models.projection.ProductProjection;
import uz.developer.task.models.projection.forTask.BulkProductsProjection;
import uz.developer.task.models.projection.forTask.HighDemandProductsProjection;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
   7.	Products that were ordered more than 10 times in total, by taking into account the
    quantities in which they appear in the order details. Return the product code and the
    total number of times it was ordered.
   */
    @Query(value = "select p.id as productId, sum(d.quantity) as allQuantity " +
            "from product p join detail d on p.id = d.pr_id " +
            "group by p.id having sum(d.quantity)>:value", nativeQuery = true)
    Page<HighDemandProductsProjection> findAllHighDemandProducts(@Param("value") Integer value,Pageable pageable);


    /*
    8.	Products that are usually ordered in bulk: whenever one of these products is ordered, it
    is ordered in a quantity that on average is equal to or greater than 8. For each such
    product, return product code and price.
    */
    @Query(value = "select  distinct p.id as productId,p.price as price from product p " +
            "    join detail d on p.id = d.pr_id where d.quantity>=:quantity", nativeQuery = true)
    Page<BulkProductsProjection> findAllBulkProducts(@Param("quantity") Integer quantity,Pageable pageable);


    @Query(value = "select p.id          as id,\n" +
            "       c.name        as country,\n" +
            "       p.name        as productName,\n" +
            "       c2.name       as category,\n" +
            "       p.description as description,\n" +
            "       p.price       as price,\n" +
            "       p.photo       as photo\n" +
            "from product p\n" +
            "         join country c on p.name = c.name\n" +
            "         join category c2 on c2.id = p.category_id", nativeQuery = true)
    Page<ProductProjection> getAll(Pageable pageable);

    @Query(value = "select p.id          as id,\n" +
            "       c.name        as country,\n" +
            "       p.name        as productName,\n" +
            "       c2.name       as category,\n" +
            "       p.description as description,\n" +
            "       p.price       as price,\n" +
            "       p.photo       as photo\n" +
            "from product p\n" +
            "         join country c on p.name = c.name\n" +
            "         join category c2 on c2.id = p.category_id where p.id=:productId",nativeQuery = true)
    ProductProjection getByProductId(@Param("productId") Long productId);


}
