public class Booking {
    private String locationNumber;
    private String clientId;
    private String clientName;
    private String agencyId;
    private String price;
    private String roomIdType;
    private String hotelId;
    private String hotelName;
    private String checkIn;
    private String roomNights;

    public Booking(String locationNumber, String clientId, String clientName, String agencyId, String price,
                   String roomIdType, String hotelId, String hotelName, String checkIn, String roomNights) {
        this.locationNumber = locationNumber;
        this.clientId = clientId;
        this.clientName = clientName;
        this.agencyId = agencyId;
        this.price = price;
        this.roomIdType = roomIdType;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.checkIn = checkIn;
        this.roomNights = roomNights;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoomIdType() {
        return roomIdType;
    }

    public void setRoomIdType(String roomIdType) {
        this.roomIdType = roomIdType;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getRoomNights() {
        return roomNights;
    }

    public void setRoomNights(String roomNights) {
        this.roomNights = roomNights;
    }
}
