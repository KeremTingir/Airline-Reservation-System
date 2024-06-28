class Reader implements Runnable {
    private final ReservationDatabase db;
    private final String flightNumber;
    private final String date;

    public Reader(ReservationDatabase db, String flightNumber, String date) {
        this.db = db;
        this.flightNumber = flightNumber;
        this.date = date;
    }

    @Override
    public void run() {
        FlightSeat seat = db.getSeat(flightNumber, date);
        if (seat != null) {
            System.out.println("Reader checking seat for "
                    + flightNumber
                    + " on "
                    + date
                    + ": "
                    + (seat.isReserved ? "Reserved" : "Available"));
        } else {
            System.out.println("Seat not found for " + flightNumber + " on " + date);
        }
    }
}
