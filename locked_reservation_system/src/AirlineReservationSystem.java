public class AirlineReservationSystem {
    public static void main(String[] args) {
        ReservationDatabase db = new ReservationDatabase();
        db.addSeat("FL123", "2024-06-01");  // Veritabanına bir koltuk ekleyelim

        // Reader ve writer thread'leri oluşturalım
        Thread reader1 = new Thread(new Reader(db, "FL123", "2024-06-01"));
        Thread writer1 = new Thread(new Writer(db, "FL123", "2024-06-01"));
        Thread writer2 = new Thread(new Writer(db, "FL123", "2024-06-01"));
        Thread reader2 = new Thread(new Reader(db, "FL123", "2024-06-01"));

        // Thread'leri başlatalım
        reader1.start();
        writer1.start();
        writer2.start();
        reader2.start();

        // Thread'lerin tamamlanmasını bekleyelim
        try {
            reader1.join();
            writer1.join();
            writer2.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Son durum çıktısı
        System.out.println("Final Seat Status:");
        db.getAllSeats().forEach((key, seat) -> {
            System.out.println(key + ": " + (seat.isReserved ? "Reserved" : "Available"));
        });
    }
}
