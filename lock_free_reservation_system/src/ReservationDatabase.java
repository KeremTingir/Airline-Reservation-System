import java.util.HashMap;
import java.util.Map;

// Rezervasyon veritabanını temsil eden sınıf
class ReservationDatabase {
    private final Map<String, FlightSeat> seats;  // Koltuk bilgilerini tutan harita

    public ReservationDatabase() {
        this.seats = new HashMap<>();
    }

    // Veritabanına yeni bir koltuk ekleme metodu
    public void addSeat(String flightNumber, String date) {
        String key = flightNumber + "-" + date;
        seats.put(key, new FlightSeat(flightNumber, date));
    }

    // Belirli bir uçuş ve tarih için koltuk bilgisi getirme metodu
    public FlightSeat getSeat(String flightNumber, String date) {
        String key = flightNumber + "-" + date;
        return seats.get(key);
    }

    // Tüm koltuk bilgilerini döndüren metot
    public Map<String, FlightSeat> getAllSeats() {
        return seats;
    }
}