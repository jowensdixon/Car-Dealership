package com.pluralsight;
import java.io.*;


public class DealershipFileManager {
    public Dealership getDealership() {
        Dealership dealership = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("\\|");
                dealership = new Dealership(parts[0], parts[1], parts[2]);
            }
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),
                        parts[2], parts[3], parts[4], parts[5],
                        Integer.parseInt(parts[6]), Double.parseDouble(parts[7])
                );
                dealership.addVehicle(vehicle);
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory file.");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/inventory.csv"))) {
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.println(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" +
                        v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" +
                        v.getOdometer() + "|" + v.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory file.");
        }
    }
}
