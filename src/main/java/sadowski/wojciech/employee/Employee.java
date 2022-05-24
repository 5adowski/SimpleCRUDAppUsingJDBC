package sadowski.wojciech.employee;

import sadowski.wojciech.database.MyDatabase;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;

    public Employee(Integer id, String firstName, String lastName, String email, BigDecimal salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, String email, BigDecimal salary) {
        this.id = getIdFromDb();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }

    private Integer getIdFromDb(){
        Set<Integer> idSet = new TreeSet<>();
        Connection connection = MyDatabase.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees;");
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                idSet.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator<Integer> iterator = idSet.iterator();
        Integer ids = 1;
        if(iterator.hasNext()) {
            for (int i = 1; i == iterator.next(); i++) {
                ids++;
                if (!iterator.hasNext()) break;
            }
        }
        return ids;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }
}
