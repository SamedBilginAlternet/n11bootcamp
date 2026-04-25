# n11 Bootcamp - 100 Soru Cevap Anahtari

---

## JAVA OOP TEMELLERİ (1-20)

---

**1. Nesneye Yonelik Programlamanin (OOP) 4 temel ozelligi nedir?**

1. **Soyutlama (Abstraction):** Nesneyi, ozellikleri ve eylemleri olan bir veri tipi olarak genellestirmektir. Gercek dunyadaki bir nesnenin (ornegin bina) bilgisayarda temsili icin ayirdedici ozellikleri (yukseklik, kat sayisi) ve metotlari (asansor calistir) belirlenir.

2. **Paketleme (Encapsulation):** Sinifi olusturan metot ve ozelliklerin gerceklestirim biciminin disaridan gizlenmesidir. `NESNE = VERİ + METOTLAR` formuluyle ifade edilir. Ic detaylar degisse bile dis arayuz ayni kaldigi surece kullanan kodlar etkilenmez.

3. **Cok Bicimliligi (Polymorphism):** Farkli nesnelerin ayni mesaja farkli sekillerde cevap verebilmesidir. Ornegin DAIRE, KARE, UCGEN siniflarinin hepsinde `ciz()` metodu vardir ama her biri farkli sekil cizer.

4. **Miras Alma (Inheritance):** Alt sinifin, temel sinifin ozelliklerini ve metotlarini devralmasidir. Ornegin `Cocuk` sinifi `Insan` sinifinin ad, soyad, yas gibi ozelliklerini otomatik olarak alir.

---

**2. Abstraction nedir?**

Soyutlama, bir nesneyi bazı karakteristikleri olan ve bazi eylemleri gerceklestirebilen bir veri tipi olarak genellestirmektir. Sinif (Class) yapisi ile gerceklestirilir.

Projede `Insan` sinifi soyutlama ornegi:
```java
public class Insan {
    public String ad;
    public String soyad;
    public int yas;
    public double maas;
    public boolean cinsiyet;
}
```
Gercek dunyadaki bir insanin sonsuz ozelligi varken, yazilimda sadece ihtiyac duydugumuz ozellikleri (ad, soyad, yas, maas, cinsiyet) modelliyoruz. Bu secim soyutlamadir.

---

**3. Encapsulation nedir?**

`NESNE = VERİ + METOTLAR` formuluyle ifade edilir. Veri (ozellikler) ve veri uzerinde islem yapan kod (metotlar) bir arada bulunur ve nesneyi olusturur. Nesneyi tanimlayan sinifin ic detaylari disindan gorunmez.

Ornegi: Bir sinifin ic implementasyonunu degistirseniz (farkli bir algoritma kullansaniz), dis arayuzu ayni kaldigi surece o sinifi kullanan kodlarda degisiklik gerekmez.

Pratikte access modifier'lar ile saglanir:
- `private` alanlar disaridan erisilemez
- `public` getter/setter metotlari kontrollü erisim saglar

---

**4. Polymorphism nedir?**

Farkli nesnelerin ayni mesaja farkli sekillerde cevap verebilme yetenegidir.

Ornek: SEKIL super siniftan turetilen DAIRE, KARE, UCGEN alt siniflarinin her birinde `ciz()` metodu vardir. Ayni isimli metod farkli davranir:
- DAIRE.ciz() → daire cizer
- KARE.ciz() → kare cizer
- UCGEN.ciz() → ucgen cizer

Projede abstracts/Test.java'daki polimorfik dizi ornegi:
```java
Calisan[] calisanlar = new Calisan[4];
calisanlar[0] = new GenelMudur();  // 300000
calisanlar[1] = new Mudur();       // 200000
calisanlar[2] = new Programci();   // 30000
calisanlar[3] = new Stajyer();     // 150000
```
Hepsi `Calisan` tipinde tutulur ama `maasinizNedir()` cagirildiginda her biri kendi degerini doner.

---

**5. Inheritance nedir?**

Alt sinifin (subclass) ust sinifin (superclass) ozelliklerini ve metotlarini devralmasidir.

JavaProje'deki hiyerarsi:
```
Insan (ad, soyad, yas, maas, cinsiyet)
  └── Cocuk (Insan'in tum ozelliklerini miras alir)
        ├── AkilliCocuk (Cocuk'un + Insan'in tum ozelliklerini miras alir)
        └── UsluCocuk (Cocuk'un + Insan'in tum ozelliklerini miras alir)
```

`AkilliCocuk` sinifi bos olmasina ragmen `ad`, `soyad`, `yas`, `maas`, `cinsiyet` alanlarinin hepsine sahiptir cunku bunlari `Insan -> Cocuk` zincirinden miras alir.

---

**6. Abstract class ile interface arasindaki farklar nelerdir?**

| Ozellik | Abstract Class | Interface |
|---------|---------------|-----------|
| Method body | Hem abstract hem concrete method olabilir | Java 8 oncesi sadece abstract (sonra default method geldi) |
| Field | Instance variable olabilir | Sadece `public static final` (sabit) |
| Constructor | Olabilir | Olamaz |
| Coklu miras | Tek abstract class extend edilir | Birden fazla interface implement edilir |
| Erisim belirleyici | Herhangi biri | Methodlar varsayilan public |

Projede:
- `Hayvan` abstract sinifi: `void yemekYe()` concrete metodu + `void hareketEt()` abstract metodu icerir
- `IDinle`, `IOku`, `IYaz` interfaceleri: Tek bir abstract method icerir (orn. `void dinle(String adi)`)
- `Anne` sinifi `Insan`'i extend ederken ayni anda `IOku`, `IYaz`, `IDinle` interfacelerini implement eder — bu interface ile mumkun, abstract class ile yapılamazdi

---

**7. Composition mu Inheritance mi?**

**Composition (Has-A iliskisi).** Kafa sinifi Goz, Burun, Kulak nesnelerini icerir:
```java
public class Kafa {
    Goz goz;
    Kulak kulak;
    Burun burun;
}
```
Bu "Kafa'nin Goz'u vardir" (Has-A) iliskisidir, "Kafa bir Goz'dur" (Is-A) degil. Inheritance'ta alt sinif ust sinifin bir turu olurken, Composition'da sinif baska sinifin nesnesini barindirır.

Benzer sekilde `YeniInsan` sinifi `Kafa` nesnesini icerir: `YeniInsan has-a Kafa has-a (Goz, Kulak, Burun)`.

---

**8. @Override annotation'i ne ise yarar?**

Bir metodun ust siniftan veya interface'ten override edildigini derleyiciye bildirir. Eger ust sinifta boyle bir metot yoksa derleme hatasi verir — bu yazim hatalarini yakalar.

JavaProje/override paketinde:
```java
public class Parent {
    public void yaz() { System.out.println("Parent"); }
}

public class Child extends Parent {
    @Override
    public void yaz() { System.out.println("Child"); }
}
```
`Child` sinifi `Parent`'in `yaz()` metodunu override eder. `super()` ile de parent constructor cagrilir.

---

**9. Interface icinde method body yazilabilir mi?**

**Java 8** ile birlikte `default` method destegi geldi. Interface icinde govdeli metot yazilabilir:
```java
public interface Selamla {
    default void merhaba() {
        System.out.println("Merhaba!");
    }
}
```
Ayrica `static` methodlar da Java 8 ile eklendi. Java 9 ile `private` methodlar da interface icinde tanimlanabildı.

---

**10. ICalisan interface'inde polymorphism nasil gerceklesiyor?**

