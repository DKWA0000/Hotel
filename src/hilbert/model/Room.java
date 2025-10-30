package hilbert.model;

public class Room {

    private int id;
    private String roomNumber;
    private String type;
    private double price;

    public Room(int id, String roomNumber, String type, double price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    public Room(String roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room id=" + id + ", roomNumber=" + roomNumber + ", type=" + type + ", price=" + price ;
    }
}
