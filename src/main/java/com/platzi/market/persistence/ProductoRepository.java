package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    private final ProductCrudRepository productCrudRepository;
    private final ProductMapper mapper;

    public ProductoRepository(ProductCrudRepository productCrudRepository, ProductMapper mapper) {
        this.productCrudRepository = productCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productCrudRepository.findByIdCategoryOrderByNameAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    public Optional<List<Product>> getScarce(int stock, boolean status) {
        Optional<List<Producto>> productos = productCrudRepository.findByStockLessThanAndStatus(stock, status);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getOne(int productId) {
        return productCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productCrudRepository.save(producto));
    }

    @Override
    public void delete(int idProduct) {
        productCrudRepository.deleteById(idProduct);
    }
}
