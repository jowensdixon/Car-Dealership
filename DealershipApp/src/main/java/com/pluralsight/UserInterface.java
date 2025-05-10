package com.pluralsight;
import java.util.*;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager fileManager = new DealershipFileManager();
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        init();
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: processPriceSearch(); break;
                case 2: processMakeModelSearch(); break;
                case 3: processYearSearch(); break;
                case 4: processColorSearch(); break;
                case 5: processMileageSearch(); break;
                case 6: processTypeSearch(); break;
                case 7: processAllVehiclesRequest(); break;
                case 8: processAddVehicle(); break;
                case 9: processRemoveVehicle(); break;
                case 99: return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void init() {
        dealership = fileManager.getDealership();
    }

    private void printMenu() {
        System.out.println("\nDEALERSHIP MENU");
        System.out.println("1 - Find by Price\n2 - Find by Make/Model\n3 - Find by Year\n4 - Find by Color");
        System.out.println("5 - Find by Mileage\n6 - Find by Type\n7 - List All\n8 - Add\n9 - Remove\n99 - Quit");
        System.out.print("Enter choice: ");
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processPriceSearch() {
        System.out.print("Enter min price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter max price: ");
        double max = scanner.nextDouble();
        displayVehicles(dealership.searchByPrice(min, max));
    }

    private void processMakeModelSearch() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.searchByMakeModel(make, model));
    }

    private void processYearSearch() {
        System.out.print("Enter min year: ");
        int min = scanner.nextInt();
        System.out.print("Enter max year: ");
        int max = scanner.nextInt();
        displayVehicles(dealership.searchByYear(min, max));
    }

    private void processColorSearch() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.searchByColor(color));
    }

    private void processMileageSearch() {
        System.out.print("Min mileage: ");
        int min = scanner.nextInt();
        System.out.print("Max mileage: ");
        int max = scanner.nextInt();
        displayVehicles(dealership.searchByMileage(min, max));
    }

    private void processTypeSearch() {
        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.searchByType(type));
    }

    private void processAddVehicle() {
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt(); scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added.");
    }

    private void processRemoveVehicle() {
        System.out.print("Enter VIN to remove: ");
        int vin = scanner.nextInt();
        dealership.removeVehicle(vin);
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle removed.");
    }
}

