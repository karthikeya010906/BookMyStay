import java.util.HashMap;

public class UseCase3InventorySetup {

    // Centralized Room Inventory Class
    static class RoomInventory {
        private HashMap<String, Integer> inventory; // centralized inventory

        // Constructor to initialize inventory
        public RoomInventory() {
            inventory = new HashMap<>();
        }

        // Register a new room type with initial availability
        public void addRoomType(String roomType, int count) {
            if (count < 0) {
                System.out.println("Cannot add negative room count.");
                return;
            }
            inventory.put(roomType, count);
        }

        // Retrieve availability for a specific room type
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        // Update room availability (booking or cancellation)
        public boolean updateAvailability(String roomType, int change) {
            if (!inventory.containsKey(roomType)) {
                System.out.println("Room type does not exist.");
                return false;
            }

            int current = inventory.get(roomType);
            int updated = current + change;

            if (updated < 0) {
                System.out.println("Cannot reduce availability below zero.");
                return false;
            }

            inventory.put(roomType, updated);
            return true;
        }

        // Display the current inventory state
        public void displayInventory() {
            System.out.println("Current Room Inventory:");
            for (String roomType : inventory.keySet()) {
                System.out.println(roomType + ": " + inventory.get(roomType) + " rooms available");
            }
        }
    }

    // Main method to test the inventory
    public static void main(String[] args) {
        // Initialize the centralized room inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.addRoomType("Single", 10);
        inventory.addRoomType("Double", 5);
        inventory.addRoomType("Suite", 2);

        // Display initial inventory
        inventory.displayInventory();

        // Simulate booking 2 Single rooms
        System.out.println("\nBooking 2 Single rooms...");
        inventory.updateAvailability("Single", -2);
        inventory.displayInventory();

        // Simulate adding 1 Suite (cancellation)
        System.out.println("\nAdding 1 Suite (cancellation)...");
        inventory.updateAvailability("Suite", 1);
        inventory.displayInventory();

        // Attempt to overbook
        System.out.println("\nAttempting to book 6 Double rooms...");
        boolean success = inventory.updateAvailability("Double", -6);
        if (!success) {
            System.out.println("Booking failed due to insufficient availability.");
        }
        inventory.displayInventory();
    }
}