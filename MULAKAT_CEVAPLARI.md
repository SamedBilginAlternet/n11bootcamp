# n11 Java Mulakat Sorulari ve Cevaplari

---

## CORE JAVA (1-10)

---

### 1. OOP'nin 4 temel prensibi nedir?

**Abstraction (Soyutlama):** Gercek dunyadaki bir nesnenin sadece ihtiyac duyulan ozelliklerini modelleme. Ornegin `Insan` sinifinda sonsuz ozellik yerine sadece ad, soyad, yas tanimliyoruz. Sinif (class) yapisi ile gerceklestirilir.

**Encapsulation (Kapsulleme):** Veri ve metotlarin bir arada tutulup, ic detaylarin disaridan gizlenmesi. Alanlari `private` yapip `public` getter/setter ile erisim saglariz. Ic implementasyon degisse bile dis arayuz ayni kaldigi surece kullanan kodlar etkilenmez.

**Polymorphism (Cok Bicimliligi):** Ayni mesaja farkli nesnelerin farkli yanit vermesi.
- **Compile-time (Static):** Method overloading — ayni isimde farkli parametreli metodlar
- **Runtime (Dynamic):** Method overriding — alt sinif ust sinifin metodunu yeniden tanimlar

```java
Calisan c = new Programci();
c.maasinizNedir(); // Programci'nin implementasyonu calisir (runtime polymorphism)
```

**Inheritance (Miras Alma):** Alt sinifin ust sinifin ozellik ve metotlarini devralmasıdır. Kod tekrarini onler, hiyerarsi olusturur. `extends` ile sinif, `implements` ile interface mirası yapilir.

---

### 2. SOLID prensiplerini aciklayiniz.

**S — Single Responsibility:** Her sinifin tek bir degisim sebebi olmali.
```java
// Kotu: Hem loglama hem mail gonderme
class UserService {
    void register() { }
    void sendEmail() { }
    void writeLog() { }
}

// Iyi: Her sinif tek sorumluluk
class UserService { void register() { } }
class EmailService { void sendEmail() { } }
class LogService { void writeLog() { } }
```

**O — Open/Closed:** Siniflar genislemeye acik, degisiklige kapali olmali.
```java
// Kotu (her yeni tip icin Logger degismeli)
switch(type) { case Xml: ... case Db: ... }

// Iyi (ILog implement et, Logger degismesin)
interface ILog { void kayitAt(String v); }
class Logger { ILog log; void kayit(String v) { log.kayitAt(v); } }
```

**L — Liskov Substitution:** Alt sinif, ust sinifin yerine sorunsuz kullanilabilmeli.
```java
Calisan c = new GenelMudur(); // GenelMudur her yerde Calisan gibi davranmali
```

**I — Interface Segregation:** Buyuk interface yerine kucuk, odakli interface'ler.
```java
// Kotu: interface IHerSey { void oku(); void yaz(); void dinle(); }
// Iyi:
interface IOku { void oku(); }
interface IYaz { void yaz(); }
interface IDinle { void dinle(); }
```

**D — Dependency Inversion:** Ust seviye moduller, alt seviye modullere degil soyutlamalara bagimli olmali.
```java
// Kotu: Logger dogrudan DbLog'a bagimli
// Iyi: Logger ILog interface'ine bagimli — herhangi bir implementasyon enjekte edilebilir
```

---

### 3. Abstract class ile interface arasindaki farklar

| Ozellik | Abstract Class | Interface |
|---------|---------------|-----------|
| Method | Abstract + concrete olabilir | Java 8 oncesi sadece abstract, sonra default/static |
| Field | Instance variable | Sadece `public static final` |
| Constructor | Var | Yok |
| Coklu miras | Tek sinif extend | Birden fazla implement |
| Erisim | Her modifier | Varsayilan public |
| Ne zaman | "Is-A" + ortak davranis paylasimi | "Can-Do" yetenek tanimlama |

