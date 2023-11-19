import java.time.LocalDate;

public class Employee {
    private String name;
    private int age;
    private LocalDate dob; 
    private double salary;
    private String department;

    public Employee(String name, int age, LocalDate dob, double salary, String department) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static String serialize(Employee employee) {
        return String.format("{\"name\":\"%s\",\"age\":%d,\"dob\":\"%s\",\"salary\":%.2f,\"department\":\"%s\"}",
                             employee.getName(), employee.getAge(), employee.getDob(), employee.getSalary(), employee.getDepartment());
    }

    public static Employee deserialize(String jsonString) {
        String[] parts = jsonString.substring(1, jsonString.length() - 1).split(",");
        String name = parts[0].split(":")[1].replaceAll("\"", "");
        int age = Integer.parseInt(parts[1].split(":")[1]);
        LocalDate dob = LocalDate.parse(parts[2].split(":")[1].replaceAll("\"", ""));
        double salary = Double.parseDouble(parts[3].split(":")[1]);
        String department = parts[4].split(":")[1].replaceAll("\"", "");

        return new Employee(name, age, dob, salary, department);
    }
}