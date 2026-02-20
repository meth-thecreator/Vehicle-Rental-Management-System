import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class RentalApp {
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static double totalIncome = 0;
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        loadVehiclesFromFile(); //Loading the text files

        int role;

        do{ //Challenging Component - Role-Based Menu(Basic Simulation)
            System.out.println("\n==== VEHICLE RENTAL SYSTEM ====");
            System.out.println("1. Admin Mode");
            System.out.println("2. User Mode");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try{
                role = sc.nextInt();

                switch (role) {
                    case 1 -> adminMenu();
                    case 2 -> userMenu();
                    case 3 -> System.out.println("Exiting system...");
                    default -> System.out.println("Invalid selection!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a valid number!");
                sc.nextLine();
                role = 0;
            }
        } while (role != 3);
    }
    //File Loading
    private static void loadVehiclesFromFile() { //Loading each file separately
        loadCars();
        loadBikes();
        loadVans();
        loadBuses();
    }

    private static void loadCars() {
        try (Scanner fileScanner = new Scanner(new File("Cars.txt"))) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                //Skipping empty lines
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                if (data.length != 5) {
                    System.out.println("Skipping invalid car entry: " + line);
                    continue;
                }
                vehicles.add(new Car(
                        data[0], data[1], data[2],
                        Double.parseDouble(data[3]),
                        Integer.parseInt(data[4])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cars.txt not found.");
        }
    }

    //Challenging Component - Importing text files to load and save vehicle data separately for each and every vehicle type
    private static void loadBikes() {
        try (Scanner fileScanner = new Scanner(new File("Bikes.txt"))) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                //Skipping empty lines
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                if (data.length != 5) {
                    System.out.println("Skipping invalid bike entry: " + line);
                    continue;
                }
                vehicles.add(new Bike(
                        data[0], data[1], data[2],
                        Double.parseDouble(data[3]),
                        Integer.parseInt(data[4])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Bikes.txt not found.");
        }
    }

    private static void loadVans() {
        try (Scanner fileScanner = new Scanner(new File("Vans.txt"))) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                //Skipping empty lines
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                if (data.length != 5) {
                    System.out.println("Skipping invalid van entry: " + line);
                    continue;
                }

                vehicles.add(new Van(
                        data[0], data[1], data[2],
                        Double.parseDouble(data[3]),
                        Double.parseDouble(data[4])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Vans.txt not found.");
        }
    }

    //Challenging Component - Introducing a new vehicle subclass with its own pricing logic
    private static void loadBuses() {
        try (Scanner fileScanner = new Scanner(new File("Buses.txt"))) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                //Skipping empty lines
                if (line.isEmpty()) continue;

                String[] data = line.split(",");

                if (data.length != 6) {
                    System.out.println("Skipping invalid bus entry: " + line);
                    continue;
                }

                vehicles.add(new Bus(
                        data[0], data[1], data[2],
                        Double.parseDouble(data[3]),
                        Integer.parseInt(data[4]),
                        Boolean.parseBoolean(data[5])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Buses.txt not found.");
        }
    }

    //Challenging Component - Role-Based Menu ; Admin Menu
    private static void adminMenu() {
        int choice;
        do {
            System.out.println("\n==== ADMIN MENU ====");
            System.out.println("1. Add vehicle");
            System.out.println("2. View all vehicles");
            System.out.println("3. Get Total Rental Income");
            System.out.println("4. Search Vehicle"); //Challenging Component - Advanced Search and Filtering
            System.out.println("5. Sort Vehicle"); //Challenging Component - Sorting Mechanism
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addVehicle();
                    case 2 -> viewVehicles();
                    case 3 -> System.out.println("Total Rental Income : " + totalIncome);
                    case 4 -> searchMenu();
                    case 5 -> sortMenu();
                    case 6 -> System.out.println("Exit\n");
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid output!");
                sc.nextLine();
                choice = 0;
            }

        } while (choice != 6);
    }

    //Challenging Component - Role-Based Menu ; User Menu
    private static void userMenu() {
        int choice;
        do {
            System.out.println("\n==== USER MENU ====");
            System.out.println("1. Rent a vehicle");
            System.out.println("2. Return a vehicle");
            System.out.println("3. Search vehicles"); //Challenging Component - Advanced Search and Filtering
            System.out.println("4. View all vehicles");
            System.out.println("5. Sort Vehicles"); //Challenging Component - Sorting Mechanism
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> rentVehicle();
                    case 2 -> returnVehicle();
                    case 3 -> searchMenu();
                    case 4 -> viewVehicles();
                    case 5 -> sortMenu();
                    case 6 -> System.out.println("Returning to main menu...");
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
                choice = 0;
            }
        } while (choice != 6);
    }

    //Add a vehicle - Admin mode
    private static void addVehicle() {
        System.out.println("Select Vehicle Type: 1.Car  2.Bike  3. Van  4. Bus");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Vehicle ID: ");
        String id = sc.nextLine();

        //Unique ID check
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                System.out.println("Vehicle ID must be unique!");
                return;
            }
        }
        System.out.print("Brand: ");
        String brand = sc.nextLine();
        System.out.print("Model: ");
        String model = sc.nextLine();
        System.out.print("Base Rate Per Day: ");
        double rate = sc.nextDouble();

        switch (type) {
            case 1 ->  {
                System.out.print("Number of seats: ");
                int seats = sc.nextInt();
                Car car = new Car(id, brand, model, rate, seats);
                vehicles.add(car);
                saveCarToFile(car);
            }

            case 2 -> {
                System.out.print("Engine Capacity CC: ");
                int cc = sc.nextInt();
                Bike bike = new Bike(id, brand, model, rate, cc);
                vehicles.add(bike);
                saveBikeToFile(bike);
            }

            case 3 -> {
                System.out.print("Cargo Capacity Kg: ");
                double kg = sc.nextDouble();
                Van van = new Van(id, brand, model, rate, kg);
                vehicles.add(van);
                saveVanToFile(van);
            }
            case 4 -> {
                System.out.print("Passenger Capacity: ");
                int capacity = sc.nextInt();

                System.out.print("AC Available (true/false): ");
                boolean ac = sc.nextBoolean();
                Bus bus = new Bus(id, brand, model, rate, capacity, ac);
                vehicles.add(bus);
                saveBusToFile(bus);
            }

            default -> System.out.println("Invalid vehicle type!");
        }

        System.out.println("Vehicle added successfully");
    }

    //Challenging Component - File Handling - Saving vehicle data to separate files
    private static void saveCarToFile(Car car) {
        try (FileWriter writer = new FileWriter("Cars.txt", true)) {
            writer.write(
                    System.lineSeparator() +
                    car.getVehicleId() + "," + car.getBrand() + "," + car.getModel() + "," + car.getBaseRatePerDay() + "," + car.getSeats() + "\n"
            );
        } catch (IOException e) {
            System.out.println("Error saving car to file.");
        }
    }

    private static void saveBikeToFile(Bike bike) {
        try (FileWriter writer = new FileWriter("Bikes.txt", true)) {
            writer.write(
                    System.lineSeparator() +
                    bike.getVehicleId() + "," + bike.getBrand() + "," + bike.getModel() + "," + bike.getBaseRatePerDay() + "," + bike.getEngineCapacityCC() + "\n"
            );
        } catch (IOException e) {
            System.out.println("Error saving bike to file.");
        }
    }

    private static void saveVanToFile(Van van) {
        try (FileWriter writer = new FileWriter("Vans.txt", true)) {
            writer.write(
                    System.lineSeparator() +
                    van.getVehicleId() + "," + van.getBrand() + "," + van.getModel() + "," + van.getBaseRatePerDay() + "," + van.getCargoCapacityKg() + "\n"
            );
        } catch (IOException e) {
            System.out.println("Error saving van to file.");
        }
    }

    private static void saveBusToFile(Bus bus) {
        try (FileWriter writer = new FileWriter("Buses.txt", true)) {
            writer.write(
                    System.lineSeparator() +
                    bus.getVehicleId() + "," + bus.getBrand() + "," + bus.getModel() + "," + bus.getBaseRatePerDay() + "," + bus.getPassengerCapacity() + "," + bus.isAcAvailable() + "\n"
            );
        } catch (IOException e) {
            System.out.println("Error saving bus to file.");
        }
    }

    //View vehicles
    private static void viewVehicles() {
        for (Vehicle v : vehicles) {
            v.displayDetails();
            System.out.println("-------------------------------------------------------");
        }
    }
    private static void saveRentalIncomeToFile(String vehicleID, int days, double cost) {
        try (FileWriter writer = new FileWriter("RentalIncome.txt", true)) {
            writer.write("Vehicle ID: " + vehicleID + " |    Days: " + days + " |   Cost: " + cost + " LKR\n");
        } catch (IOException e) {
            System.out.println("Error writing down rental income to file.");
        }
    }

    //Rent a vehicle
    private static void rentVehicle() {
        sc.nextLine();
        System.out.print("Enter Vehicle ID: ");
        String id = sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                if(!v.isAvailable()) {
                    System.out.println("Vehicle is already rented!");
                    return;
                }
                System.out.print("Enter number of rental days: ");
                int days = sc.nextInt();

                if (days <= 0) {
                    System.out.println("Days must be greater than zero!");
                    return;
                }

                double cost = v.calculateRentalCost(days);
                v.rentVehicle();
                totalIncome += cost;
                saveRentalIncomeToFile(v.getVehicleId(), days, cost);

                System.out.println("Vehicle rented successfully!");
                System.out.println("Total Rental Cost: " + cost + "LKR");
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    //Return a vehicle
    private static void returnVehicle() {
        sc.nextLine();
        System.out.print("Enter vehicle ID: ");
        String id = sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                if (v.isAvailable()) {
                    System.out.println("Vehicle was not rented!");
                } else {
                    v.returnVehicle();
                    System.out.println("Vehicle returned successfully!");
                }
                return;
            }
        }

        System.out.println("Vehicle not found!");
    }

    //Search vehicle
    //Challenging Component - Advanced Search and Filtering
    private static void searchMenu() {
        System.out.println("\n----- Search Vehicles -----");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Brand");
        System.out.println("3. Search by Model");
        System.out.println("4. Search by availability status");
        System.out.println("5. Exit");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> searchVehicleByID();
            case 2 -> searchVehicleByBrand();
            case 3 -> searchVehicleByModel();
            case 4 -> searchByAvailability();
            case 5 -> System.out.println("Exit");
        }
    }

    //Search vehicle by ID
    private static void searchVehicleByID() {
        System.out.print("Enter Vehicle ID: ");
        String id = sc.nextLine();

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                v.displayDetails();
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }


    //Search vehicle by brand
    private static void searchVehicleByBrand() {
        System.out.print("Enter Vehicle Brand: ");
        String brand = sc.nextLine();

        boolean found = false; // Start with not found status

        // Search through every item one by one
        for (Vehicle v: vehicles) {
            if (v.getBrand().equalsIgnoreCase(brand)) {
                v.displayDetails();
                System.out.println("\n-------------------------\n");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Vehicle Brand not found!");
        }
    }



    //Search vehicle by model
    private static void searchVehicleByModel() {
        System.out.print("Enter Vehicle Model: ");
        String model = sc.nextLine();

        boolean found = false; // Start with not found status

        // Search through every item one by one
        for (Vehicle v: vehicles) {
            if (v.getModel().equalsIgnoreCase(model)) {
                v.displayDetails();
                System.out.println("\n-------------------------\n");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Vehicle Model not found!");
        }
    }


    //Search vehicle by availability status
    private static void searchByAvailability(){
        System.out.println("1. Show Available Vehicles");
        System.out.println("2. Show Rented Vehicles");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        boolean found = false;
        for (Vehicle v: vehicles) {
            if ( choice ==1 && v.isAvailable()||choice == 2&& !v.isAvailable()) {
                v.displayDetails();
                System.out.println("\n-------------------------\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Vehicle Availability not found!");
        }
    }

    //Challenging Component - Sorting Mechanism
    //Sort vehicles by base rate, brand name, or vehicle type
    private static void sortMenu(){
        System.out.println("\n----- Sort Vehicles -----\n");
        System.out.println("1. Base rate - Low to High");
        System.out.println("2. Brand Name");
        System.out.println("3. Vehicle Type");
        System.out.println("4. Exit");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1-> vehicles.sort(Comparator.comparingDouble(Vehicle::getBaseRatePerDay));  // sort base rate low to high
            case 2-> vehicles.sort(Comparator.comparing(Vehicle:: getBrand,String.CASE_INSENSITIVE_ORDER)); // sort brand name
            case 3-> vehicles.sort(Comparator.comparing(v -> v.getClass().getSimpleName())); // sort type
            case 4 -> System.out.println("Exit");
        }
        if (choice == 1 || choice == 2 || choice == 3 ) {
            viewVehicles();
        }
    }

}
















