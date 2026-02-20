public class Bus extends Vehicle{
    public int passengerCapacity;
    private boolean acAvailable;

    public Bus(String vehicleId, String brand, String model, double baseRatePerDay, int passengerCapacity, boolean acAvailable) {
        super(vehicleId, brand, model, baseRatePerDay);
        this.passengerCapacity = passengerCapacity;
        this.acAvailable = acAvailable;
    }

    public int getPassengerCapacity() {return passengerCapacity;}
    public boolean isAcAvailable() {return acAvailable;}

    @Override
    public double calculateRentalCost(int days) {
        double cost = getBaseRatePerDay() * days;

        if (passengerCapacity > 50) {
            cost += 2000 * days;
        }

        if (acAvailable) {
            cost += 3000 * days;
        }
        return cost;
    }
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("AC Available: " + (acAvailable ? "Yes" : "No"));
    }
}