```java
// Abstract class: ortak davranis + zorunlu implementasyon
abstract class Hayvan {
    void yemekYe() { System.out.println("Yiyor"); } // concrete
    abstract void hareketEt(); // alt sinif yazmali
}

// Interface: yetenek kontrati
interface IOku { void oku(String adi); }
interface IYaz { void yaz(String adi); }
class Anne extends Insan implements IOku, IYaz { ... } // coklu implement
```

---

### 4. == vs .equals() farki, hashCode kontrati

**==** referans karsilastirir (ayni obje mi?), **.equals()** icerik karsilastirir (ayni deger mi?).

```java
String a = new String("test");
String b = new String("test");
a == b       // false — farkli objeler
a.equals(b)  // true — ayni icerik

String c = "test";
String d = "test";
c == d       // true — String Pool'dan ayni referans
```

**hashCode kontrati:**
- `equals()` true donerse `hashCode()` ayni olmali
- `hashCode()` ayni olsa bile `equals()` false olabilir (collision)
- HashMap/HashSet dogru calismasi icin ikisi birlikte override edilmeli

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    return Objects.equals(id, ((User) o).id);
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
```

---

### 5. String neden immutable? String Pool nedir?

**Immutable:** Bir String olusturulduktan sonra degeri degistirilemez. Her degisiklik yeni obje olusturur.

```java
String s = "Merhaba";
s = s + " Dunya"; // "Merhaba" degismez, yeni "Merhaba Dunya" objesi olusur
```

**Neden immutable:**
- **Guvenlik:** Parametre olarak gecilen String degistirilemez (URL, dosya yolu, DB connection)
- **Thread safety:** Immutable objeler dogal olarak thread-safe
- **String Pool:** Ayni degere sahip String'ler bellekte tek kopya olarak tutulabilir
- **hashCode cache:** hashCode bir kez hesaplanir, HashMap'te performans artar

**String Pool:** JVM Heap icinde ozel bir alan. String literal'leri burada tutulur:
```java
String a = "test";  // Pool'da olusturulur
String b = "test";  // Pool'dan ayni referans verilir (yeni obje yok)
String c = new String("test"); // Pool disinda Heap'te yeni obje
```

**StringBuilder vs StringBuffer:**
- `StringBuilder` — thread-safe degil, hizli (tek thread)
- `StringBuffer` — synchronized, thread-safe, yavas (coklu thread)

---

### 6. Collections — ArrayList vs LinkedList, HashMap ic yapisi

**ArrayList vs LinkedList:**

| Islem | ArrayList | LinkedList |
|-------|-----------|------------|
| get(index) | O(1) | O(n) |
| add(sona) | O(1) amortized | O(1) |
| add(ortaya) | O(n) — kaydirma | O(1) — pointer |
| Bellek | Az (dizi) | Fazla (node + pointer) |
| Kullanim | Okuma agirlikli | Ekleme/silme agirlikli |

**HashMap ic yapisi:**
1. `put(key, value)` cagirilir
2. `key.hashCode()` hesaplanir
3. hashCode'a gore bucket (dizi indexi) belirlenir: `index = hash & (n-1)`
4. Bucket bossa dogrudan eklenir
5. Doluysa (collision): Java 8 oncesi LinkedList, 8+ ise 8 elemandan sonra **Red-Black Tree**'ye donusur
6. `get(key)` → ayni hash → bucket → equals ile dogru key bulunur

```java
Map<String, Integer> map = new HashMap<>();
map.put("ali", 25);     // "ali".hashCode() → bucket 3'e git → ekle
map.get("ali");          // "ali".hashCode() → bucket 3 → equals ile bul → 25
```

**HashSet:** Icinde HashMap tutar. Deger olarak sabit bir dummy obje kullanir. Eklenen eleman key olarak saklanir — bu yuzden tekrar kabul etmez.

---

### 7. Generics — type erasure, bounded types, wildcard

**Type Erasure:** Derleme sirasinda Generic tip bilgisi silinir. Runtime'da `List<String>` ve `List<Integer>` ayni `List` olur.
```java
// Derleme oncesi:
List<String> list = new ArrayList<>();
// Derleme sonrasi:
List list = new ArrayList(); // tip bilgisi silindi
```

**Bounded Types:** Tipi sinirlandirma.
```java
<T extends Number>        // T sadece Number veya alt siniflari olabilir
<T extends Comparable<T>> // T Comparable implement etmeli
```

**Wildcard:**
```java
List<?>              // herhangi bir tip (okuma)
List<? extends Number> // Number veya alt tipler (okuma — PECS Producer)
List<? super Integer>  // Integer veya ust tipler (yazma — PECS Consumer)
```

**PECS kurali:** Producer Extends, Consumer Super.

---

### 8. Exception handling — checked vs unchecked

**Checked Exception:** Derleme zamaninda kontrol edilir. `try-catch` veya `throws` zorunlu.
```java
// IOException, SQLException, FileNotFoundException
try {
    FileReader f = new FileReader("dosya.txt");
} catch (FileNotFoundException e) {
    // zorunlu handle
}
```

**Unchecked Exception (RuntimeException):** Derleme zamaninda kontrol edilmez.
```java
// NullPointerException, ArrayIndexOutOfBoundsException, IllegalArgumentException
String s = null;
s.length(); // NullPointerException — compile'da hata vermez
```

**try-with-resources (Java 7+):** AutoCloseable kaynaklar otomatik kapatilir.
```java
try (Connection conn = getConnection();
     PreparedStatement ps = conn.prepareStatement(sql)) {
    // kullan
} // otomatik close() — finally gerek yok
```

| Ozellik | Checked | Unchecked |
|---------|---------|-----------|
| Extends | Exception | RuntimeException |
| Derleme kontrolu | Evet | Hayir |
| Handle zorunlu mu | Evet | Hayir |
| Ornek | IOException | NullPointerException |

---

### 9. Java 8+ ozellikleri

**Lambda Expression:** Fonksiyonel interface'lerin kisa gosterimi.
```java
// Oncesi
Runnable r = new Runnable() {
    @Override public void run() { System.out.println("Merhaba"); }
};
// Sonrasi
Runnable r = () -> System.out.println("Merhaba");
```

**Stream API:** Koleksiyonlar uzerinde fonksiyonel islemler.
```java
List<String> isimler = Arrays.asList("Ali", "Veli", "Ayse", "Ali");
isimler.stream()
    .filter(s -> s.length() > 3)    // filtrele
    .distinct()                      // tekrarsiz
    .map(String::toUpperCase)        // donustur
    .sorted()                        // sirala
    .forEach(System.out::println);   // yazdir