`ICalisan` interface'ini implement eden her sinif `maasinizNedir()` metodunu farkli dondurur:
- `Programci` → 30000
- `Stajyer` → 150000
- `Mudur` → 200000
- `GenelMudur` → 300000

Test sinifinda polimorfik dizi:
```java
Calisan[] calisanlar = new Calisan[4];
for (Calisan c : calisanlar) {
    System.out.println(c.maasinizNedir()); // Her biri kendi degerini doner
}
```
Referans tipi `Calisan` olmasina ragmen, calisma zamaninda gercek nesne tipinin metodu cagrilir — bu **runtime polymorphism**'dir.

---

**11. Abstract sinifta abstract ve concrete method birlikte bulunabilir mi?**

**Evet.** `Hayvan` abstract sinifinda:
```java
public abstract class Hayvan {
    public void yemekYe() { /* concrete - govdesi var */ }
    public abstract void hareketEt(); // abstract - govdesi yok
}
```
`Kedi` sinifi sadece abstract olan `hareketEt()`'i override etmek zorundadir, `yemekYe()` oldugu gibi miras alinir.

---

**12. `super` anahtar kelimesinin kullanim alanlari**

1. **Ust sinifin constructor'ini cagirmak:** `super()` veya `super(parametre)`
2. **Ust sinifin metodunu cagirmak:** `super.metodAdi()`
3. **Ust sinifin alanina erismek:** `super.alanAdi`

Child sinifinda ornek:
```java
public class Child extends Parent {
    public Child() {
        super(); // Parent sinifinin constructor'i cagrilir
    }
}
```

---

**13. Static method ile instance method arasindaki fark**

| Ozellik | Static Method | Instance Method |
|---------|--------------|-----------------|
| Cagirma | `Matematik.topla(2,3)` sinif adi ile | `obj.metod()` nesne ile |
| `this` erisim | Yok | Var |
| Instance field erisim | Erisemez | Erisir |
| Bellek | Sinif yuklenmesiyle bir kez | Her nesne icin ayri |

`Matematik` sinifindaki `static` topla metotlari nesne olusturmadan sinif adi ile cagirilir.

---

**14. Access modifier'lar**

| Modifier | Ayni Sinif | Ayni Paket | Alt Sinif | Her Yer |
|----------|-----------|-----------|-----------|---------|
| `public` | Evet | Evet | Evet | Evet |
| `protected` | Evet | Evet | Evet | Hayir |
| default (bos) | Evet | Evet | Hayir | Hayir |
| `private` | Evet | Hayir | Hayir | Hayir |

Encapsulation icin en iyi pratik: alanlari `private` yap, `public` getter/setter ile kontrollü erisim sagla.

---

**15. Composition mu Aggregation mu?**

`Yoneticeler` sinifinda `Mudur` listesi tutuluyor. Bu **aggregation**'dir cunku:
- **Composition:** Parca butun olmadan var olamaz (Kafa silinirse Goz de silinir)
- **Aggregation:** Parca butun olmadan da var olabilir (Yoneticeler silinse Mudur nesneleri baska yerde yasayabilir)

Mudur nesneleri bagimsiz olarak olusturulur ve listeye eklenir, Yoneticeler sinifina bagimli degildir.

---

**16. Constructor overloading**

Ayni sinifta farkli parametre listeleriyle birden fazla constructor tanimlama. `Insan` sinifinda:
```java
public Insan() { }                          // parametresiz
public Insan(String ad, String soyad) { }    // 2 parametreli
public Insan(String ad, String soyad, int yas, double maas, boolean cinsiyet) { } // 5 parametreli
```
Farkli durumlarda farkli constructor'lar kullanilabilir — bu esneklik saglar.

---

**17. YeniInsan sinifi ne eklemistir?**

`YeniInsan` sinifi `Insan`'i extend ederken **Composition** ile `Kafa` nesnesi eklemistir. Bu OOP'nin **Extensibility (Genisletilebilirlik)** prensibini ornekler — mevcut sinifa yeni ozellik ve metotlar ekleyerek artan islevsellik saglanir.

---

**18. `this` anahtar kelimesinin 3 kullanim alani**

1. **Sinifin kendi alanina erisim:** `this.ad = ad;` (parametre ile alan adini ayirt etmek)
2. **Ayni sinifin baska constructor'ini cagirma:** `this(parametre);`
3. **Mevcut nesneyi parametre olarak gecme:** `metod(this);`

---

**19. Diamond problem olusabilir mi?**

**Hayir.** Java'da class seviyesinde multiple inheritance yoktur. `UsluCocuk` ve `AkilliCocuk` her ikisi de `Cocuk`'u extend eder ama birbirinden bagimsizdir. Diamond problem ancak bir sinif iki farkli sinifi extend etmeye calisirsa olusur ki Java buna izin vermez. Diamond problem sadece interface'lerde (Java 8+ default method ile) olusabilir.

---

**20. Multiple inheritance yerine ne kullanilir?**

Java'da **interface** kullanilir. Bir sinif birden fazla interface implement edebilir:
```java
public class Anne extends Insan implements IOku, IYaz, IDinle {
    @Override public void oku(String adi) { }
    @Override public void yaz(String adi) { }
    @Override public void dinle(String adi) { }
}
```
`Anne` sinifi hem `Insan`'dan miras alir hem de 3 farkli interface'i implement eder.

---

## SOLID ve DESIGN PATTERNS (21-30)

---

**21. SOLID prensipleri**

- **S - Single Responsibility:** Her sinifin tek bir sorumlulugu olmalidir. `DbLog` sadece veritabanina log yazar, baska is yapmaz.
- **O - Open/Closed:** Siniflar genislemeye acik, degisiklige kapali olmalidir. Yeni log tipi eklemek icin `ILog` implement edilir, `Logger` degismez.
- **L - Liskov Substitution:** Alt siniflar, ust sinifin yerine kullanilabilmelidir. `Programci` nesnesini `Calisan` referansiyla tutabiliriz.
- **I - Interface Segregation:** Buyuk interface'ler yerine kucuk, odakli interface'ler tercih edilmelidir. `IDinle`, `IOku`, `IYaz` ayri ayri tanimlanmistir.
- **D - Dependency Inversion:** Ust seviye moduller alt seviye modullere degil, soyutlamalara (interface) bagimli olmalidir. `Logger` sinifi `ILog` interface'ine bagimlidir, concrete sinifa degil.

---

**22. `solid/log/kotu` paketi neden Open/Closed'a uymuyor?**

`Logger` sinifi concrete siniflara dogrudan bagimlidir:
```java
private DbLog dbLog;
private XmlLog xmlLog;
private TextLog textLog;

public void LogKayit(LogType type, String value) {
    switch(type) {
        case Xml: xmlLog.xmlKayit(value); break;
        case Db: dbLog.dbKayit(value); break;
        case Text: textLog.textKayit(value); break;
    }
}
```
Yeni bir log tipi (orn. `FileLog`) eklemek icin: LogType enum'a yeni deger, Logger'a yeni field, switch'e yeni case, constructor'a yeni parametre eklemek gerekir — yani **Logger sinifi degistirilmek zorundadir**.

---

**23. `solid/log/iyi` paketi nasil Open/Closed'a uyuyor?**

`ILog` interface'i tanimlanmistir:
```java
public interface ILog {
    void kayitAt(String value);
}
```
`DbLog`, `TextLog`, `XmlLog` hepsi `ILog`'u implement eder. `Logger` sinifi sadece `ILog`'a bagimlidir:
```java
private ILog iLog;
public Logger(ILog iLog) { this.iLog = iLog; }
public void kayitAt(String value) { iLog.kayitAt(value); }
```
Yeni bir `FileLog` eklemek icin sadece `ILog`'u implement eden yeni sinif yazilir. **Logger sinifi hic degismez.** Open/Closed prensibine uygundur.

