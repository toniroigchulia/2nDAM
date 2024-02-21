import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingsBean {
    private int locationNumber;
    private int clientId;
    private String clientName;
    private int agencyId;
    private String agencyName;
    private BigDecimal price;
    private int roomTypeId;
    private String hotelName;
    private LocalDate checkIn;
    private int roomNights;

    public BookingsBean(int locationNumber, int clientId, String clientName, int agencyId, String agencyName,
            BigDecimal price, int roomTypeId, String hotelName, LocalDate checkIn, int roomNights) {
        this.locationNumber = locationNumber;
        this.clientId = clientId;
        this.clientName = clientName;
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.price = price;
        this.roomTypeId = roomTypeId;
        this.hotelName = hotelName;
        this.checkIn = checkIn;
        this.roomNights = roomNights;
    }

    public int getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public int getRoomNights() {
        return roomNights;
    }

    public void setRoomNights(int roomNights) {
        this.roomNights = roomNights;
    }
}