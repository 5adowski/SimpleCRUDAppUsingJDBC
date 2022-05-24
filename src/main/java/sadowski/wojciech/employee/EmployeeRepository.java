package sadowski.wojciech.employee;

import sadowski.wojciech.database.MyDatabase;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class EmployeeRepository {
    private final Connection connection = MyDatabase.connect();

    public List<Employee> getAll() {
        List<Employee> list = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                BigDecimal salary = resultSet.getBigDecimal(5);
                Employee employee = new Employee(id, firstName, lastName, email, salary);
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Employee getById(Integer id){
        Employee employee = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer idin = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                BigDecimal salary = resultSet.getBigDecimal(5);
                employee = new Employee(idin, firstName, lastName, email, salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public int add(Employee employee) {
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO employees (id, firstName, lastName, email, salary) VALUES (?, ?, ?, ?, ?);");
            statement.setInt(1,employee.getId());
            statement.setString(2,employee.getFirstName());
            statement.setString(3,employee.getLastName());
            statement.setString(4,employee.getEmail());
            statement.setBigDecimal(5,employee.getSalary());
            statement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?;");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editFirstName(Integer id ,String newFirstName) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET firstName = ? WHERE id = ?;");
            statement.setString(1, newFirstName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editLastName(Integer id ,String newLastName) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET lastName = ? WHERE id = ?;");
            statement.setString(1, newLastName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editEmail(Integer id ,String newEmail) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET email = ? WHERE id = ?;");
            statement.setString(1, newEmail);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSalary(Integer id ,BigDecimal newSalary) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE employees SET salary = ? WHERE id = ?;");
            statement.setBigDecimal(1, newSalary);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
