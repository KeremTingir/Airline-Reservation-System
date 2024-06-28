// Uçuş koltuğunu temsil eden sınıf
class FlightSeat {
    String flightNumber;
    String date;
    boolean isReserved;

    public FlightSeat(String flightNumber, String date) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.isReserved = false;  // Başlangıçta koltuk rezerve edilmemiş durumda
    }
}