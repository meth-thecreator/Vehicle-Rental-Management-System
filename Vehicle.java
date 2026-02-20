public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String brand, String model, double baseRatePerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = true;
    }

    //Getters
    public String getVehicleId() {return vehicleId;}
    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public double getBaseRatePerDay() {return baseRatePerDay;}
    public boolean isAvailable() {return isAvailable;}

    //Setters
    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId;}
    public void setBrand(String brand) {this.brand = brand;}
    public void setModel(String model) {this.model = model;}
    public void setBaseRatePerDay(double baseRatePerDay) {this.baseRatePerDay = baseRatePerDay;}
    public void setIsAvailable(boolean available) {this.isAvailable = available;}

    //Display details
    public void displayDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Base rate per day: " + baseRatePerDay);
        System.out.println("Availability: " + isAvailable);
    }

    //Rent Vehicle
    public void rentVehicle() {
        isAvailable = false;
    }

    //Return Vehicle
    public void returnVehicle() {
        isAvailable = true;
    }

    //Abstract double calculate rental cost
    public abstract double calculateRentalCost(int days);

}
