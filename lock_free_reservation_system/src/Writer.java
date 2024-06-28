// Writer thread'ini temsil eden sınıf
class Writer implements Runnable {
    private final ReservationDatabase db;
    private final String flightNumber;
    private final String date;

    public Writer(ReservationDatabase db, String flightNumber, String date) {
        this.db = db;
        this.flightNumber = flightNumber;
        this.date = date;
    }

    // Koltuk rezervasyonu yapma metodu
    public void makeReservation() {
        synchronized (db) {
            FlightSeat seat = db.getSeat(flightNumber, date);
            if (seat != null && !seat.isReserved) {
                seat.isReserved = true;
                System.out.println("Writer reserved seat for " + flightNumber + " on " + date);
            } else {
                System.out.println("Seat already reserved or not found for " + flightNumber + " on " + date);
            }
        }
    }

    // Koltuk rezervasyonunu iptal etme metodu
    public void cancelReservation() {
        synchronized (db) {
            FlightSeat seat = db.getSeat(flightNumber, date);
            if (seat != null && seat.isReserved) {
                seat.isReserved = false;
                System.out.println("Writer canceled reservation for " + flightNumber + " on " + date);
            } else {
                System.out.println("Seat not reserved or not found for " + flightNumber + " on " + date);
            }
        }
    }

    @Override
    public void run() {
        makeReservation();
    }
}