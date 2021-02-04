package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public static final String SHOW_ALL_PRODUCT_SQL = "select  * from product";
    public static final String CREATE_NEW_PRODUCT_SQL = "insert into product" + " (name,price,description,producer) values" + "(?,?,?,?);";
    public static final String FIND_PRODUCT_BY_ID_SQL = "select  * from product where id=? ";
    public static final String UPDATE_PRODUCT_SQL = "update product set name = ?,price =?,description=?,producer=? where id= ?";
    public static final String DELETE_PRODUCT_SQL = "delete from product where id= ?";
    public static final String SORT_BY_NAME_SQL = "select * from product order by name asc ;";
    private String jdbcURL = "jdbc:mysql://localhost:3306/productmanager";
    private String jdbcUser = "root";
    private String jdbcPassword = "";

//    JDBC Connect

    protected Connection getConnection() {
        Connection connection = null;
        try {
            //connect sql by load driver
            Class.forName("com.mysql.jdbc.Driver");
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //create connection
            connection = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPassword);
            //connection.close();
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
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_PRODUCT_SQL);
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
        Connection connection = getConnection();
        try {
            PreparedStatement p = connection.prepareStatement(CREATE_NEW_PRODUCT_SQL);
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
        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID_SQL);
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
            PreparedStatement p = connection.prepareStatement(UPDATE_PRODUCT_SQL);
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
            PreparedStatement p = connection.prepareStatement(DELETE_PRODUCT_SQL);
            p.setInt(1, id);
            check = p.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Product> sort() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_NAME_SQL);
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
}
