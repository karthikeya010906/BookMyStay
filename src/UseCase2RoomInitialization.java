/**
 * Book My Stay App
 * Use Case 2: Basic Room Types & Static Availability
 * Version 2.1
 *
 * Demonstrates abstract class, inheritance, polymorphism, and
 * static room availability.
 *
 * Author: Karthikeya
 */

// Abstract room class
abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public abstract void displayDetails();

    public String getRoomType() { return roomType; }
    public int getNumberOfBeds() { return numberOfBeds; }
    public double getPricePerNight() { return pricePerNight; }
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 50.0); }
    @Override
    public void displayDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 90.0); }
    @Override
    public void displayDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 150.0); }
    @Override
    public void displayDetails() {
        System.out.println(getRoomType() + " | Beds: " + getNumberOfBeds() + " | Price: $" + getPricePerNight());
    }
}

// Application entry point for UC2
public class UseCase2RoomInitialization {

    // Static availability variables
    static int availableSingleRooms = 5;
    static int availableDoubleRooms = 3;
    static int availableSuiteRooms = 2;

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay App v2.1          ");
        System.out.println("      Room Initialization Demo       ");
        System.out.println("=====================================");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("\nAvailable Rooms:");
        single.displayDetails();
        System.out.println("Available: " + availableSingleRooms);

        doubleRoom.displayDetails();
        System.out.println("Available: " + availableDoubleRooms);

        suite.displayDetails();
        System.out.println("Available: " + availableSuiteRooms);

        System.out.println("\nApplication Terminated Successfully.");
    }
}