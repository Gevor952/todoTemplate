package am.itspace.todotemplate.service;

import am.itspace.todotemplate.Enum.Status;
import am.itspace.todotemplate.db.DBConnectionProvider;
import am.itspace.todotemplate.model.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoService {

    private static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<ToDo> getAllToDoByUserId(int user_id) {
        List<ToDo> results = new ArrayList<>();
        String sql = "SELECT * FROM to_do WHERE user_id = " + user_id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                results.add(ToDo.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .createdDateTime(resultSet.getTimestamp(3))
                        .finishedDateTime(resultSet.getTimestamp(4))
                        .userId(resultSet.getInt(5))
                        .status(Status.valueOf(resultSet.getString(6)))
                        .build());

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    public ToDo getAllToDoById(int id) {
        ToDo results = null;
        String sql = "SELECT * FROM to_do WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                results = ToDo.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .createdDateTime(resultSet.getTimestamp(3))
                        .finishedDateTime(resultSet.getTimestamp(4))
                        .userId(resultSet.getInt(5))
                        .status(Status.valueOf(resultSet.getString(6)))
                        .build();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void add(ToDo toDo) {
        java.sql.Date finishD = null;
        if (toDo.getFinishedDateTime() != null) {
            finishD = new java.sql.Date((toDo.getFinishedDateTime()).getTime());
        }
        String sql = "INSERT INTO to_do(title, created_date, finished_date, user_id, status) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setDate(2, new java.sql.Date((toDo.getCreatedDateTime()).getTime()));
            preparedStatement.setDate(3, finishD);
            preparedStatement.setInt(4, toDo.getUserId());
            preparedStatement.setString(5, String.valueOf(toDo.getStatus()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                toDo.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM to_do WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String title) {
        String sql = "UPDATE to_do SET title = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(ToDo toDo) {
        String sql = "UPDATE to_do SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, toDo.getStatus().name());
            preparedStatement.setInt(2, toDo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