---

**24. LogFactory hangi design pattern'i uygular?**

**Factory Pattern.** `LogFactory` nesnelerin olusturulmasini merkezi bir yere tasir ve Reflection kullanarak dinamik nesne olusturur:
```java
public static ILog create(String className) {
    Class c = Class.forName("com.n11.oop.solid.log.iyi." + className);
    return (ILog) c.newInstance();
}
```
Avantajlari:
- Nesne olusturma mantigi istemciden ayrilir
- Yeni tipler eklemek kolay — sinif adi String olarak verilir
- Bagimliliklari azaltir

---

**25. LogType enum + switch-case yaklasiminin dezavantaji**

- Her yeni log tipi icin enum'a yeni deger ve switch'e yeni case eklenmek zorundadir
- Open/Closed prensibini ihlal eder
- Logger sinifi tum concrete siniflari bilmek zorundadir (tight coupling)
- Degisiklik yapilacak yer sayisi arttikca hata riski artar
- Olceklenebilirlik dusuktur

---

**26. Observer Design Pattern**

Bir nesne kumesi arasindaki **one-to-many** iliskiyi tanimlar. Bir nesnenin (Subject/Publisher) durumu degistiginde, tum baglimlilarina (Observer/Subscriber) bildirilir.

- **Publisher (Yayinci/Subject):** Durum bilgisini tutan ve degistiren nesne. Abonelerin referanslarini bir listede saklar.
- **Subscriber (Abone/Observer):** Durum degisikliginden haberdar edilmek isteyen nesneler. Yayincinin olay bildirimlerini dinler.

Gercek hayat ornegi: YouTube kanali (Publisher) video yuklediginde, tum abonelerine (Subscriber) bildirim gider.

---

**27. Loose Coupling nedir?**

Iki obje birbiriyle iliskilidir ama birbiri hakkinda cok az sey bilir. Observer pattern'da:
- Subject, Observer'larin ic yapisini bilmez — sadece bildirim gondermesi gerektigi arayuzu bilir
- Observer, Subject'in implementasyonunu bilmez — sadece durum degisikligi bildirimini alir
- Birinde yapilan degisiklik digerini etkilemez

Onem: Bagimsiz gelistirme, test etme kolayligi, degisiklige dayaniklilik.

---

**28. Observer vs Mediator**

- **Observer:** Nesneler arasinda dinamik, tek yonlu baglanti kurar. Bazi nesneler diger nesnelerin alt nesnesi (subscriber) olarak davranir.
- **Mediator:** Bir dizi bilesenin karsilikli bagimliligini ortadan kaldirir. Bilesenler tek bir araci nesneye bagimli hale gelir.

Ikisi birlikte kullanilabilir: Mediator'un Observer-tabanli uygulamasinda, mediator yayinci, bilesenler ise abone olarak hareket eder.

---

**29. Dinamik abonelik mekanizmasi**

Yayincinin sinifi icerisine abonelik mekanizmasi eklenir:
1. Abone nesnelerin referanslarini saklayan bir liste/array alani
2. Listeye abone eklemeyi saglayan `subscribe()` metodu
3. Listeden abone cikarmayi saglayan `unsubscribe()` metodu
4. Tum abonelere bildirim gonderen `notify()` metodu

Abonelik listesi dinamiktir — aboneler istedikleri zaman abone olur veya abonelikten cikar.

---

**30. 4 pattern karsilastirmasi**

- **Chain of Responsibility:** Istegi potansiyel alicilar zinciri boyunca sirayla iletir, en az biri isleyene kadar devam eder.
- **Command:** Gonderici ve alici arasinda tek yonlu baglanti kurar. Istegi nesne olarak kapsullar.
- **Mediator:** Gonderici ve alici arasindaki dogrudan baglantilari kaldirarak araci nesne uzerinden dolaylı iletisim kurar.
- **Observer:** Alicilarin isteklere dinamik olarak abone olmasini ve abonelikten cikmasini saglar. One-to-many iliski.

---

## GENERICS (31-40)

---

**31. Java Generics nedir?**

Generics, farkli referans veri tiplerini alan, hangi tipi alacagina karar verilebilen ve uzerinde benzer islemler yapilabilen yapilardir. Java 5 ile geldi.

Generics olmadan ClassCastException:
```java
List list = new ArrayList();
list.add("Merhaba");
list.add(123);
String s = (String) list.get(1); // RuntimeException! 123 String degil
```
Generics ile bu hata derleme zamaninda yakalanir.

---

**32. Object ve raw type neden tehlikeli?**

```java
// Java 1.4 - Tehlikeli kullanim
List list = new ArrayList();
list.add("Merhaba Dunya");
list.add(123);
for (int i = 0; i < list.size(); i++) {
    String s = (String) list.get(i); // i=1'de ClassCastException!
}
```
Derleyici hata vermez cunku `Object` tipi her seyi kabul eder. Hata ancak calisma zamaninda ortaya cikar — bu cok daha tehlikelidir.

---

**33. Type Safety nedir?**

Tip guvenli, tip dogrulamasi anlamina gelir. Java gibi type-safe dillerde degisken tanimlarken tipini bildirmek zorunludur. Derleyici derleme sirasinda tum degiskenler icin dogrulama yapar.

Generics type safety saglar:
```java
List<String> list = new ArrayList<>();
list.add("Merhaba");
list.add(123); // DERLEME HATASI! Integer eklenemez
```
Hatalar runtime yerine compile-time'da yakalanir.

---

**34. Derleme hatasi verir mi?**

**Evet, 3. satirda derleme hatasi verir.** `List<String>` sadece String kabul eder. `list.add(123)` ifadesinde `123` bir int/Integer'dir, String degil. Derleyici bunu derleme zamaninda reddeder.

---

**35. Generic sinif, interface ve method ornekleri**

```java
// Generic Interface
public interface Comparable<T> {
    public int compareTo(T object);
}

// Generic Class
public class Printer<T> {
    public void print(T object) {
        System.out.println(object);
    }
}

// Generic Method
public <T> T myGenericMethod(T object) {
    return object;
}
```

---

**36. T, E, K, V ne anlama gelir?**

Java Naming Convention'a goredir:
- **T** — Type (genel tip)
- **E** — Element (koleksiyon elemani, orn. `List<E>`)
- **K** — Key (anahtar, orn. `Map<K,V>`)
- **V** — Value (deger, orn. `Map<K,V>`)
- **N** — Number
- **S, U** — Ikinci, ucuncu tip parametreleri

Zorunlu degildir ama konvansiyon olarak uyulmasi okunabilirligi artirir.

---

**37. Bounded Types nedir?**

Generic yapiyi belirli bir siniftan tureyen tiplerle sinirlandirir.

```java
public class Kare<T extends Number> {
    public double alanHesapla(T uzunluk) {
        return uzunluk.doubleValue() * uzunluk.doubleValue();
    }
}
```
`<T extends Number>` sayesinde T sadece Number alt sinifları (Integer, Double, Long vb.) olabilir. `Kare<String>` derleme hatasi verir.

---

**38. Wildcard turleri**

**Unbounded Wildcard (?):** Tipini bilmedigimiz verileri okumak icin:
```java
public void printList(List<?> list) {
    for (Object obj : list) System.out.println(obj);
}
```

**Upper Bounded (? extends T):** Okuma icin — T ve alt tipleri:
```java
public void read(List<? extends Number> list) { /* Number veya alt tipleri */ }
```

