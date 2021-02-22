package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Producto, Integer> {

    //    @Query(value = "SELECT * FROM productos WHERE id_producto = ?", nativeQuery = true);
    List<Producto> findByIdCategoryOrderByNameAsc(int idCategory);

    Optional<List<Producto>> findByStockLessThanAndStatus(int stock, boolean status);

}
