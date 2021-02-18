package service;

import model.Product;
import java.util.List;

public interface IProductService extends IGeneralService<Product>{
    List<Product> findByName(String name);
}
