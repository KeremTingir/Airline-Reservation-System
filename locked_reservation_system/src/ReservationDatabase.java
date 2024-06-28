import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.HashMap;
import java.util.Map;

class ReservationDatabase {
    private final Map<String, FlightSeat> seats;
    private final ReadWriteLock lock;

    public ReservationDatabase() {
        this.seats = new HashMap<>();
        this.lock = new ReentrantReadWriteLock(true);  // Adil kilit kullanarak yazıcı önceliği sağlanır
    }

    // Veritabanına yeni bir koltuk ekleme metodu
    public void addSeat(String flightNumber, String date) {
        lock.writeLock().lock();  // Yazma kilidi alındı
        try {
            String key = flightNumber + "-" + date;
            seats.put(key, new FlightSeat(flightNumber, date));
        } finally {
            lock.writeLock().unlock();  // Yazma kilidi bırakıldı
        }
    }

    // Belirli bir uçuş ve tarih için koltuk bilgisi getirme metodu
    public FlightSeat getSeat(String flightNumber, String date) {
        lock.readLock().lock();  // Okuma kilidi alındı
        try {
            String key = flightNumber + "-" + date;
            return seats.get(key);
        } finally {
            lock.readLock().unlock();  // Okuma kilidi bırakıldı
        }
    }

    // Tüm koltuk bilgilerini döndüren metot
    public Map<String, FlightSeat> getAllSeats() {
        lock.readLock().lock();  // Okuma kilidi alındı
        try {
            return new HashMap<>(seats);
        } finally {
            lock.readLock().unlock();  // Okuma kilidi bırakıldı
        }
    }

    // Koltuk rezervasyonu yapma metodu
    public void makeReservation(String flightNumber, String date) {
        lock.writeLock().lock();  // Yazma kilidi alındı
        try {
            FlightSeat seat = getSeat(flightNumber, date);
            if (seat != null && !seat.isReserved) {
                seat.isReserved = true;
                System.out.println("Writer reserved seat for " + flightNumber + " on " + date);
            } else {
                System.out.println("Seat already reserved or not found for " + flightNumber + " on " + date);
            }
        } finally {
            lock.writeLock().unlock();  // Yazma kilidi bırakıldı
        }
    }

    // Koltuk rezervasyonunu iptal etme metodu
    public void cancelReservation(String flightNumber, String date) {
        lock.writeLock().lock();  // Yazma kilidi alındı
        try {
            FlightSeat seat = getSeat(flightNumber, date);
            if (seat != null && seat.isReserved) {
                seat.isReserved = false;
                System.out.println("Writer canceled reservation for " + flightNumber + " on " + date);
            } else {
                System.out.println("Seat not reserved or not found for " + flightNumber + " on " + date);
            }
        } finally {
            lock.writeLock().unlock();  // Yazma kilidi bırakıldı
        }
    }
}