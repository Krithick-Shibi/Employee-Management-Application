import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

public class ManagementServices {

    private EmployeeDataManager _dataManager = new EmployeeDataManager();

    public void getEmployeeDetails() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        try {
            // prompts user to enter employee details
            String employeeName = Validator.getAndParseInput("\nEnter Employee Name :", String.class);
            int employeeAge = Validator.getAndParseInput("Enter Employee Age : ", Integer.class);
            LocalDate dob = Validator.getDate();
            String department = Validator.getDepartment();
            double salary = Validator.getAndParseInput("Enter Salary : ", Double.class);

            _dataManager.addEmployee(employeeName, employeeAge, dob, salary, department);
            System.out.println("\nEmployee added successfully!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewEmployeesDetails(Iterable<Employee> employeeData) {
        int indexCount = 1;
        if (!employeeData.iterator().hasNext()) {
            System.out.println("\nNo employees.");
        } else {
            for (Employee employee : employeeData) {
                System.out.println(
                        "\nID : " + indexCount++ + "\t\tNAME : " + employee.getName() + "\t\tAGE : " + employee.getAge()
                                + "\tDATE OF BIRTH : " + employee.getDob() + "\tDEPARTMENT : "
                                + employee.getDepartment() + "\tSALARY : " + employee.getSalary());
            }
        }
    }

    public Iterable<Employee> SearchEmployeeByName() {
        try {
            String searchKey = Validator.getAndParseInput("\nEnter name to search :", String.class);
            return _dataManager.findEmployeesByName(searchKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Iterable<Employee> SearchEmployeeByDepartment() {
        try {
            String searchKey = Validator.getDepartment();
            return _dataManager.findEmployeesByDepartment(searchKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteEmployee() {
        try {
            int index = Validator.getValidUserChoice("Enter index to delete : ", _dataManager.fetchCountOfEmployees());
            _dataManager.removeEmployee(index);
            System.out.println("\n\t\tEmployee deleted successfully! ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployee() {
        Scanner scanner = new Scanner(System.in);
        try {
            int index = Validator.getValidUserChoice("Enter index to update : ", _dataManager.fetchCountOfEmployees());
            System.out.println(
                    "\nChoose what to update\n\n1.Update Name\n2.Update Age\n3.Update DOB\n4.Update Department\n5.Update Salary");
            System.out.print("\nEnter your choice : ");
            var input = Validator.getValidUserChoice("Enter your choice : ", 5);
            UpdateOptions choice = UpdateOptions.values()[input - 1];
            switch (choice) {
                case NAME:
                    String employeeName = Validator.getAndParseInput("\nEnter Employee Name :", String.class);
                    _dataManager.updateName(index, employeeName);
                    break;

                case AGE:
                    int employeeAge = Validator.getAndParseInput("\nEnter Employee Age : ", Integer.class);
                    _dataManager.updateAge(index, employeeAge);
                    break;

                case DOB:
                    LocalDate dob = Validator.getDate();
                    _dataManager.updateDate(index, dob);
                    break;

                case DEPARTMENT:
                    String department = Validator.getDepartment();
                    _dataManager.updateDepartment(index, department);
                    break;

                case SALARY:
                    double salary = Validator.getAndParseInput("\nEnter Salary : ", Double.class);
                    _dataManager.updateSalary(index, salary);
                    break;

                default:
                    System.out.println("\nInvalid choice.");
                    break;
            }

            System.out.println("\n\t\tEmployee data updated successfully!");
            viewEmployeesDetails(_dataManager.getEmployeeByIndex(index));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void filterEmployees() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(
                    "\nChoose criteria to filter\n\n1.Name\n2.Age\n3.Department\n4.Salary");
            System.out.print("\nEnter your choice : ");
            var input = Validator.getValidUserChoice("Enter your choice : ", 4);
            FilterOptions choice = FilterOptions.values()[input - 1];
            switch (choice) {
                case NAME:
                    viewEmployeesDetails(SearchEmployeeByName());
                    break;
                case AGE:
                    int employeeAge = Validator.getAndParseInput("\nEnter Employee Age to filter : ", Integer.class);
                    viewEmployeesDetails(_dataManager.filterEmployeeByAge(employeeAge));
                    // _dataManager.FilterEmployeeByAge(employeeAge);
                    break;
                case DEPARTMENT:
                    viewEmployeesDetails(SearchEmployeeByDepartment());
                    break;
                case SALARY:
                    double salary = Validator.getAndParseInput("\nEnter Salary : ", Double.class);
                    viewEmployeesDetails(_dataManager.filterEmployeeBySalary(salary));
                    // _dataManager.FilterEmployeeBySalary(salary);
                    break;
                default:
                    System.out.println("\nInvalid choice.");
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayTotalSalary(){
        Iterable<Employee> employeeData = _dataManager.getEmployeeData();
        int totalSalary = 0;
        double average = 0;
        for(Employee employee : employeeData){
           totalSalary+= employee.getSalary();

        }
        average = totalSalary/_dataManager.fetchCountOfEmployees(); 

        System.out.println("\nTotal Salary of Employees : "+ totalSalary +"\nAverage Salary of the Employees : "+ average +"\n");
    }

    public void displaySalaryByDepartment(DepartmentOptions option){
        Iterable<Employee> employeeData = _dataManager.getEmployeeData();
        int totalSalary = 0;
        int numberOfEmployeesInDepartment = 0;
        for(Employee employee : employeeData){
           if(option.toString().equals(employee.getDepartment())){
            totalSalary+= employee.getSalary();
            numberOfEmployeesInDepartment++;
           }
        }
        double average = totalSalary/numberOfEmployeesInDepartment;
        System.out.println("Total Salary in "+ option.toString() +" : "+ totalSalary + "\nAverage Salary of Employees" + option.toString() + " : " + average +"\n" );
    }
}
