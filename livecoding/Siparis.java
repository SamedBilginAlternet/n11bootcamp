import java.util.*;
import java.util.stream.*;

public class Siparis {

    // --- Model ---
    private String musteri;
    private double tutar;
    private String kategori;

    public Siparis(String musteri, double tutar, String kategori) {
        this.musteri = musteri;
        this.tutar = tutar;
        this.kategori = kategori;
    }

    public String getMusteri() { return musteri; }
    public double getTutar() { return tutar; }
    public String getKategori() { return kategori; }

    @Override
    public String toString() {
        return musteri + " | " + kategori + " | " + tutar + " TL";
    }

    // --- Cozum ---
    public static void main(String[] args) {

        // Test verisi
        List<Siparis> siparisler = List.of(
            new Siparis("Ali",     1500, "Elektronik"),
            new Siparis("Veli",     200, "Giyim"),
            new Siparis("Ayse",    3200, "Elektronik"),
            new Siparis("Fatma",    800, "Kozmetik"),
            new Siparis("Mehmet",   450, "Giyim"),
            new Siparis("Zeynep", 12000, "Elektronik"),
            new Siparis("Hasan",   1100, "Kozmetik"),
            new Siparis("Deniz",    350, "Giyim")
        );

        System.out.println("=== Tum Siparisler ===");
        siparisler.forEach(System.out::println);

        // 1. Her kategorinin toplam cirosu
        Map<String, Double> kategoriBazliCiro = siparisler.stream()
            .collect(Collectors.groupingBy(
                Siparis::getKategori,
                Collectors.summingDouble(Siparis::getTutar)
            ));

        System.out.println("\n=== Kategori Bazli Toplam Ciro ===");
        kategoriBazliCiro.forEach((k, v) ->
            System.out.println(k + ": " + v + " TL")
        );

        // 2. Buyukten kucuge siralama
        List<Map.Entry<String, Double>> sirali = kategoriBazliCiro.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .collect(Collectors.toList());

        System.out.println("\n=== Ciroya Gore Siralama (Buyuk -> Kucuk) ===");
        int sira = 1;
        for (Map.Entry<String, Double> entry : sirali) {
            System.out.println(sira++ + ". " + entry.getKey() + ": " + entry.getValue() + " TL");
        }

        // 3. En cok ciro yapan kategori
        String enCokCiroKategori = kategoriBazliCiro.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("Kategori bulunamadi");

        double enCokCiro = kategoriBazliCiro.getOrDefault(enCokCiroKategori, 0.0);

        System.out.println("\n=== En Cok Ciro Yapan Kategori ===");
        System.out.println(enCokCiroKategori + ": " + enCokCiro + " TL");
    }
}
