import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDataManager {
    private static ArrayList<Employee> _employeeDataList = new ArrayList<>();

    public void addEmployee(String name, int age, LocalDate dob, double salary,String department){
        Employee newEmployee = new Employee(name, age, dob, salary, department);
        _employeeDataList.add(newEmployee);
    }

    public Iterable<Employee> getEmployeeData(){
        return _employeeDataList;
    }

    public Iterable<Employee> findEmployeesByName(String searchValue){
        return _employeeDataList.stream()
        .filter(employee -> employee.getName().toLowerCase().contains(searchValue.toLowerCase()))
        .toList(); 
    }

    public Iterable<Employee> findEmployeesByDepartment(String searchValue){
        return _employeeDataList.stream()
        .filter(employee -> employee.getDepartment().equals(searchValue))
        .toList(); 
    }

    public Iterable<Employee> filterEmployeeByAge(int age){
       return _employeeDataList.stream()
        .filter(employee->employee.getAge() == age)
        .toList();
    }

    public Iterable<Employee> filterEmployeeBySalary(double salary){
       return _employeeDataList.stream()
        .filter(employee->employee.getSalary() == salary)
        .toList();
    }

    public int fetchCountOfEmployees(){
        return _employeeDataList.size();
    }

    public void removeEmployee(int index){
        _employeeDataList.remove(index-1);
    }

    public void updateName(int index, String name){
        _employeeDataList.get(index-1).setName(name);
    }

    public void updateAge(int index, int age){
        _employeeDataList.get(index -1).setAge(age);
    }

    public void updateDate(int index, LocalDate date){
        _employeeDataList.get(index-1).setDob(date);
    }

    public void updateDepartment(int index, String department){
        _employeeDataList.get(index-1).setDepartment(department);
    }

    public void updateSalary(int index, double salary){
        _employeeDataList.get(index-1).setSalary(salary);
    }

    public Iterable<Employee> getEmployeeByIndex(int index){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(_employeeDataList.get(index - 1));
        return employeeList;
    }

    public void loadDataFromFile(){
         Path dataFile = Paths.get("EmployeeUserData.txt");

        if (Files.exists(dataFile)) {
            try {
                List<String> employeeData = Files.readAllLines(dataFile);
                for (String employee : employeeData) {
                    _employeeDataList.add(Employee.deserialize(employee));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Files.createFile(dataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void saveDataToFile(){
         Path dataFile = Paths.get("EmployeeUserData.txt");
          try {
            String dataString = "";
            for (Employee employee : _employeeDataList){
                dataString += Employee.serialize(employee)+"\n";
            }
            Files.write(dataFile, dataString.getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