```

**Optional:** Null-safe sarmalayici.
```java
Optional<User> user = repository.findById(id);
user.orElseThrow(() -> new RuntimeException("Bulunamadi"));
user.ifPresent(u -> System.out.println(u.getName()));
user.map(User::getName).orElse("Bilinmiyor");
```

**Functional Interface:** Tek abstract metodu olan interface. `@FunctionalInterface` ile isaretlenir.
```java
@FunctionalInterface
interface Hesapla { int uygula(int a, int b); }

Hesapla topla = (a, b) -> a + b;
Hesapla carp = (a, b) -> a * b;
topla.uygula(3, 5); // 8
```

Hazir functional interface'ler: `Predicate<T>`, `Function<T,R>`, `Consumer<T>`, `Supplier<T>`.

---

### 10. Concurrency temelleri

**Thread olusturma:**
```java
// 1. Runnable (onerilen)
Runnable task = () -> System.out.println("Thread: " + Thread.currentThread().getName());
new Thread(task).start();

// 2. Thread extend
class MyThread extends Thread {
    public void run() { System.out.println("Calisiyor"); }
}
```

**synchronized:** Ayni anda tek thread erisimi.
```java
public synchronized void increment() { count++; }
// veya
synchronized(this) { count++; }
```

**volatile:** Degiskenin her zaman ana bellekten okunmasini saglar. Cache'lenmez.
```java
private volatile boolean running = true;
```

**ExecutorService:** Thread havuzu yonetimi.
```java
ExecutorService executor = Executors.newFixedThreadPool(4);
executor.submit(() -> islemYap());
executor.shutdown();
```

**Thread vs Runnable:**
- `Thread` extend → baska sinifi extend edemezsin (Java tek miras)
- `Runnable` implement → esneklik, tercih edilen yol

---

## SPRING BOOT (11-17)

---

### 11. IoC / DI nedir, Spring nasil uygular?

**IoC (Inversion of Control):** Nesne olusturma ve yaşam dongusu kontrolunun gelistiriciden framework'e devredilmesi.

**DI (Dependency Injection):** IoC'nin uygulama yontemi. Bagimliliklarin disaridan enjekte edilmesi.

```java
// DI olmadan — siki bagimlilik
class OrderService {
    private PaymentService payment = new PaymentService(); // kendisi olusturuyor
}