**Lower Bounded (? super T):** Yazma icin — T ve ust tipleri:
```java
public void addNumbers(List<? super Number> list) {
    list.add(1); // Number ve ust tiplerini kabul eden listeye ekleme
}
```

Kural: **PECS** — Producer Extends, Consumer Super.

---

**39. Type Erasure nedir?**

Derleme sirasinda Generic tip bilgilerinin silinmesidir. Derleyici Generic ifadeleri anlar ve dogrular, sonra tip bilgisini siler.

Derleme oncesi:
```java
public static <E> void printArray(E[] array) {
    for (E element : array) System.out.println(element);
}
```

Derleme sonrasi:
```java
public static void printArray(Object[] array) {
    for (Object element : array) System.out.println(element);
}
```
`E` → `Object`'e donusur. Generics sadece derleme zamaninda calisir, runtime'da kaybolur.

---

**40. Neden overload edilemez?**

Type Erasure sonrasi her iki method da ayni imzaya donusur:
```java
// Derleme oncesi:
public void print(List<String> param);
public void print(List<Integer> param);

// Derleme sonrasi (Type Erasure):
public void print(List param);  // Ayni!
public void print(List param);  // Ayni!
```
Derleyici son iki methodun ayni method oldugunu kabul eder ve derleme hatasi verir.

---

## VALUE/REFERENCE TYPE, STACK/HEAP, BOXING (41-48)

---

**41. Value Type vs Reference Type**

- **Value Type:** Boyutu onceden belirli, icinde dogrudan veri tutar. Stack'te saklanir. Ornekler: `int`, `long`, `float`, `double`, `char`, `boolean`, `byte`, `short`.
- **Reference Type:** Icinde verinin kendisi degil, verinin bulundugu hafiza adresini (referans) tutar. Referans Stack'te, nesne Heap'te saklanir. Ornekler: `String`, `Object`, tum siniflar, diziler.

---

**42. Stack ve Heap**

- **Stack:** Primitif tipler ve referanslar saklanir. LIFO mantigi ile calisir. Veriler ustuste dizilir. Hizli erisim. Veri hemen silinir. Boyutu sinirlidir (stack overflow olabilir).
- **Heap:** Referans tiplerin gercek nesneleri saklanir. Veriler karisik sekilde saklanir. Erismek Stack'e gore yavas. Temizlik Garbage Collector'a baglidir. Paylasilir alan.

Belirleyen sey: Primitif tipler → Stack, Nesneler (new ile olusturulan) → Heap.

---

**43. String neden reference type?**

String'in belirli bir boyutu yoktur — 1 karakter de olabilir, 1 milyon karakter de. Bu yuzden Stack'te sabit boyutla tutulamaz, Heap'te saklanir.

**Immutable** olmasi: String degeri degistirildiginde yeni bir obje olusturulur, eski obje degismez.
```java
String s = "Merhaba";
s = s + " Dunya"; // "Merhaba" objesi degismez, yeni "Merhaba Dunya" objesi olusturulur
// Eski "Merhaba" Garbage Collector'a birakilir
```
Referans (s) artik yeni objeyi gosterir.

---

**44. Boxing ve Unboxing**

- **Boxing:** Deger tipinin referans tipine donusmesi. Stack'teki deger Heap'e tasinir, Object icerisine gomulur.
```java
int n = 123;
Object obj = n; // Boxing: 123 Heap'te bir Object icerisine paketlenir
```

- **Unboxing:** Referans tipinin deger tipine donusmesi. Heap'teki nesneden deger cikarilir.
```java
int m = (int) obj; // Unboxing: Object icinden int deger cikarilir
```

**Performans etkisi:** Her iki islem de maliyetlidir. Boxing'de Heap'te nesne olusturulur (bellek ayrilir, deger kopyalanir). Unboxing'de casting islemi yapilir. Yogun donguler icinde kacinilmalidır.

---

**45. Stack ve Heap'te ne olur?**

```java
int n = 123;         // Stack: n = 123 (dogrudan deger)
Object obj = n;      // Boxing: Heap'te Integer objesi olusturulur, 123 icine konur
                     // Stack: obj = [Heap adresine referans]
int m = (int) obj;   // Unboxing: Heap'teki 123 degeri cikarilir
                     // Stack: m = 123 (dogrudan deger)
```

---

**46. LIFO ne demektir?**

**Last-In-First-Out:** Son giren ilk cikar. Stack'te veriler ustuste yigilir. Son eklenen veri ilk cikarilir. Aradaki bir veriye dogrudan erisilemez, ustundekiler cikmadan ulasilamaz.

Onemi: Method cagrilari stack frame olarak saklanir. Bir method baska bir methodu cagirdiginda yeni frame eklenir. Method donunce frame cikarilir. Bu yapida recursive cagrilar cok fazla olursa "StackOverflowError" olusur.

---

**47. Garbage Collector**

Java'da Heap bellegindeki kullanilmayan nesneleri otomatik temizleyen mekanizmadir.

- **Stack:** Scope bitince veriler otomatik silinir (method bitince frame kaldirilir). Deterministic — ne zaman silinecegi bellidir.
- **Heap:** Garbage Collector ne zaman calisacagi garanti degildir. Bir nesneye hicbir referans kalmadiginda GC onu "cöp" olarak isaretler ve uygun zamanda temizler.

`System.gc()` ile oneride bulunulabilir ama GC'nin hemen calisacagi garanti edilemez.

---

**48. Autoboxing**

Java 5 ile gelen otomatik kutulama ozelligi. Manuel boxing/unboxing gerekmez:
```java
Integer x = 5;       // Autoboxing: int 5 otomatik Integer'a donusur
int y = x;            // Auto-unboxing: Integer otomatik int'e donusur
```
Arka planda: `Integer x = Integer.valueOf(5);` ve `int y = x.intValue();` cagrilir.

---

## REFLECTION ve ANNOTATION (49-55)

---

**49. Reflection nedir?**

Nesnelerin sinif, method, degisken ve diger ozelliklerine calisma zamaninda (runtime) erismemizi saglayan ozelliktir.

Gson ornegi: `fromJson(jsonString, MyClass.class)` cagirildiginda Gson, Reflection ile MyClass'in field'larina eriserek degerlerini JSON'dan set eder. Sinifin yapisini onceden bilmesine gerek yoktur.

---

**50. Reflection ile sinif bilgilerine erisim**

```java
Class<?> cls = Class.forName("com.n11.oop.reflection.Deneme");

// Sinif ismi
cls.getName();          // "com.n11.oop.reflection.Deneme"
cls.getSimpleName();    // "Deneme"

// Constructor sayisi
cls.getConstructors().length;

// Field'lar
cls.getDeclaredFields();

// Methodlar
Method[] methods = cls.getDeclaredMethods();
for (Method m : methods) {
    m.getName();           // Method adi
    m.getReturnType();     // Donus tipi
    m.getParameterCount(); // Parametre sayisi
}

// Dinamik nesne olusturma ve method cagirma
Object obj = cls.newInstance();
Method method = cls.getMethod("gosterString", String.class);
method.invoke(obj, "test");
```

---

**51. Annotation nedir?**

Java'da method, sinif ve degiskenlere ust bilgi (metadata) eklemek icin kullanilan yapilardir.

Varsayilan ornekler:
- `@Override` — Ust siniftan override edildigini belirtir, yoksa derleme hatasi verir
- `@Deprecated` — Metodun artik kullanilmamasi gerektigini belirtir, uyari uretir
- `@SuppressWarnings` — Belirli uyarilari bastirmak icin
- `@FunctionalInterface` — Tek abstract methodu olan interface'i isaretler

---

**52. Custom annotation olusturma**

