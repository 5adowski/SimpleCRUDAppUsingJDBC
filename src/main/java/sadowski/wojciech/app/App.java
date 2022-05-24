package sadowski.wojciech.app;

import sadowski.wojciech.employee.Employee;
import sadowski.wojciech.employee.EmployeeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        EmployeeRepository employeeRepository = new EmployeeRepository();

        while (true) {
            System.out.println("What to do?");
            System.out.println("1. Add employee");
            System.out.println("2. Delete employee");
            System.out.println("3. Show employee");
            System.out.println("4. Show all employees");
            System.out.println("5. Edit employee");
            System.out.println("0. EXIT");
            int option = scan.nextInt();
            scan.nextLine();
            if (option == 1) {
                System.out.println("Input first name:");
                String firstName = scan.nextLine();
                System.out.println("Input last name:");
                String lastName = scan.nextLine();
                System.out.println("Input email:");
                String email = scan.nextLine();
                System.out.println("Input salary:");
                BigDecimal salary = scan.nextBigDecimal();
                Employee employee = new Employee(firstName, lastName, email, salary);
                employeeRepository.add(employee);
                System.out.println("Employee created: " + employee);
            }
            if (option == 2) {
                List<Employee> list = employeeRepository.getAll();
                for (Employee employee : list) {
                    System.out.println(employee);
                }
                System.out.println("Input id of employee to be deleted:");
                Integer id = scan.nextInt();
                employeeRepository.delete(id);
                System.out.println("Deleted!");
            }
            if(option == 3) {
                System.out.println("Input id of employee you want to print:");
                Integer id = scan.nextInt();
                System.out.println(employeeRepository.getById(id));
            }
            if (option == 4) {
                List<Employee> list = employeeRepository.getAll();
                for (Employee employee : list) {
                    System.out.println(employee);
                }
            }
            if(option == 5) {
                List<Employee> list = employeeRepository.getAll();
                for (Employee employee : list) {
                    System.out.println(employee);
                }
                System.out.println("Which employee do you want to change?");
                System.out.println("Input id:");
                Integer id = scan.nextInt();
                System.out.println(employeeRepository.getById(id));
                System.out.println("What do you want to change?");
                System.out.println("1. First name");
                System.out.println("2. Last name");
                System.out.println("3. Email");
                System.out.println("4. Salary");
                System.out.println("0. GO BACK!");
                Integer input = scan.nextInt();
                scan.nextLine();
                if(input==1) {
                    System.out.println("Input new first name:");
                    String firstName = scan.nextLine();
                    employeeRepository.editFirstName(id, firstName);
                    System.out.println("Changed!");
                }
                if(input==2){
                    System.out.println("Input new last name:");
                    String lastName = scan.nextLine();
                    employeeRepository.editLastName(id, lastName);
                    System.out.println("Changed!");
                }
                if(input==3){
                    System.out.println("Input new email:");
                    String email = scan.nextLine();
                    employeeRepository.editEmail(id, email);
                    System.out.println("Changed!");
                }
                if(input==4){
                    System.out.println("Input new salary:");
                    BigDecimal salary = scan.nextBigDecimal();
                    employeeRepository.editSalary(id, salary);
                    System.out.println("Changed!");
                }
                if(input==0) break;
            }
            if (option == 0) break;
        }
    }
}
