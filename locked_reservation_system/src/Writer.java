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
        db.makeReservation(flightNumber, date);
    }

    // Koltuk rezervasyonunu iptal etme metodu
    public void cancelReservation() {
        db.cancelReservation(flightNumber, date);
    }

    @Override
    public void run() {
        makeReservation();
    }
}