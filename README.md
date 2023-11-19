# CONSOLE BASED EMPLOYEE MANAGEMENT APPLICATION
## Introduction
This is a simple console-based contact managent application that allows you to manage PRESIDIO' S employees.

## Features

- Add Employees
- View Employees
- Filter Employee
- Search Employee
- Update Employee
- Delete Employee
- Salary Details

Menu class contains all these functionalities.
## Implementation

### EmployeeDataManager

- **addEmployee() :** receives input parameters, including name, age, date of birth (DOB), salary, and department. It then stores the provided user information in an Arraylist.

- **getEmployeeData() :** returns the entire employee data.
- **findEmployeesByName() :** searches and returns employees whose names match the specified value.
- **findEmployeesByDepartment() :** searches and returns employees belonging to the specified department.
- **filterEmployeeByAge() :** searches and returns employees belonging to the specified department.
- **filterEmployeeBySalary() :**  filters and returns employees with the specified salary.
- **fetchCountOfEmployees() :** returns the total count of employees.
- **removeEmployee() :** removes an employee record based on the provided index.
- **updateName() :** updates the name of an employee at the specified index.
- **updateAge() :** updates the age of an employee at the specified index.
- **updateDate() :** updates the date of birth of an employee at the specified index. 
- **updateDepartment() :** updates the department of an employee at the specified index.
- **updateSalary() :** updates the salary of an employee at the specified index.
- **getEmployeeByIndex() :** retrieves and returns a list containing the employee at the specified index.
- **loadDataFromFile() :** reads employee data from a file and then deserializes the data to store it in a list.
- **saveDataToFile() :** writes all the employee data in the list to a .txt file after properly serializing the data.


### ManagementServices

- **getEmployeeDetails() :** Prompts the user to enter details for a new employee, validates the input, and adds the employee to the data manager list.

- **viewEmployeesDetails() :** Displays employee details which includes ID, name, age, date of birth, department and salary.
- **searchEmployeeByName() :** Prompts the user to enter a name for searching employees and returns a list of matching employees.
- **searchEmployeeByDepartment() :** Prompts the user to enter a department for searching employees and returns a list of matching employees.
- **deleteEmployee() :** Prompts the user to enter the index of the employee to delete and removes the corresponding employee record.
- **updateEmployee() :** Prompts the user to select an employee to update and choose the field to update (name, age, date of birth, department, or salary). Then, updates the selected field for the chosen employee.
- **filterEmployees() :** Prompts the user to choose criteria for filtering employees (name, age, department, or salary) and displays the filtered results.
- **displayTotalSalary() :** Calculates and displays the total salary and average salary of all employees.
- **displaySalaryByDepartment() :** Calculates and displays the total salary and average salary for employees with respect to different departments.

### Employee

The Employee class encapsulates the essential attributes and behaviors of an employee within the Employee Management System. It includes methods for accessing and modifying employee details, as well as serialization and deserialization functionalities for data persistence.
- **serialize() :** Converts the employee object into a JSON-formatted string for serialization.

- **deserialize() :** Converts a JSON-formatted string into an Employee object for deserialization.

### Validator

- **getAndParseInput() :** Prompts the user with the provided instruction and attempts to parse the entered input into the specified data type (type). It allows up to three attempts for valid input and throws an InvalidInputException if unsuccessful.

- **isValid() :** Checks whether the provided input is valid for the given data type (type). Returns true if the input can be successfully cast to the specified type; otherwise, returns false.
- **parseInput() :** Parses the provided input into the specified data type (type). Throws an exception if parsing is unsuccessful.
- **getDate() :** Prompts the user to enter a valid date in the "yyyy/mm/dd" format. It uses the getAndParseInput method for input validation and returns a LocalDate object.
- **getDepartment() :** Displays department options, prompts the user to choose a department, and returns the chosen department as a string.
- **getValidUserChoice() :** Prompts the user to enter a valid index within the specified range. It uses the getAndParseInput method to validate the user's input and ensures the entered index is within the specified range.

Also there are various enums to represent states like Menuoption, Filteroptions and UpdateOptions.

## Conclusion
These functions work together to create a cohesive enmployee management system in the console application. They handle different aspects of managing employees, from adding and displaying to searching, updating, displaying salary details and deleting employee recods.

