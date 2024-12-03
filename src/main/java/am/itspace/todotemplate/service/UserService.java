package am.itspace.myfriend.service;


import am.itspace.myfriend.db.DBConnectionProvider;
import am.itspace.myfriend.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(User user) {
        String sql = "insert into user (name, surname, email, password, image_name) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getImage_name());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User grtByEmailAndPassword(String email, String password) {
        String sql = "select * from user where email = ? and password = ?";
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .image_name(resultSet.getString("image_name"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User grtByEmail(String email) {
        String sql = "select * from user where email = ?";
        User user = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .image_name(resultSet.getString("image_name"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImage_name(resultSet.getString("image_name"));
                result.add(user);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<User> getAllUsers(int id) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM user WHERE id <> " + id ;
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setImage_name(resultSet.getString("image_name"));
                result.add(user);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return result;
    }

    public void acceptFriend(User user, User user2) {
        String sql = "INSERT INTO friends(user_id, friend_id) VALUES (?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.setInt(2, user2.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user2.getId());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getFriends(int id) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM friends " +
                "INNER JOIN user u1 ON friends.friend_id = u1.id " +
                "where user_id = " + id;

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(3))
                        .name(resultSet.getString(4))
                        .surname(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .image_name(resultSet.getString(8))
                        .build();
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<User> getFriends(int userid, int friendid) {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM friends " +
                "INNER JOIN user u1 ON friends.friend_id = u1.id " +
                "where user_id = " + userid + " AND friend_id = " + friendid;

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(3))
                        .name(resultSet.getString(4))
                        .surname(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .password(resultSet.getString(7))
                        .image_name(resultSet.getString(8))
                        .build();
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
