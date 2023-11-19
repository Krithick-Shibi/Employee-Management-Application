import java.util.Scanner;

public class Menu {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isExitRequested = false;
        String instruction = "\n\nChoose your action\n\n1.Add Employee\n2.View Employees\n3.Filter Employees\n4.Search Employee\n5.Update Employee\n6.Delete Employee\n7.Salary Details\n8.Exit\n";
        ManagementServices services = new ManagementServices();
        EmployeeDataManager dataManager = new EmployeeDataManager();
        try {
            dataManager.loadDataFromFile();
            while (!isExitRequested) {
                System.out.println(instruction);
                var input = Validator.getValidUserChoice("Enter your choice : ", 8);
                MenuOptions choice = MenuOptions.values()[input - 1];
                switch (choice) {
                    case AddEmployee:
                        services.getEmployeeDetails();
                        break;

                    case ViewEmployees:
                        services.viewEmployeesDetails(dataManager.getEmployeeData());
                        break;

                    case FilterEmployees:
                        services.filterEmployees();
                        break;

                    case UpdateEmployee:
                        services.viewEmployeesDetails(dataManager.getEmployeeData());
                        services.updateEmployee();

                        break;

                    case SearchEmployee:
                        services.viewEmployeesDetails(services.SearchEmployeeByName());
                        break;

                    case DeleteEmployee:
                        services.viewEmployeesDetails(dataManager.getEmployeeData());
                        services.deleteEmployee();
                        break;

                    case SalaryDetails:
                        services.displayTotalSalary();
                        services.displaySalaryByDepartment(DepartmentOptions.AGILETEAM);
                        services.displaySalaryByDepartment(DepartmentOptions.DEVELOPERSTEAM);
                        services.displaySalaryByDepartment(DepartmentOptions.HRTEAM);
                        services.displaySalaryByDepartment(DepartmentOptions.QATEAM);
                        services.displaySalaryByDepartment(DepartmentOptions.SALESANDMARKETING);

                        break;

                    case Exit:
                        System.out.println(
                                "\n----------------------------------------  Thankyou!!  -------------------------------------------\n\n");
                        isExitRequested = true;
                        dataManager.saveDataToFile();
                        break;

                    default:
                        System.out.println("\nEnter a valid choice!");
                        break;

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}