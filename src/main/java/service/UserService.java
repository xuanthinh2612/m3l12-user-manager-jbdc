package service;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {


    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_manager", "root", "1010");


        return connection;
    }


    @Override
    public List<User> findAll() throws ClassNotFoundException, SQLException {
        List<User> userList = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");


        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String country = resultSet.getString("country");

            userList.add(new User(id, name, email, country));
        }
        return userList;
    }

    @Override
    public User findByIdy(int id) {
        User user = null;
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id, name, email, country);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(int id, User user) {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("update users set  name =?,email=?,country=? where id=?");


            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void create(User user) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name, email, country) VALUE (?,?,? );");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> findByCountry(String country){

        List<User> userList = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where country = ?");
            preparedStatement.setString(1,country);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                userList.add( new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email"),resultSet.getString("country"))) ;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }
}

