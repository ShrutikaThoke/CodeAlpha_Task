import java.io.*;
import java.util.*;

class Room {
    String type;
    int roomNumber;
    boolean isBooked;

    Room(String type, int roomNumber) {
        this.type = type;
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }
}

class Booking {
    String customerName;
    Room room;
    boolean isPaid;

    Booking(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
        this.isPaid = false;
    }

    void makePayment() {
        this.isPaid = true;
        System.out.println("💳 Payment Successful for " + customerName);
    }

    void printDetails() {
        System.out.println("👤 Name: " + customerName);
        System.out.println("🏨 Room Type: " + room.type + ", Room No: " + room.roomNumber);
        System.out.println("💰 Paid: " + (isPaid ? "Yes" : "No"));
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeRooms();

        while (true) {
            System.out.println("\n==== Hotel Reservation System ====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    bookRoom(sc);
                    break;
                case 3:
                    cancelBooking(sc);
                    break;
                case 4:
                    showBookings();
                    break;
                case 5:
                    saveBookingsToFile();
                    System.out.println("✅ Thank you for using Hotel Reservation System!");
                    return;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    static void initializeRooms() {
        for (int i = 1; i <= 3; i++) rooms.add(new Room("Standard", i));
        for (int i = 4; i <= 6; i++) rooms.add(new Room("Deluxe", i));
        for (int i = 7; i <= 9; i++) rooms.add(new Room("Suite", i));
    }

    static void viewAvailableRooms() {
        System.out.println("\n--- 🏨 Available Rooms ---");
        for (Room r : rooms) {
            if (!r.isBooked) {
                System.out.println("Room No: " + r.roomNumber + " (" + r.type + ")");
            }
        }
    }

    static void bookRoom(Scanner sc) {
        viewAvailableRooms();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter desired room number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        Room selected = null;
        for (Room r : rooms) {
            if (r.roomNumber == roomNo && !r.isBooked) {
                selected = r;
                break;
            }
        }

        if (selected == null) {
            System.out.println("❌ Room is unavailable.");
            return;
        }

        Booking b = new Booking(name, selected);
        b.makePayment();
        bookings.add(b);
        selected.isBooked = true;
        System.out.println("✅ Booking confirmed for " + name);
    }

    static void cancelBooking(Scanner sc) {
        System.out.print("Enter your name to cancel booking: ");
        String name = sc.nextLine();

        Booking toRemove = null;
        for (Booking b : bookings) {
            if (b.customerName.equalsIgnoreCase(name)) {
                toRemove = b;
                break;
            }
        }

        if (toRemove != null) {
            toRemove.room.isBooked = false;
            bookings.remove(toRemove);
            System.out.println("❌ Booking cancelled.");
        } else {
            System.out.println("No booking found under that name.");
        }
    }

    static void showBookings() {
        System.out.println("\n--- 📋 All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println("-------------------------");
            b.printDetails();
        }
    }

    static void saveBookingsToFile() {
        try (PrintWriter pw = new PrintWriter("bookings.txt")) {
            for (Booking b : bookings) {
                pw.println(b.customerName + "," + b.room.roomNumber + "," + b.room.type + "," + b.isPaid);
            }
            System.out.println("💾 Bookings saved to file.");
        } catch (Exception e) {
            System.out.println("⚠ Failed to save bookings.");
        }
    }
}