// DI ile — gevsek bagimlilik
class OrderService {
    private final PaymentService payment;
    public OrderService(PaymentService payment) { // disaridan veriliyor
        this.payment = payment;
    }
}
```

**Spring nasil uygular:**
1. `@Component`, `@Service`, `@Repository` ile siniflar bean olarak isaretlenir
2. Spring `ApplicationContext` (IoC Container) baslatildiginda bu sinifları tarar
3. Bean'leri olusturur, bagimliklarini cozer ve inject eder
4. Yasam dongusunu yonetir (creation → initialization → use → destruction)

---

### 12. @Component vs @Service vs @Repository vs @Controller

Hepsi `@Component`'in ozellestirilmis halidir. Teknik olarak ayni seyi yaparlar (bean kaydı) ama **semantik anlam** tasirlar:

| Annotation | Katman | Anlam | Ekstra Ozellik |
|-----------|--------|-------|----------------|
| `@Component` | Genel | Genel amacli bean | — |
| `@Controller` | Web | HTTP endpoint | View resolver, `@RequestMapping` |
| `@RestController` | Web | REST API | `@Controller` + `@ResponseBody` |
| `@Service` | Business | Is mantigi | Semantik, ekstra davranis yok |
| `@Repository` | Data | Veri erisimi | DataAccessException cevirisi |

`@Repository`'nin ekstra ozelligi: JDBC/JPA exception'larini Spring'in `DataAccessException` hiyerarsisine cevirir.

---

### 13. Constructor injection vs field injection

```java
// Constructor injection (onerilen)
@Service
public class OrderService {
    private final PaymentService payment;
    public OrderService(PaymentService payment) {
        this.payment = payment;
    }
}

// Field injection (onerilmez)
@Service
public class OrderService {
    @Autowired
    private PaymentService payment;
}
```

| Ozellik | Constructor | Field |
|---------|------------|-------|
| Immutability | `final` olabilir | Olamaz |
| Null guvenlik | Derleme zamani | Runtime NPE |
| Test | `new OrderService(mockPayment)` | Reflection gerekir |
| Bagimlilık gorunurlugu | Acik | Gizli |
| Dongusel bagimlilik | Hemen hata verir | Gec farkedilir |
| Spring onerisi | Evet | Hayir |

**Neden constructor:** Nesne olusturuldugunda tum bagimliliklarin mevcut oldugu garanti edilir. Test'te mock kolayca gecirilir.

---

### 14. Bean lifecycle ve scope

**Lifecycle:**
```
Bean tanimı bulunur → Constructor → Dependency Injection
→ @PostConstruct → Bean hazir (kullanilir)
→ @PreDestroy → Container kapanir → Bean yok edilir
```

**Scope:**

| Scope | Anlam | Kullanim |
|-------|-------|---------|
| `singleton` (default) | Container'da tek instance | Stateless servisler |
| `prototype` | Her istendiginde yeni instance | Stateful objeler |
| `request` | Her HTTP istegi icin yeni | Web uygulamalari |
| `session` | Her HTTP session icin yeni | Kullanici bazli |

```java
@Service
@Scope("prototype")
public class ReportGenerator { ... } // Her inject edildiginde yeni instance
```

---

### 15. Spring Security — JWT authentication akisi

```
1. Client → POST /login {username, password}
2. AuthController → AuthenticationManager.authenticate()
3. UserDetailsService → DB'den kullanici bul, sifre kontrol (BCrypt)
4. Basarili → TokenManager.generateToken(username) → JWT olustur
5. Client ← 200 OK + JWT token

