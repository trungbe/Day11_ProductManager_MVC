package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Shoe Nike", 100, "Just Do It", "Nike"));
        products.put(2, new Product(2, "T-shirt Adidas", 300, "Impossible is Nothing", "Adidas"));
        products.put(3, new Product(3, "Vans", 200, "Vans of the walk", "Vans"));
        products.put(4, new Product(4, "Bitis shoe", 500, "Nang niu ban chan Viet", "Bitis"));
        products.put(5, new Product(5, "Fila", 600, "Fila lala", "Fila"));
        products.put(6, new Product(6, "Puma", 900, "Unleash your wild side", "Puma"));
        products.put(7, new Product(7, "Reebok", 800, "Reebok ", "Reebok International Limited"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product save(Product product) {
        return products.put(product.getId(), product);
    }

    @Override
    public Product updateById(int id, Product product) {
        return products.replace(id, product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void deleteById(int id) {
        products.remove(id);
    }

    @Override
    public Product findByName(String name) {
        return products.get(name);
    }
}
