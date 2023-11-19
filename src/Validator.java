import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {

    public static <T> T getAndParseInput(String instruction, Class<T> type) throws InvalidInputException {
        int invalidInputCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (invalidInputCount <= 3) {
            System.out.print(instruction);
            String userInput = scanner.nextLine();
            if (userInput != null && !userInput.isEmpty() && isValid(userInput, type)) {
                return parseInput(userInput, type);
            }

            invalidInputCount++;
            System.out.println("[Warning] Invalid input. You have " + (4 - invalidInputCount) + " attempts left.\n");
        }
        throw new InvalidInputException();
    }

    private static <T> boolean isValid(String input, Class<T> type) {
        try {
            if (type.equals(Integer.class)) {
                type.cast(Integer.parseInt(input));
            } else if (type.equals(Double.class)) {
                type.cast(Double.parseDouble(input));
            } else {
                type.cast(input);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static <T> T parseInput(String input, Class<T> type) throws InvalidInputException {
        try {
            if (type.equals(Integer.class)) {
                return type.cast(Integer.parseInt(input));
            } else if (type.equals(Double.class)) {
                return type.cast(Double.parseDouble(input));
            } else {
                return type.cast(input);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static LocalDate getDate() throws InvalidInputException {
        int invalidInputCount = 0;
        while (invalidInputCount < 3) {
            System.out.println("\n[Enter valid date] ");
            String date = getAndParseInput("Enter Date Of Birth [format : yyyy/mm/dd] : ", String.class);
            if (date != null && date.matches("^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])$")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                return LocalDate.parse(date, formatter);
            }
            invalidInputCount++;
        }

        throw new InvalidInputException();
    }

    public static String getDepartment() {
        try {
            int indexCount = 1;
            for (DepartmentOptions option : DepartmentOptions.values()) {
                System.out.println(indexCount++ + "." + option.toString());
            }
            int index = Validator.getValidUserChoice("Enter index to choose department : ", 5);
            return DepartmentOptions.values()[index - 1].toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static int getValidUserChoice(String instruction, int range) throws InvalidInputException {
        int invalidInputCount = 0;
        while (invalidInputCount < 3) {
            System.out.println("\n[Enter valid index]");
            int choice = getAndParseInput(instruction, Integer.class);
            if (choice > 0 && choice <= range) {
                return choice;
            }

            invalidInputCount++;
        }
        throw new InvalidInputException();
    }
}
