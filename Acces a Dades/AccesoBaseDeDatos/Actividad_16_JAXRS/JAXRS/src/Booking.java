import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {

    @JsonProperty("idLocation")
    private int idLocation;

    @JsonProperty("client")
    private String client;

    @JsonProperty("agency")
    private String agency;

    @JsonProperty("price")
    private String price;

    @JsonProperty("room_type")
    private String room_type;

    @JsonProperty("hotel")
    private String hotel;

    @JsonProperty("check_in")
    private String check_in;

    @JsonProperty("room_nights")
    private String room_nights;

    public Booking(int idLocation, String client, String agency, String price, String room_type, String hotel, String check_in, String room_nights) {
        this.idLocation = idLocation;
        this.client = client;
        this.agency = agency;
        this.price = price;
        this.room_type = room_type;
        this.hotel = hotel;
        this.check_in = check_in;
        this.room_nights = room_nights;
    }

    public Booking() {
        this.idLocation = -1;
        this.client = "";
        this.agency = "";
        this.price = "";
        this.room_type = "";
        this.hotel = "";
        this.check_in = "";
        this.room_nights = "";
    }


    public int getLocationId() {
        return idLocation;
    }

    public void setLocationId(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getRoom_nights() {
        return room_nights;
    }

    public void setRoom_nights(String room_nights) {
        this.room_nights = room_nights;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "idLocation=" + idLocation +
                ", client='" + client + '\'' +
                ", agency='" + agency + '\'' +
                ", price='" + price + '\'' +
                ", room_type='" + room_type + '\'' +
                ", hotel='" + hotel + '\'' +
                ", check_in='" + check_in + '\'' +
                ", room_nights='" + room_nights + '\'' +
                '}';
    }
}
