package HotelManagement;


import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class Hotel {
    private int HotelId;
    private String HotelName;
    private String HotelLocation;
    private int NoOfRoom;
    private float CostPerRoom;

    static HashMap<Integer, Hotel> hoteldetail = new HashMap<>();

    static int countHotel = 1;

    public void setHotelId(int hotelId) {
        HotelId = hotelId;

    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public void setHotelLocation(String hotelLocation) {
        HotelLocation = hotelLocation;
    }

    public void setNoOfRoom(int noOfRooms) {
        NoOfRoom = noOfRooms;
    }

    public void setCostPerRoom(float costPerRoom) {
        CostPerRoom = costPerRoom;
    }

    public Integer getHotelId() {
        return HotelId;
    }

    public String getHotelName() {
        return HotelName;
    }

    public String getHotelLocation() {
        return HotelLocation;
    }

    public Integer getNoOfRoom() {
        return NoOfRoom;
    }

    public float getCostPerRoom() {
        return CostPerRoom;
    }

}

    public class Main {
        public static void printMenu() {
    
            System.out.println("1. To create a new HotelId");
            System.out.println("2. To remove a  Hotel");
            System.out.println("3. To update a Hotel");
            System.out.println("4. To display all a Hotel");
            System.out.println("5. To search a  Hotel");
            System.out.println("6. To exit application");
        }

        private static void toCreateAHotel() {
            System.out.println("Welcome - creating a hotel:\n");
            Hotel obHotel = new Hotel();

            Scanner input = new Scanner(System.in);

            System.out.println("Enter a Name: ");
            obHotel.setHotelName(input.next());

            System.out.println("Enter a Location: ");
            obHotel.setHotelLocation(input.next());

            System.out.println("Enter a roomNum: ");
            obHotel.setNoOfRoom(input.nextInt());

            System.out.println("Enter cost per room: ");
            obHotel.setCostPerRoom(input.nextFloat());

            DbOperations.toCreateAHotelData(obHotel); // Pass obHotel instead of hotelObj

            System.out.println("Successfully created\n");
        }


    private static void toRemoveAHotel() {
        System.out.println("Welcome - Remove a hotel:\n");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a ID: ");
        int id = input.nextInt();
        DbOperations.toRemoveAHotelData(id);
    }
//        
//        if (Hotel.hoteldetail.containsKey(id)) {
//            Hotel.hoteldetail.remove(id);
//            System.out.println("removed successfully\n");
//        } else {
//            System.out.println("id does not exist\n");
//        }
//
//    }

    private static void toUpdateAHotel() {
        System.out.println("Welcome - update a hotel:\n");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the ID of the hotel to be Update:");
        int id = input.nextInt();
        if (DbOperations.hotelExists(id)) {
            System.out.println("1.HotelName");
            System.out.println("2.HotelLocation");
            System.out.println("3.RoomNumber:");
            System.out.println("4.CostPerRoom");
            System.out.print("Enter a choice");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the Name:");
                    String name = input.next();
//                    Hotel.hotelDetail.get(id).setHotelName(name);
                    DbOperations.toUpdatehotelName(name,id);

                    break;
                case 2:
                    System.out.println("Enter the Location:");
                    String location = input.next();
                    DbOperations.toUpdatehotellocation(location,id);
                    break;
                case 3:
                    System.out.println("Enter the count of available rooms:");
                    Integer NoOfRoom = input.nextInt();
//                    Hotel.hotelDetail.get(id).setNoOfRooms(NoOfRooms);
                    DbOperations.toUpdateNoOfRoom(NoOfRoom);

                    break;
                case 4:
                    System.out.println("Enter the CostPerRoom:");
                    Float CostPerRoom = input.nextFloat();
//                    Hotel.hotelDetail.get(id).setCostPerRoom(CostPerRoom);
                    DbOperations.toUpdateCostPerRoom(CostPerRoom,id);
                    break;
                default:
                    System.err.println("invalid choice:\n");
                    return;
            }
            System.err.println("hotel data updated successfully!!1\n");
        } else {
            System.out.println("hotel does not exit with this id!!\n");

        }
    }

    private static void toDisplayAHotel() {
        HashMap<Integer, Hotel> hoteldetails = DbOperations.toDisplayHotel();
        System.out.println("Welcome - a Display hotel:\n");
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.print("ID \t\t ");
        System.out.print("Name \t\t ");
        System.out.print("Location \t\t ");
        System.out.print("Rooms \t\t ");
        System.out.println("cost \t\t ");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, Hotel> dataEntry : hoteldetails.entrySet()) {
            System.out.print(dataEntry.getValue().getHotelId() + "\t\t");
            System.out.print(dataEntry.getValue().getHotelName() + "\t\t");
            System.out.print(dataEntry.getValue().getHotelLocation() + "\t\t\t");
            System.out.print(dataEntry.getValue().getNoOfRoom() + "\t\t");
            System.out.println(dataEntry.getValue().getCostPerRoom() + "\t\t");
            System.out.println("--------------------------------------------------------------------------------------");
        }
    }
 
    private static void toSearchAHotel() {
        System.out.println("Welcome - Search a hotel:\n");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the hotel ID to be searched:\n");
        int id = input.nextInt();
        
        if (DbOperations.hotelExists(id)) {
            Hotel hotel = DbOperations.toGethoteldata(id);
//            if (hotel != null) {
                System.out.println("HotelName:\n" + hotel.getHotelName());
                System.out.println("HotelLocation:\n" + hotel.getHotelLocation());
                System.out.println("RoomNumber:\n" + hotel.getNoOfRoom());
                System.out.println("CostPerRoom:\n" + hotel.getCostPerRoom());
                System.out.println("Hotel details displayed successfully.\n");
           } 
//                else {
//                System.out.println("Hotel with ID " + id + " exists but has null details.\n");
//            }
         else {
            System.out.println("Hotel with ID " + id + " does not exist.\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hotel\n");

        Scanner input = new Scanner(System.in); 
        while (true) {
            System.out.println("1. Create a Hotel");
            System.out.println("2. Remove a Hotel");
            System.out.println("3. Update a Hotel");
            System.out.println("4. Display all Hotels");
            System.out.println("5. Search a Hotel");
            System.out.println("6. Exit");
            System.out.println("Enter a Choice:");

            int choice = input.nextInt(); 
            switch (choice) {
                case 1:
                    toCreateAHotel();
                    break;
                case 2:
                    toRemoveAHotel();
                    break;
                case 3:
                    toUpdateAHotel();
                    break;
                case 4:
                    toDisplayAHotel();
                    break;
                case 5:
                    toSearchAHotel();
                    break;
                case 6:
                    System.out.println("Application is Exiting");
                    input.close(); // Close the Scanner before exiting
                    return;
                default:
                    System.out.println("Invalid choice!!!");
                    break;
            }
        }
    

    }
}