```java
@Target(ElementType.TYPE)              // Nereye uygulanabilir (sinif, method, field vb.)
@Retention(RetentionPolicy.RUNTIME)    // Ne zaman erisilebilir (SOURCE, CLASS, RUNTIME)
public @interface Bilgi {
    String bilgi();
    String tarih();
    String yazar();
}
```

- **@Target:** Annotation'in nereye uygulanabilecegini sinirlar (TYPE=sinif, METHOD=metod, FIELD=alan, CONSTRUCTOR=yapici vb.)
- **@Retention:** Annotation'in ne zamana kadar muhafaza edilecegini belirler:
  - `SOURCE` — Sadece kaynak kodda, derleme sirasinda atilir
  - `CLASS` — .class dosyasinda kalir ama runtime'da erisilemez
  - `RUNTIME` — Calisma zamaninda Reflection ile erisilebilir

---

**53. Runtime annotation'a Reflection ile erisim**

```java
Class<?> cls = Class.forName("com.n11.oop.annotation.TestAnnotation");
BilgiRuntime info = cls.getAnnotation(BilgiRuntime.class);
System.out.println(info.bilgi());  // "Annotation Örnek"
System.out.println(info.tarih()); // "26.03.2024"
System.out.println(info.yazar()); // "ibrahim gökyar"
```
Sadece `@Retention(RetentionPolicy.RUNTIME)` ile isaretlenmis annotation'lara bu sekilde erisilebilir.

---

**54. BilgiRuntime annotation'i ve kullanimi**

Tanim:
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BilgiRuntime {
    String bilgi();
    String tarih();
    String yazar();
}
```

Kullanim:
```java
@BilgiRuntime(bilgi = "Annotation Örnek", tarih="26.03.2024", yazar="ibrahim gökyar")
public class TestAnnotation {
    public static void main(String[] args) {
        Class cls = TestAnnotation.class;
        BilgiRuntime infoAnn = (BilgiRuntime) cls.getAnnotation(BilgiRuntime.class);
        System.out.println(infoAnn.bilgi());
    }
}
```

---

**55. ElementType.METHOD vs ElementType.TYPE**

- `@Target(ElementType.METHOD)` — Annotation sadece **metotlarin** basina konabilir
- `@Target(ElementType.TYPE)` — Annotation sadece **sinif, interface ve enum** basina konabilir

Ornek: `@Override` sadece method seviyesinde kullanilir (METHOD), `@BilgiRuntime` sinif seviyesinde kullanilir (TYPE).

---

## COLLECTIONS (56-58)

---

**56. List, Set ve Map farklari**

| Ozellik | List | Set | Map |
|---------|------|-----|-----|
| Sirali mi | Evet (index) | Hayir (genelde) | Hayir (genelde) |
| Tekrar | Izin verir | Izin vermez | Key tekrar olmaz, Value olabilir |
| Erisim | Index ile (get(0)) | Iterator ile | Key ile (get(key)) |
| Ornekler | ArrayList, LinkedList | HashSet, TreeSet | HashMap, TreeMap |
| Null | Birden fazla null | Tek null (HashSet) | Tek null key (HashMap) |

---

**57. Personel sinifi ve koleksiyon kullanimi**

```java
public class Personel {
    String ad, soyad;
    double maas;
}
```
Test sinifinda `ArrayList<Personel>` kullanilarak personel listesi olusturulmustur. `add()` ile ekleme, enhanced for-loop ile gezme yapilmistir. Projede `Comparable` veya `Comparator` kullanimi yoktur — siralama gerekmemistir.

---

**58. ArrayList vs LinkedList**

| Islem | ArrayList | LinkedList |
|-------|-----------|------------|
| Index erisim (get) | O(1) — hizli | O(n) — yavas |
| Basa/ortaya ekleme | O(n) — kaydirma gerekir | O(1) — pointer degisir |
| Sona ekleme | O(1) amortized | O(1) |
| Bellek | Daha az (sadece dizi) | Daha fazla (her node icin ekstra pointer) |

- **ArrayList:** Rastgele erisim cok, ekleme/silme az ise tercih edilir
- **LinkedList:** Basa/ortaya sik ekleme/silme varsa tercih edilir

---

## SPRING BOOT TEMELLERİ (59-68)

---

**59. Spring Boot nedir?**

Spring Framework uzerine kurulmus, hizli ve kolay uygulama gelistirmeyi saglayan bir framework'tur.

Avantajlari:
- **Auto-configuration:** Bagimliliga gore otomatik yapilandirma
- **Embedded server:** Tomcat/Jetty gomulu gelir, ayri kurulum gereksiz
- **Starter dependency:** `spring-boot-starter-web` gibi hazir paketler
- **Production-ready:** Actuator ile metrik, health check
- **Opinionated defaults:** En iyi pratikler varsayilan olarak gelir

Geleneksel Spring'den farki: XML konfigurasyonu yerine annotation-based, manuel Tomcat kurulumu yerine embedded server.

---

**60. Katmanli mimari**

```
Controller (ProjectController)
    ↓ HTTP istegi alir, Service'e yonlendirir
Service (ProjectServiceImpl)
    ↓ Is mantigi burada, Repository'yi cagirir
Repository (ProjectRepository)
    ↓ Veritabani islemleri (JPA sorgu)
Entity (Project)
    → Veritabani tablosunun Java karsiligi
```

- **Controller:** HTTP isteklerini karsilar, uygun service metodunu cagirir
- **Service:** Is kurallari ve mantigi burada uygulanir
- **Repository:** Veritabani erisimi (CRUD islemleri)
- **Entity:** Veritabani tablosunu temsil eden Java nesnesi

---

**61. @RestController vs @Controller**

- `@Controller` — View (HTML sayfa) doner. Thymeleaf, JSP gibi template engine'ler ile kullanilir.
- `@RestController` — `@Controller + @ResponseBody`. Method donus degeri dogrudan HTTP response body'ye yazilir (JSON/XML). REST API'ler icin kullanilir.

---

**62. HTTP metot annotation'lari**

| Annotation | HTTP Metodu | Amac |
|-----------|-------------|------|
| `@GetMapping` | GET | Veri okuma/listeleme |
| `@PostMapping` | POST | Yeni veri olusturma |
| `@PutMapping` | PUT | Mevcut veriyi tamamen guncelleme |
| `@DeleteMapping` | DELETE | Veri silme |
| `@PatchMapping` | PATCH | Verinin bir parcasini guncelleme |

---

**63. Constructor injection vs @Autowired field injection**

```java
// Constructor Injection (onerilen)
private final ProjectRepository projectRepository;
public ProjectServiceImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
}

// Field Injection (onerilmez)
@Autowired
private ProjectRepository projectRepository;
```

| Ozellik | Constructor Injection | Field Injection |
|---------|----------------------|-----------------|
| Immutability | `final` olabilir | Olamaz |
| Test edilebilirlik | Mock kolayca gecirilir | Reflection gerekir |
| Bagimliliklarin gorunurlugu | Acikca gorunur | Gizli kalir |
| Null guvenlik | Derleme zamani | Runtime NullPointer |

**Constructor injection tercih edilmelidir** — Spring de bunu onerir.

---

**64. JpaRepository<Project, Long>**

`JpaRepository<T, ID>` where T = entity sinifi, ID = primary key tipi.

Hazir methodlar:
- `findAll()` — Tum kayitlari getir
- `findById(Long id)` — ID ile bul (Optional doner)
- `save(Project p)` — Kaydet veya guncelle
- `deleteById(Long id)` — ID ile sil
- `count()` — Kayit sayisi
- `existsById(Long id)` — Var mi kontrol

Ozel sorgular icin method adlandirma ile otomatik sorgu: `findByProjectName(String name)`.

---

**65. JPA annotation'lari**

```java
@Entity                                    // Bu sinif bir JPA entity'dir (veritabani tablosuna karsilik gelir)
@Table(name="Project")                     // Tablo adi "Project" olarak belirlenir
public class Project implements Serializable {

