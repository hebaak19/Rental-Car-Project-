package UI;

public class Menu {
        static String mainMenu = """
                        === Main Menu ===
                        1. Employee Menu
                        2. Customer Menu
                        3. Exit
                        """;
        static String employeeMenu = """
                        === Employee Menu ===
                        1. Administer Cars
                        2. Manager Menu
                        3. Salesman Menu
                        4. Customer Service Menu
                        5. Exit
                        """;
        static String customerMenu = """
                        === Customer Menu ===
                        1. View Available Cars
                        2. Rent a Car
                        3. Return a Car
                        4. Refund Request
                        5. Exit
                        """;
        public static String adminMenu = """
                        === Admin Menu ===
                        1. Add Car
                        2. Remove Car
                        3. View All Cars
                        4. Exit
                        """;

        public static String managerMenu = """
                        === Manager Menu ===
                        1. View Active Reports
                        2. View all Employees
                        3. Exit
                        """;

        public static String salesmanMenu = """
                        === Salesman Menu ===
                        1. View Sales
                        2. View all Customers
                        3. Exit
                        """;
        public static String customerServiceMenu = """
                        === Customer Service Menu ===
                        1. View Active Contracts
                        2. Manage refunds
                        3. Exit
                        """;
}
