import java.util.HashMap;

// Room domain class
abstract class Room {
    protected String name;
    protected double price;
    protected String amenities;

    public Room(String name, double price, String amenities) {
        this.name = name;
        this.price = price;
        this.amenities = amenities;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }

    public void displayDetails() {
        System.out.println(name + " - $" + price + " per night | Amenities: " + amenities);
    }
}

// Specific room types
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single", 50.0, "1 Bed, Free Wi-Fi");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double", 90.0, "2 Beds, Free Wi-Fi, TV");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite", 150.0, "2 Beds, Free Wi-Fi, TV, Mini Bar, Living Area");
    }
}

// Centralized Room Inventory (read/write allowed)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        if (count >= 0) inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public boolean updateAvailability(String roomType, int change) {
        if (!inventory.containsKey(roomType)) return false;
        int updated = inventory.get(roomType) + change;
        if (updated < 0) return false;
        inventory.put(roomType, updated);
        return true;
    }

    public HashMap<String, Integer> getAllAvailability() {
        // Return a copy to prevent modification
        return new HashMap<>(inventory);
    }
}

// Search service: read-only operations
class RoomSearchService {
    private RoomInventory inventory;
    private Room[] rooms;

    public RoomSearchService(RoomInventory inventory, Room[] rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getName());
            if (available > 0) {  // only show rooms with availability
                room.displayDetails();
                System.out.println("Rooms Available: " + available + "\n");
            }
        }
    }
}

// Main class
public class UseCase4RoomSearch {
    public static void main(String[] args) {
        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 10);
        inventory.addRoomType("Double", 5);
        inventory.addRoomType("Suite", 2);

        // Initialize room objects
        Room[] rooms = {new SingleRoom(), new DoubleRoom(), new SuiteRoom()};

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory, rooms);

        // Display available rooms (read-only)
        searchService.displayAvailableRooms();

        // Example: inventory remains unchanged
        System.out.println("After search, inventory remains unchanged:");
        System.out.println("Single rooms available: " + inventory.getAvailability("Single"));
        System.out.println("Double rooms available: " + inventory.getAvailability("Double"));
        System.out.println("Suite rooms available: " + inventory.getAvailability("Suite"));
    }
}