    @Id                                    // Primary key alani
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    private Long id;

    @Column(name="project_name")           // Sutun adi "project_name" olarak eslestirilir
    private String projectName;

    @Column(name="insert_date")
    @Temporal(TemporalType.TIMESTAMP)      // Tarih/saat formatini belirler
    private Date insertDate;
}
```

---

**66. @Temporal(TemporalType.TIMESTAMP)**

`java.util.Date` veya `java.util.Calendar` alaninin veritabaninda nasil saklanacagini belirler:
- `TemporalType.DATE` — Sadece tarih (2024-03-26)
- `TemporalType.TIME` — Sadece saat (14:30:00)
- `TemporalType.TIMESTAMP` — Tarih + saat + milisaniye (2024-03-26 14:30:00.123)

Not: Java 8+ `LocalDateTime` kullanildiginda `@Temporal` gerekmez.

---

**67. findById().get() neden tehlikeli?**

`findById()` bir `Optional<Project>` doner. `.get()` dogrudan cagrildiginda kayit yoksa `NoSuchElementException` firlatir.

Guvenli alternatifler:
```java
// 1. orElseThrow
Project p = repository.findById(id)
    .orElseThrow(() -> new RuntimeException("Kayit bulunamadi"));

// 2. orElse
Project p = repository.findById(id).orElse(new Project());

// 3. isPresent kontrolu
Optional<Project> opt = repository.findById(id);
if (opt.isPresent()) { ... }
```

---

**68. HTTP status kodlari**

| Grup | Anlam | Ornekler |
|------|-------|---------|
| **1XX** | Bilgilendirme | 100 Continue, 101 Switching Protocols |
| **2XX** | Basari | 200 OK, 201 Created, 204 No Content |
| **3XX** | Yonlendirme | 301 Moved Permanently, 302 Found, 304 Not Modified |
| **4XX** | Istemci hatasi | 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found |
| **5XX** | Sunucu hatasi | 500 Internal Server Error, 502 Bad Gateway, 503 Service Unavailable |

---

## AOP - ASPECT ORIENTED PROGRAMMING (69-75)

---

**69. AOP nedir?**

Aspect-Oriented Programming, modularity'yi artirmayi ve Cross-Cutting Concern'ler arasindaki ayrimı saglayan bir programlama paradigmasidir. OOP'nin **yerine degil, yaninda** kullanilir — tamamlayicidir. Class, interface, inheritance, polymorphism gibi kavramlar devam eder.

AOP cozulmemis bir sorunu cozmekten ziyade, cozulmus bir soruna daha iyi cozum getirir — ozellikle encapsulation konusunda tamamlayicidir.

---

**70. Cross-Cutting Concern nedir?**

Katman bagimsiz, her katmanda birbirinden bagimsiz sekilde kullanilabilen parcalar/modullerdir. Dikey olarak tum katmanlari keser.

Ornekler:
- **Logging:** Her katmanda log tutma ihtiyaci
- **Exception Handling:** Tum katmanlarda hata yonetimi
- **Security:** Yetkilendirme kontrolu her yerde
- **Caching:** Performans icin onbellekleme
- **Transaction:** Veritabani islem yonetimi

Bunlari her methoda tek tek yazmak yerine AOP ile merkezi olarak tanimlanir.

---

**71. Concern, Join Point, Pointcut**

- **Concern:** Logging, Exception Handling, Caching, Security gibi kavramlar. AOP'un merkezindeki kavramsaldir.
- **Join Point:** Programin akisi sirasinda aspect kodunun ne zaman execute edilecegi. Bir on kosul (precondition). Ornegin "metod cagrilmadan once" veya "metod döndukten sonra".
- **Pointcut:** Join Point kumesidir. Birden fazla Join Point'i bir arada gruplar. Program calisirken bir Join Point'e gelindiginde, pointcut ile iliskilendirilmis kod parcacigi (concern) execute edilir — bu bir **interception**'dir.

---

**72. @Before, @After, @AfterReturning**

```java
@Before("execution(* ...MessageService.mesajVer(..))")
public void MesajVerMetodundanOnce(JoinPoint jp) {
    // mesajVer() cagirilmadan ONCE calisir
    // Parametre: jp.getArgs()[0]
}

@After("execution(* ...service.*.*(..))")
public void MesajVerMetodundanSonra(JoinPoint jp) {
    // Herhangi bir service methodu calistiktan SONRA calisir (basarili veya hata fark etmez)
}

@AfterReturning(pointcut="execution(* ...mesajVer(..))", returning="retVal")
public void DegerDonduktenSonra(Object retVal) {
    // mesajVer() basariyla deger DONDURDUKTEN sonra calisir
    // retVal = donen deger
}
```

---

**73. Pointcut ifadesinin parcalari**

`@Before("execution(* com.n11bootcamp.aopornek.service.MessageService.mesajVer(..))")`

- `execution` — Method calistirildiginda tetiklenir
- `*` — Herhangi bir donus tipi (void, String, int vb.)
- `com.n11bootcamp.aopornek.service` — Paket yolu
- `MessageService` — Sinif adi
- `mesajVer` — Method adi
- `(..)` — Herhangi sayida ve tipte parametre kabul eder

---

**74. Neden tum service'leri kapsar?**

`@After("execution(* com.n11bootcamp.aopornek.service.*.*(..))")`

- `service.*` — service paketindeki **tum siniflar** (sadece MessageService degil)
- `*(..)` — Her siniftaki **tum methodlar**

Eger sadece `MessageService` olsun istenseydi: `service.MessageService.*(..)` yazilmalidir.

---

**75. AOP'nin avantajlari ve SRP iliskisi**

Avantajlari:
- Is mantigi kodundan cross-cutting concern'ler ayrilir → **temiz kod**
- Ayni loglama kodu her methoda yazilmaz → **kod tekrari onlenir**
- Concern degisikligi tek yerden yapilir → **bakim kolayligi**
- Readability, Reusability, Extensibility artar

**SRP iliskisi:** Single Responsibility Prensibi her sinifin tek bir sorumlulugu olmasi gerektigini soyler. AOP ile loglama, guvenlik gibi sorumluluklar is mantigi siniflarindan ayrilarak ayri aspect siniflarinda toplanir. Boylece her sinif sadece kendi isine odaklanir.

---

## JWT AUTHENTICATION (76-82)

---

**76. JWT nedir?**

JSON Web Token, taraflar arasinda guvenli bilgi aktarimi icin kullanilan acik standarttir (RFC 7519). 3 parcadan olusur:

1. **Header:** Algoritma ve token tipi
```json
{"alg": "HS256", "typ": "JWT"}
```

2. **Payload:** Claims (talepler) — kullanici bilgileri ve metadata
```json
{"sub": "username", "iss": "www.opendart.com", "exp": 1711500000}
```

3. **Signature:** Header + Payload'in gizli anahtar ile imzalanmasi
```
HMACSHA256(base64(header) + "." + base64(payload), secretKey)
```

Sonuc: `xxxxx.yyyyy.zzzzz` (nokta ile ayrilmis 3 Base64 parca)

---

**77. TokenManager'da token uretimi**

```java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)                                    // Kullanici adi (payload'da "sub")
        .setIssuer("www.opendart.com")                          // Token'i ureten (payload'da "iss")
        .setIssuedAt(new Date(System.currentTimeMillis()))       // Olusturulma zamani ("iat")
        .setExpiration(new Date(System.currentTimeMillis() + 300000)) // Son gecerlilik ("exp") - 5dk
        .signWith(key)                                           // HS256 ile imzala
        .compact();                                              // String'e donustur
}
```
Key her uygulama baslatildiginda `Keys.secretKeyFor(HS256)` ile uretilir.

---

**78. JwtTokenFilter ve OncePerRequestFilter**

`OncePerRequestFilter` her HTTP istegi icin **tam olarak bir kez** calismayi garanti eder (forward/redirect durumlarinda tekrar calismaz).

Her istekte:
1. `Authorization` header'indan token cikarilir
2. Token gecerli mi kontrol edilir (imza + sure)
3. Gecerliyse `SecurityContextHolder`'a authentication set edilir
4. `filterChain.doFilter()` ile istek devam eder

---

**79. Token nasil cikarilir?**

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWI...
              ↑      ↑
              0-6    7'den itibaren token baslar
```

