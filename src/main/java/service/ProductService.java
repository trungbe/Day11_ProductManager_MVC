package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static Map<Integer, Product> products;

//    static {
//        products = new HashMap<>();
//        products.put(1, new Product(1, "Shoe Nike", 100, "Just Do It", "Nike"));
//        products.put(2, new Product(2, "T-shirt Adidas", 300, "Impossible is Nothing", "Adidas"));
//        products.put(3, new Product(3, "Vans", 200, "Vans of the walk", "Vans"));
//        products.put(4, new Product(4, "Bitis shoe", 500, "Nang niu ban chan Viet", "Bitis"));
//        products.put(5, new Product(5, "Fila", 600, "Fila lala", "Fila"));
//        products.put(6, new Product(6, "Puma", 900, "Unleash your wild side", "Puma"));
//        products.put(7, new Product(7, "Reebok", 800, "Reebok ", "Reebok International Limited"));
//    }

    //JDBC Connect

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanager",
                    "root",
                    ""
            );
        } catch (ClassNotFoundException e) {
            System.out.println("kh co driver");
        } catch (SQLException throwables) {
            System.out.println("kh connect sql");
        }
        System.out.println("connect thanh cong");
        return connection;
    }


    @Override
    public List<Product> findAll() {
//        return new ArrayList<>(products.values());
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from product");
            // chay roi luu cai truy van tren
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String producer = resultSet.getString("producer");
                products.add(new Product(id, name, price, description, producer));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
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
//        return products.get(id);

        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from product where id=? ");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {
//                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                String description = resultSet.getString("description");
                String producer = resultSet.getString("producer");
                product = new Product(id, name, price, description, producer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public void deleteById(int id) {
        products.remove(id);
    }

    @Override
    public Product findByName(String name) {
        return products.get(name);
    }

    @Override
    public boolean update(Product product) {
        Connection connection = getConnection();
        boolean check = false;
        try {
            PreparedStatement p = connection.prepareStatement("update product set name = ?,price =?,description=?,producer=? where id= ?");
            p.setString(1, product.getName());
            p.setInt(2, product.getPrice());
            p.setString(3, product.getDescription());
            p.setString(4, product.getProducer());
            p.setInt(5, product.getId());
            check = p.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
}
