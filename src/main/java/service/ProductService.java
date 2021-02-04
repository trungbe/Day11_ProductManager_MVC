package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    public static final String SHOW_ALL_PRODUCT = "select  * from product";
    public static final String CREATE_NEW_PRODUCT = "insert into product" + " (name,price,description,producer) values" + "(?,?,?,?);";
    public static final String FIND_PRODUCT_BY_ID = "select  * from product where id=? ";
    public static final String UPDATE_PRODUCT = "update product set name = ?,price =?,description=?,producer=? where id= ?";
    public static final String DELETE_PRODUCT = "delete from product where id= ?";
    private static Map<Integer, Product> products;


//    JDBC Connect

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
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_PRODUCT);
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
    public Product create(Product product) {
//        return products.put(product.getId(), product);
        Connection connection = getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(CREATE_NEW_PRODUCT);
            p.setString(1, product.getName());
            p.setInt(2, product.getPrice());
            p.setString(3, product.getDescription());
            p.setString(4, product.getProducer());
            p.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }


    @Override
    public Product findById(int id) {
//        return products.get(id);

        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID);
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
    public boolean update(Product product) {
        Connection connection = getConnection();
        boolean check = false;
        try {
            PreparedStatement p = connection.prepareStatement(UPDATE_PRODUCT);
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

    @Override
    public boolean delete(int id) {
        Connection connection = getConnection();
        boolean check = false;
        try {
            PreparedStatement p = connection.prepareStatement(DELETE_PRODUCT);
            p.setInt(1, id);
            check = p.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    /////////////////////////////

    @Override
    public Product findByName(String name) {
        return products.get(name);
    }
}