`substring(7)` kullanilir cunku "Bearer " ifadesi 7 karakter (B-e-a-r-e-r-bosluk). 7. indexten itibaren gercek JWT token baslar.

---

**80. SecurityContextHolder.getContext().setAuthentication()**

Spring Security'nin mevcut kullanici bilgisini tuttugu yerdir. Token dogrulandiktan sonra:
```java
UsernamePasswordAuthenticationToken upassToken =
    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
SecurityContextHolder.getContext().setAuthentication(upassToken);
```
Bu satir "bu kullanici dogrulanmis, istek devam edebilir" demektir. Sonraki filter'lar ve controller'lar `SecurityContextHolder`'dan authenticated kullaniciyi alabilir.

---

**81. SessionCreationPolicy.STATELESS neden?**

JWT stateless (durumsuz) bir mekanizmadir. Her istekte token gonderilir, sunucu session tutmaz.

| Ozellik | Session | JWT |
|---------|---------|-----|
| Durum | Sunucuda saklanir | Token icinde tasınır |
| Olceklenebilirlik | Zor (sticky session) | Kolay (herhangi sunucu dogrular) |
| Bellek | Sunucu RAM kullanir | Kullanmaz |
| Gecerlilik | Sunucu kontrol eder | Token suresi ile |

`STATELESS` ile Spring Security session olusturmaz → bellek tasarrufu + yatay olcekleme kolayligi.

---

**82. addFilterBefore neden "before"?**

```java
http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
```

JWT filter'in `UsernamePasswordAuthenticationFilter`'dan **once** calismasi gerekir cunku:
1. Once JWT token dogrulanir ve SecurityContext'e authentication set edilir
2. Sonra Spring Security'nin standart filter'i zaten dogrulanmis kullaniciyi gorur
3. Eger JWT filter sonra calissaydi, Spring Security tokensiz istegi reddederdi

Filter zinciri: ... → **JwtTokenFilter** → UsernamePasswordAuthenticationFilter → ...

---

## RABBITMQ (83-90)

---

**83. RabbitMQ nedir?**

Erlang dili ile yazilmis, open-source bir message broker'dir. Bir yerden aldigi veriyi (Producer) kendine abone olan baska bir yere (Consumer) sirayla teslim eder.

**Ne zaman kullanilir:**
- Aninda yapilmasi gerekmeyen islemler (mail, SMS gonderimi)
- E-ticaret siparis sonrasi fatura kesme, kargo entegrasyonu
- PDF olusturma gibi uzun sureli islemler
- Olceklenebilir uygulamalarda yuk dagitimi

**AMQP (Advanced Message Queue Protocol):** RabbitMQ'nun dogrudan destekledigi mesajlasma protokoludur. Producer/consumer arasinda standart bir iletisim saglar.

---

**84. RabbitMQ bileşenleri**

- **Producer (Publisher):** Mesaji olusturan ve gonderen taraf. Queue'yu bilmez, mesaji Exchange'e gonderir.
- **Consumer:** Abone oldugu queue'dan veriyi alip isleven taraf (tuketici).
- **Queue:** Mesajlarin siraya kondugu kuyruk yapisi. Consumer queue'ya abone olur.
- **Exchange:** Mesaji yonlendiren araci. Producer → Exchange → Queue → Consumer.
- **Binding:** Exchange ve Queue arasindaki baglantidir. "Bu exchange'ten gelen mesajlari su queue'ya yonlendir" der.
- **Routing Key:** Mesajin hangi queue'ya iletilecegini belirleyen anahtar. Exchange tipi ile birlikte calısır.

---

**85. RabbitMQConfiguration'da tanimlar**

```java
@Bean
Queue queue() {
    return new Queue("n11bootcamp_notification", false); // Queue adi, non-durable
}

@Bean
DirectExchange exchange() {
    return new DirectExchange("n11bootcamp_exchange"); // Direct tipinde exchange
}

@Bean
Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("n11bootcamp_routingkey");
    // Queue'yu exchange'e routing key ile bagla
}
```

---

**86. Exchange tipleri**