6. Client → GET /api/data + Header: "Authorization: Bearer <token>"
7. JwtTokenFilter (OncePerRequestFilter):
   a. Header'dan token cikar (substring(7))
   b. TokenManager.tokenValidate(token) — imza + sure kontrol
   c. SecurityContextHolder'a authentication set et
8. Controller → authenticated kullanici ile islem yap
```

Projede (`jwtornek`):
- `TokenManager` — HS256 ile token uret/dogrula
- `JwtTokenFilter` — her istekte token kontrol
- `WebSecurityConfiguration` — STATELESS session, filter chain

---

### 16. @Transactional nasil calisir?

Spring AOP ile proxy olusturur. Metod cagirildiginda:
1. Transaction baslatilir (`BEGIN`)
2. Metod calisir
3. Basarili → `COMMIT`
4. Exception → `ROLLBACK`

```java
@Service
public class OrderService {
    @Transactional
    public void placeOrder(Order order) {
        orderRepo.save(order);         // 1. kaydet
        paymentService.charge(order);   // 2. odeme al
        stockService.reduce(order);     // 3. stok dusur
        // Herhangi birinde hata → hepsi geri alinir
    }
}
```

**Propagation turleri:**

| Tur | Anlam |
|-----|-------|
| `REQUIRED` (default) | Varsa mevcut TX kullan, yoksa yeni ac |
| `REQUIRES_NEW` | Her zaman yeni TX ac (mevcut askıya alinir) |
| `MANDATORY` | TX olmali, yoksa hata |
| `NEVER` | TX olmamali, varsa hata |
| `SUPPORTS` | TX varsa kullan, yoksa TX'siz calis |

**Dikkat:** Ayni sinif icinden `@Transactional` metod cagirirsan proxy bypass edilir — calismaz! Baska bean uzerinden cagirmak gerekir.

---

### 17. @ControllerAdvice ve @ExceptionHandler

Global exception handling:
```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(400, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex) {
        return ResponseEntity.status(500).body(new ErrorResponse(500, "Internal error"));
    }
}
```

- `@ControllerAdvice` — tum controller'lar icin gecerli
- `@ExceptionHandler` — belirli exception tipini yakalar
- En spesifik handler once eslesir

---

## MICROSERVICES (18-24)

---

### 18. Monolith vs Microservices

| Ozellik | Monolith | Microservices |
|---------|----------|---------------|
| Deploy | Tek birim | Bagimsiz servisler |
| Olcekleme | Tumu birlikte | Sadece gereken servis |
| Teknoloji | Tek stack | Her servis farkli olabilir |
| Hata etkisi | Tum uygulama coker | Sadece bir servis |
| Gelistirme | Basit baslar, buyudukce zorlar | Basindan karmasik, buyudukce kolaylar |
| Deploy hizi | Yavas (tum build) | Hizli (sadece degisen servis) |
| Veri | Tek DB | Her servis kendi DB'si |
| Iletisim | Method call | Network (REST, MQ) |

**Ne zaman Microservices:** Buyuk takim, yuksek trafik, bagimsiz olcekleme ihtiyaci, farkli release dongusu.

**Ne zaman Monolith:** Kucuk takim, baslangiç asamasi, hizli MVP.

---

### 19. API Gateway nedir?

Tum client isteklerinin tek giris noktasi. Istemci arkadaki servisleri bilmez, Gateway yonlendirir.

**Gorevleri:**
- **Routing:** `/api/product/**` → product-service
- **Load Balancing:** Birden fazla instance arasinda dagitim (`lb://`)
- **Authentication:** Token dogrulama, yetkisiz istekleri reddetme
- **Rate Limiting:** Asiri istekleri sinirlandirma
- **CORS:** Cross-origin ayarlari
- **Request/Response transformation**

Projede:
```properties
spring.cloud.gateway.routes[0].uri=lb://PRODUCT-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/product/**
```

---

### 20. Eureka nasil calisir?

1. **Eureka Server** baslatilir (`@EnableEurekaServer`)
2. Servisler baslatildiginda Eureka'ya **kayit olur** (register)
3. Her 30 saniyede **heartbeat** gonderir ("ben hayattayim")
4. Heartbeat gelmezse Eureka listeden cikarir
5. Baska servis gerektiginde Eureka'ya sorar: "order-service nerede?"
6. Eureka IP/port bilgisini doner
7. Gateway `lb://` ile Eureka'dan adres alir + load balance yapar

```properties
# Server (kendini kayit etmez)
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

# Client (kayit olur)
eureka.client.register-with-eureka=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 21. Config Server neden gerekli?

20 mikroserviste ayni DB URL'ini degistirmek → 20 dosyada degisiklik. Config Server ile tek yerden yonetilir.

**Avantajlar:**
- Tek yerden tum servislerin konfigurasyonu
- Ortam bazli ayirım (dev, staging, prod)
- Runtime'da degisiklik (`@RefreshScope` + `/actuator/refresh`)
- Hassas bilgiler merkezi olarak sifrelenebilir
- Git-backed: konfigurasyon versiyon kontrolunde

---

### 22. Sync vs Async iletisim

**Synchronous (REST/Feign):**
```java
// Feign Client — deklaratif REST
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/product/{id}")
    Product getProduct(@PathVariable Long id);
}
// Cagiran servis cevap gelene kadar BEKLER
```

**Asynchronous (RabbitMQ/Kafka):**
```java
// Producer — mesaj gonder, BEKLEME
rabbitTemplate.convertAndSend(exchange, routingKey, event);

// Consumer — mesaj gelince isle
@RabbitListener(queues = "order-queue")
public void handle(OrderEvent event) { ... }
```

| | Sync | Async |
|--|------|-------|
| Bekleme | Cevap bekler | Beklemez |
| Bagimlilik | Servis cokse hata | Mesaj kuyrukta bekler |
| Kullanim | Anlik cevap gereken | Mail, bildirim, fatura |
| Karmasiklik | Basit | Eventual consistency |

---

### 23. Circuit Breaker (Resilience4j)

Bir servis surekli hata veriyorsa diger servisleri korumak icin "devre kesici" devreye girer.

**3 durum:**
- **CLOSED (Normal):** Istekler gecer
- **OPEN (Acik):** Istekler gonderilmez, hemen fallback doner
- **HALF-OPEN:** Sinirli istek gonderilir, basarili olursa CLOSED'a doner

```java
@CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
public Payment charge(Order order) {
    return paymentClient.charge(order); // baska servise istek
}

public Payment paymentFallback(Order order, Exception ex) {
    return new Payment("PENDING"); // servis cokse bile cevap doner
}
```

---

### 24. Saga Pattern

Dagitik sistemlerde transaction yonetimi. Tek DB transaction'i yerine her servis kendi islemini yapar, hata olursa **compensating transaction** ile geri alinir.

**Choreography (Koreografi):** Servisler event ile haberlesir.
```
OrderService → "OrderCreated" event → RabbitMQ
PaymentService dinler → odeme al → "PaymentCompleted" event
StockService dinler → stok dus → "StockReduced" event
Hata olursa → "PaymentFailed" event → OrderService iptal eder
```

**Orchestration:** Merkezi bir orkestrator adimlari yonetir.
```
SagaOrchestrator:
  1. OrderService.create() → basarili
  2. PaymentService.charge() → basarili
  3. StockService.reduce() → HATA!
  4. PaymentService.refund() → compensate
  5. OrderService.cancel() → compensate
```

---

## VERITABANI & ORM (25-30)

---

### 25. JPA Entity lifecycle

```
        new()           persist()         commit/flush
Transient ────→ Managed ──────────→ DB'ye yazilir
                  ↑                     |
                  | merge()         detach()/close()
                  |                     ↓
                  └──────────── Detached
                                    |
                  remove()          |
Managed ──────────→ Removed → DB'den silinir
```

- **Transient:** `new User()` — JPA bilmiyor
- **Managed:** `em.persist(user)` — JPA takip ediyor, degisiklikler otomatik sync
- **Detached:** Session kapandi — degisiklikler takip edilmiyor, `merge()` ile geri alinir
- **Removed:** `em.remove(user)` — flush'ta DB'den silinecek

---

### 26. Lazy vs Eager loading, N+1 problem

**Lazy:** Iliskili veri ancak erisildinde yuklenir.
**Eager:** Iliskili veri ana sorgu ile birlikte yuklenir.

```java
@OneToMany(fetch = FetchType.LAZY)   // Kategorinin urunleri — erisildikce yukle
private List<Product> products;

@ManyToOne(fetch = FetchType.EAGER)  // Urunun kategorisi — hemen yukle
private Category category;
```

**N+1 Problem:**
```java
List<Category> cats = categoryRepo.findAll(); // 1 sorgu: SELECT * FROM category
for (Category c : cats) {
    c.getProducts(); // Her kategori icin ayri sorgu! N sorgu daha
}
// Toplam: 1 + N sorgu
```

**Cozum:** `JOIN FETCH` veya `@EntityGraph`
```java
@Query("SELECT c FROM Category c JOIN FETCH c.products")
List<Category> findAllWithProducts(); // Tek sorgu!
```

---

### 27. JPA iliskiler

```java
// Bir kullanicinin birden fazla siparisi
@Entity
public class User {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
}

@Entity
public class Order {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}

// Bir sipariste birden fazla urun (Many-to-Many)
@ManyToMany
@JoinTable(name = "order_product",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
private Set<Product> products;
```

`mappedBy` = iliskinin sahibi degil, diger taraf yonetiyor.

---

### 28. Index nedir?

Veritabaninda arama hizlandirmak icin kullanilan veri yapisi (B-Tree genelde).

**Ne zaman kullanilir:**
- WHERE kosulunda sik kullanilan sutunlar
- JOIN sutunlari (foreign key)
- ORDER BY / GROUP BY sutunlari

**Ne zaman kullanilmaz:**
- Cok az satiri olan tablolar
- Sik guncellenen sutunlar (INSERT/UPDATE yavaşlar)
- Cardinality dusuk sutunlar (orn. boolean — sadece true/false)

```sql
CREATE INDEX idx_product_name ON product(name);
CREATE INDEX idx_order_user_date ON orders(user_id, created_at); -- composite index
```

---

### 29. ACID prensipleri

| Prensip | Anlam | Ornek |
|---------|-------|-------|
| **Atomicity** | Ya hepsi ya hic | Para transferi: A'dan dusur + B'ye ekle — ikisi birden veya hicbiri |
| **Consistency** | Islem oncesi/sonrasi veri tutarli | Toplam bakiye islem oncesi = sonrasi |
| **Isolation** | Esanli islemler birbirini etkilemez | Iki kisi ayni urunun son stogunu almaya calissa biri basarir |
| **Durability** | Commit edilen veri kaybolmaz | Sunucu cokse bile commitlenen veri diskte kalir |

---

### 30. SQL — JOIN, GROUP BY

```sql
-- INNER JOIN: Her iki tabloda eslesme olan satirlar
SELECT u.name, o.total FROM users u
INNER JOIN orders o ON u.id = o.user_id;

-- LEFT JOIN: Sol tablodaki tum satirlar (eslesme olmasa null)
SELECT u.name, o.total FROM users u
LEFT JOIN orders o ON u.id = o.user_id;

-- RIGHT JOIN: Sag tablodaki tum satirlar
-- FULL JOIN: Her iki tablodaki tum satirlar

-- GROUP BY + HAVING
SELECT u.name, COUNT(o.id) as order_count, SUM(o.total) as total_spent
FROM users u
JOIN orders o ON u.id = o.user_id
GROUP BY u.name
HAVING SUM(o.total) > 1000
ORDER BY total_spent DESC;

-- Subquery
SELECT * FROM products
WHERE price > (SELECT AVG(price) FROM products);
```

---

## MİMARİ & TASARIM (31-35)

---

### 31. Design Patterns

**Singleton:** Tek instance garanti eder.
```java
public class DbConnection {
    private static final DbConnection INSTANCE = new DbConnection();
    private DbConnection() {} // private constructor
    public static DbConnection getInstance() { return INSTANCE; }
}
```

**Factory:** Nesne olusturma mantigi merkezilestir.
```java
public static ILog create(String type) {
    return switch(type) {
        case "db" -> new DbLog();
        case "file" -> new FileLog();
        default -> throw new IllegalArgumentException();
    };
}
```

**Observer:** Durum degisikligini abonelere bildir (RabbitMQ producer/consumer gibi).

**Strategy:** Algoritmayı runtime'da degistir (ILog interface'i — farkli loglama stratejileri).

**Builder:** Karmasik nesne adim adim olustur.
```java
User user = User.builder()
    .name("Samed")
    .email("samed@test.com")
    .role(Role.ADMIN)
    .build();
```

---

### 32. REST API best practices

- **Kaynak odakli URL:** `/api/products/123` (fiil degil — getProduct degil)
- **HTTP metod:** GET=oku, POST=olustur, PUT=guncelle, DELETE=sil
- **Status kodlari:** 200 OK, 201 Created, 204 No Content, 400 Bad Request, 404 Not Found
- **Idempotency:** GET, PUT, DELETE idempotent (tekrar cagrilsa ayni sonuc). POST degil.
- **Versiyonlama:** `/api/v1/products`
- **Pagination:** `?page=0&size=20`
- **HATEOAS:** Response'ta iliskili linkleri don (opsiyonel)
- **Error format:** RFC 7807 Problem+JSON

---

### 33. Caching — Redis

```java
@Configuration
@EnableCaching
public class RedisConfig { ... }

@Service
public class ProductService {
    @Cacheable(value = "products", key = "#id")
    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(); // sadece ilk cagirida DB'ye gider
    }

    @CacheEvict(value = "products", key = "#id")
    public void update(Long id, Product p) {
        repo.save(p); // guncelleyince cache temizle
    }
}
```

**Cache invalidation stratejileri:**
- **TTL (Time-To-Live):** Belirli sure sonra otomatik sil
- **Write-through:** Yazma sirasinda cache guncelle
- **Write-behind:** Async olarak cache guncelle
- **Cache-aside:** Okumada cache yoksa DB'den al, cache'e yaz

---

### 34. RabbitMQ vs Kafka

| Ozellik | RabbitMQ | Kafka |
|---------|----------|-------|
| Model | Message Queue (FIFO) | Event Streaming (log) |
| Mesaj tutma | Consumer aldiktan sonra silinir | Belirli sure saklanir (replay) |
| Sirali islem | Queue bazli siralama | Partition bazli siralama |
| Performans | Dusuk-orta trafik | Cok yuksek trafik (milyon/sn) |
| Kullanim | Task queue, bildirim, mail | Log aggregation, event sourcing, analytics |
| Routing | Exchange + routing key (esnek) | Topic + partition |
| Karmasiklik | Basit kurulum | Zookeeper/KRaft gerekir |

**Ne zaman RabbitMQ:** Bildirim, mail, siparis isleme, task queue.
**Ne zaman Kafka:** Log toplama, event sourcing, real-time analytics, cok yuksek throughput.

---

### 35. Docker temelleri

```dockerfile
# Multi-stage build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Temel kavramlar:**
- **Image:** Uygulamanin paketlenmis hali (read-only sablón)
- **Container:** Image'in calisir hali (instance)
- **Dockerfile:** Image olusturma tarifi
- **docker-compose.yml:** Birden fazla container'i birlikte yonetme

```yaml
# docker-compose.yml
services:
  product-service:
    build: ./services/product-service
    ports: ["8082:8082"]
    depends_on:
      postgres: { condition: service_healthy }
  postgres:
    image: postgres:16
    environment:
      POSTGRES_DB: n11db
```
