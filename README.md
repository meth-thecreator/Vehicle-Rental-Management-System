README - Vehicle Rental Management System

Project Title
Vehicle Rental Management System (Command-Line Application)

Module
Object-Oriented Programming (OOP)

1. How to compile and run the program

Requirements:
Java Development Kit (JDK 8 or higher)
Command Prompt / Terminal
All .java files and text files in the same folder

Files required:
Vehicle.java
Car.java
Bike.java
Van.java
Bus.java
RentalApp.java
Cars.txt
Bikes.txt
Vans.txt
Buses.txt

Steps to compile:
Open Command Prompt / Terminal
Navigate to the project folder using: 
cd <path_to_the_folder>
Compile all Java files:
javac RentalApp.java

If there are no errors, .class files will be generated.

Steps to run:
After successful compilation, run:
java RentalApp
The system menu will appear in the console.



2. Assumptions made
Each vehicle has a unique Vehicle ID
Rental cost is calculated per day based on vehicle type
A vehicle cannot be rented if it is already rented
A vehicle must be rented before it can be returned
Rental days must be greater than zero
Vehicle data is stored in separate text files for each vehicle type
If data files are missing, the system will display an error but still run
Rental income is appended to a text file named RentalIncome.txt



3. Sample Menu usage
Main Menu
==== VEHICLE RENTAL SYSTEM ====
Admin Mode
User Mode
Exit
Enter your choice: 

Admin mode example
==== ADMIN MENU ====
Add vehicle
View all vehicles
Get Total Rental Income
Search Vehicle
Sort Vehicle
Exit
Enter your choice:

Example: Adding a Car
Select Vehicle Type: 1. Car 	2. Bike 	3. Van 		4.Bus
Enter Vehicle ID: C006
Brand: Toyota
Model: Carmodel
Base Rate Per Day: 190 
Number of seats: 5
Vehicle added successfully

User mode example
==== USER MENU ====
Rent a vehicle
Return a vehicle
Search vehicles
View all vehicles
Sort vehicles
Back
Enter your choice:

Example: Renting a vehicle
Enter Vehicle ID: C003
Enter number of rental days: 3
Vehicle rented successfully!
Total Rental Cost: 10500.0LKR

Example: Returning a vehicle
Enter vehicle ID: C003
Vehicle returned successfully!

Search Example:
----- Search Vehicles -----
Search by ID
Search by Brand
Search by Model
Search by availability status
Exit
Choice:

Sorting Example:
----- Sort Vehicles -----
Base rate - Low to High
Brand Name
Vehicle Type
Exit
Choice: 

Author:
Methara Kuruppu