| Tip | Yonlendirme | Kullanim |
|-----|-------------|---------|
| **DirectExchange** | Routing key tam eslesme ile | Belirli queue'ya birebir yonlendirme |
| **TopicExchange** | Routing key pattern eslesmesi (* ve #) | Konu bazli yonlendirme (orn. `order.*`) |
| **FanoutExchange** | Routing key yok, tum queue'lara | Broadcast — tum abonelere |
| **HeaderExchange** | Header attribute ile | Kompleks yonlendirme kurallari |

Projede **DirectExchange** kullanilmis — routing key `n11bootcamp_routingkey` ile tam eslesme.

---

**87. convertAndSend nasil calisir?**

```java
rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
```

1. `user` nesnesi `Jackson2JsonMessageConverter` ile JSON'a donusturulur
2. JSON mesaj, belirtilen `exchange`'e gonderilir
3. Exchange, `routingJsonKey`'e gore uygun queue'yu bulur
4. Mesaj queue'ya eklenir
5. Queue'ya abone olan consumer mesaji alir

---

**88. @RabbitListener**

```java
@RabbitListener(queues = "n11bootcamp_notification")
public void consumeJsonMessage(User user) {
    LOGGER.info("Received: " + user.firstName + " " + user.lastName);
}
```

Bu annotation metodu belirtilen queue'nun dinleyicisi yapar. Queue'ya yeni mesaj geldiginde:
1. Spring AMQP mesaji otomatik olarak JSON'dan `User` nesnesine donusturur (Jackson2JsonMessageConverter ile)
2. `consumeJsonMessage` metodu otomatik tetiklenir
3. Mesaj islenir

---

**89. Jackson2JsonMessageConverter**

Varsayilan converter `SimpleMessageConverter`'dir ve Java serialization kullanir. Dezavantajlari: farkli diller arasinda uyumsuz, insan okunamaz.

`Jackson2JsonMessageConverter` ile:
- Mesajlar JSON formatinda gonderilir/alinir
- Dil bagimsiz (herhangi bir consumer JSON okuyabilir)
- Insan tarafindan okunabilir
- REST API'lerle uyumlu format

---

**90. Queue ozellikleri**

- **durable:** `true` ise broker restart oldugunda queue silinmez (persistent). `false` ise restart ile kaybolur (in-memory). Projede `false` — demo amacli.
- **exclusive:** `true` ise queue sadece olusturan connection tarafindan kullanilabilir. Baska connection'lar erisemez. Connection kapaninca queue silinir.
- **autoDelete:** `true` ise son consumer abone olmaktan ciktiginda queue otomatik silinir.

---

## MICROSERVICES MIMARISI (91-97)

---

**91. Microservices vs Monolithic**

| Ozellik | Monolithic | Microservices |
|---------|-----------|---------------|
| Yapi | Tek buyuk uygulama | Bagimsiz kucuk servisler |
| Deploy | Tamamı birlikte | Her servis bagimsiz |
| Olcekleme | Tamamı olceklenir | Sadece gereken servis |
| Teknoloji | Tek dil/framework | Her servis farkli olabilir |
| Hata etkisi | Tum uygulama etkilenir | Sadece o servis |
| Karmasiklik | Dusuk (baslangicta) | Yuksek (network, discovery, config) |

---

**92. @EnableEurekaServer ve ayarlar**

`@EnableEurekaServer` — Bu uygulamayi Eureka Service Registry yapar. Diger servisler buraya kayit olur.

```properties
eureka.client.register-with-eureka=false  # Kendini kayit etme (ben zaten serverim)
eureka.client.fetch-registry=false        # Baskalarindan kayit listesi alma (ben merkezdeyim)
```
Eureka Server kendisi bir servis degil, servislerin **kayit defteri**dir. Kendini kayit etmesi veya baskalarinin listesini almasi anlamsizdir.

---

**93. Servislerin Eureka'ya kaydi**

1. Servisin pom.xml'ine `spring-cloud-starter-netflix-eureka-client` eklenir
2. Ana sinifa `@EnableDiscoveryClient` annotation'i eklenir
3. application.properties'e Eureka adresi eklenir:
```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
```
4. Uygulama basladiginda otomatik olarak Eureka'ya kayit olur ve her 30 saniyede heartbeat gonderir.

`@EnableDiscoveryClient` — Servisi Eureka client olarak aktif eder, kayit ve kesif islemlerini baslatir.

---

**94. API Gateway ve lb://**

API Gateway, tum client isteklerinin gecis noktasidir. Istekleri ilgili servislere yonlendirir.

Route tanimlari:
```properties
spring.cloud.gateway.routes[0].id=PRODUCT-SERVICE
spring.cloud.gateway.routes[0].uri=lb://PRODUCT-SERVICE
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/product/**
```

- `Path=/api/product/**` → `/api/product/` ile baslayan istekler bu route'a duser
- `lb://PRODUCT-SERVICE` → **Load Balanced** anlamina gelir. Eureka'dan PRODUCT-SERVICE'in adresini bulur ve istegi yonlendirir. Birden fazla instance varsa aralarinda yuk dagitimi yapar.

---

**95. Config Server nedir?**

Tum mikroservislerin konfigurasyonunu merkezi bir yerden yonetmeyi saglar.

`@EnableConfigServer` — Uygulamayi Spring Cloud Config Server yapar. Diger servisler konfigurasyonlarini buradan cekerler.

Neden merkezi yonetim:
- 20 servisin her birinde ayni veritabani URL'ini degistirmek yerine tek yerden degistirilir
- Environment-bazli konfigurasyon (dev, staging, prod) merkezi olarak yonetilir
- Runtime'da konfigurasyon degistirme mumkun (`@RefreshScope` ile)
- Gizli bilgiler (sifreler) merkezi olarak sifrelenir

---

**96. discovery.locator.enabled**

```properties
spring.cloud.gateway.discovery.locator.enabled=true
```

Bu ayar aktif olduğunda, Eureka'ya kayitli tum servisler icin otomatik route olusturulur. Manuel route tanimlamaya gerek kalmaz. Servis adi ile erisim:
```
http://gateway:8763/PRODUCT-SERVICE/api/product/1
```

`lower-case-service-id=true` ile kucuk harf de calısır:
```
http://gateway:8763/product-service/api/product/1
```

Projede lokal ortamda `false` olarak birakilmistir (deadlock onlemek icin), Docker ortaminda `true` yapilabilir.

---

**97. Synchronous vs Asynchronous iletisim**

**Synchronous (Esanli):**
- REST API: Servis A, HTTP ile Servis B'yi cagirir ve **cevap bekler**
- Feign Client: Deklaratif REST client, interface tanimi ile
- Avantaj: Basit, anlık cevap
- Dezavantaj: Bagimlılık — Servis B cokerse A da bekler

**Asynchronous (Esanlisiz):**
- RabbitMQ: Servis A mesaji kuyruga birakir, **beklemeden devam eder**
- Kafka: Event streaming ile
- Avantaj: Servisler bagimsiz, Servis B cokse bile mesaj kuyrukta bekler
- Dezavantaj: Anlık cevap yok, karmasiklik artar

Projede: API Gateway → servisler arasi REST (sync), RabbitMQ ile bildirim/siparis islemleri (async).

---

## MAVEN ve DI/IoC (98-100)

---

**98. Maven nedir?**

Apache Maven, proje yonetim aracidir. Projenin tum yasam dongusune mudahale edebilecegi bir yapi saglar.

**pom.xml icerigi:**
- `groupId`, `artifactId`, `version` — Proje kimlik bilgileri
- `dependencies` — Proje bagimlilikları (kutuphaneler)
- `plugins` — Build sirasinda calisacak eklentiler
- `properties` — Ortak degiskenler (Java surumu vb.)

**Repository turleri:**
1. **Local Repository:** `~/.m2` dizininde. Indirilen jar'lar burada tutulur.
2. **Central Repository:** Maven'in merkez deposu (repo.maven.apache.org). Tum kutuphaneler burada bulunur.
3. **Remote Repository:** Ozel/kurumsal depo. pom.xml'de belirtilir.

Arama sirasi: Local → Central → Remote. Bulunamazsa hata.

---

**99. Dependency Injection (DI)**

Bagimlilik enjeksiyonu — bagimli nesmelerin disaridan verilmesidir. SOLID'in D (Dependency Inversion) prensibinin uygulamasidir.

**3 turu:**

1. **Constructor Injection:** Bagimlilik constructor parametresi ile verilir (onerilen)
```java
// springbootornek'teki ornek
private final ProjectRepository projectRepository;
public ProjectServiceImpl(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
}
```

2. **Setter Injection:** Setter metodu ile verilir
```java
private ProjectRepository repo;
@Autowired
public void setRepo(ProjectRepository repo) { this.repo = repo; }
```

3. **Method Injection:** Herhangi bir metod parametresi ile verilir
```java
@Autowired
public void configure(ProjectRepository repo) { this.repo = repo; }
```

DI sayesinde: gevsek bagimlilik, kolay test, kolay degistirebilirlik.

---

**100. Inversion of Control (IoC)**

Uygulama icindeki obje instance'larinin yonetiminin gelistiriciden alinip framework'e devredilmesidir.

**Neden kontrol framework'e verilir:**
- Framework gerekli kaynaklari olusturur ve yonetir
- Gelistirici sadece is mantigi kodunu yazar
- Nesne yasam dongusu otomatik yonetilir
- Bagimliliklarin olusturulmasi ve enjekte edilmesi otomatiktir

**Spring IoC Container:**
- `ApplicationContext` — Spring'in IoC container'idir
- Bean tanimlarina gore nesneleri olusturur, bagimliklarini cozer ve inject eder
- `@Component`, `@Service`, `@Repository`, `@Controller` ile isaretlenen siniflar otomatik olarak bean olarak kaydedilir
- Container, bean'lerin yasam dongusunu (creation, injection, destruction) yonetir

Akis: Framework baslar → Bean'leri olusturur → Bagimlilikları inject eder → Bizim kodumuzu cagirir → Kontrol yeniden framework'e doner = **Inversion of Control**.
