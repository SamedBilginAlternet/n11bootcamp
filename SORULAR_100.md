# n11 Bootcamp - 100 Soru

---

## JAVA OOP TEMELLERİ (1-20)

**1.** Nesneye Yonelik Programlamanin (OOP) 4 temel ozelligi nedir? Kısaca aciklayiniz.

**2.** Abstraction (Soyutlama) nedir? `Insan` sinifini ornek vererek aciklayiniz.

**3.** Encapsulation (Paketleme) nedir? `NESNE = VERİ + METOTLAR` formulunu aciklayiniz.

**4.** Polymorphism (Cok Bicimliligi) nedir? SEKIL sinifinden turetilen DAIRE, KARE, UCGEN ornegi uzerinden aciklayiniz.

**5.** Inheritance (Miras Alma) nedir? JavaProje'deki `Insan -> Cocuk -> AkilliCocuk` hiyerarsisinde hangi ozellikler miras alinir?

**6.** `abstract class` ile `interface` arasindaki farklar nelerdir? JavaProje'deki `Hayvan` abstract sinifi ile `IDinle`, `IOku`, `IYaz` interfacelerini karsilastiriniz.

**7.** JavaProje'deki `Kafa` sinifi `Goz`, `Burun`, `Kulak` siniflarini icerir. Bu hangi OOP iliskisini gosterir: Inheritance mi, Composition mu? Neden?

**8.** `@Override` annotation'i ne ise yarar? JavaProje/override paketindeki `Parent` ve `Child` siniflarinda nasil kullanilmistir?

**9.** Java'da `interface` icinde method body yazilabilir mi? Hangi Java surumuyle birlikte default method destegi geldi?

**10.** JavaProje'deki `ICalisan` interface'ini implement eden `Programci`, `Mudur`, `GenelMudur`, `Stajyer` siniflarinda polymorphism nasil gerceklesiyor?

**11.** `Calisan` abstract sinifinda abstract method ve concrete method birlikte bulunabilir mi? Ornek veriniz.

**12.** Java'da `super` anahtar kelimesinin kullanim alanlari nelerdir? Cocuk sinifi uzerinden aciklayiniz.

**13.** `Matematik` sinifinda `static` method kullanilmistir. Static method ile instance method arasindaki fark nedir?

**14.** Access modifier'lar (public, private, protected, default) arasindaki farki OOP prensiplerine gore aciklayiniz.

**15.** `Yoneticeler` sinifinda `Mudur` listesi tutulmaktadir. Bu composition mu yoksa aggregation mu? Farkini aciklayiniz.

**16.** Java'da constructor overloading nedir? `Insan` sinifinda parametresiz ve parametreli constructor farki nedir?

**17.** `YeniInsan` sinifi `Insan` sinifini extend ederken hangi ek ozellikler eklemistir? Bu OOP'nin hangi prensibini ornekler?

**18.** Java'da `this` anahtar kelimesinin 3 farkli kullanim alanini yaziniz.

**19.** `UsluCocuk` ve `AkilliCocuk` siniflarinin her ikisi de `Cocuk` sinifini extend eder. Bu yapida diamond problem olusabilir mi? Neden?

**20.** Java'da multiple inheritance desteklenmez. Bunun yerine ne kullanilir ve JavaProje'de nasil orneklenmistir?

---

## SOLID ve DESIGN PATTERNS (21-30)

**21.** SOLID prensiplerini tek tek aciklayiniz.

**22.** JavaProje'deki `solid/log/kotu` paketinde Open/Closed prensibine neden uyulmadigini aciklayiniz. `Logger` sinifinda yeni bir log tipi eklemek icin ne degistirmek gerekir?

**23.** JavaProje'deki `solid/log/iyi` paketinde Open/Closed prensibine nasil uyulmustur? `ILog` interface'i bu konuda nasil yardimci olmustur?

**24.** `LogFactory` sinifi hangi design pattern'i uygulamaktadir? Bu pattern'in avantajlari nelerdir?

**25.** `solid/log/kotu` paketinde `LogType` enum'i kullanilarak switch-case ile log yonlendirmesi yapilmistir. Bu yaklasimin dezavantaji nedir?

**26.** Observer Design Pattern nedir? Publisher (Yayinci) ve Subscriber (Abone) kavramlarini aciklayiniz.

**27.** Observer pattern'da `loose coupling` ne demektir? Subject ve Observer'larin birbirinden bagimsiz olmasi neden onemlidir?

**28.** Observer pattern ile Mediator pattern arasindaki fark nedir? Ikisi birlikte kullanilabilir mi?

**29.** Observer pattern'da dinamik abonelik (subscribe/unsubscribe) mekanizmasi nasil calisir?

**30.** Chain of Responsibility, Command, Mediator ve Observer patternleri alici-gondericileri baglamak icin farkli yontemler onerir. Her birini kisa aciklayiniz.

---

## GENERICS (31-40)

**31.** Java Generics nedir ve neden kullanilir? Generics olmadan `ClassCastException` nasil olusabilir?

**32.** Java 1.4 oncesinde `Object` ve raw type ile calismak neden tehlikeliydi? Kod ornegi ile aciklayiniz.

**33.** Type Safety nedir? Generics bu konuda nasil yardimci olur?

**34.** Asagidaki kodda derleme hatasi verir mi? Neden?
```java
List<String> list = new ArrayList<>();
list.add("Merhaba");
list.add(123);
```

**35.** Generic sinif, generic interface ve generic method arasindaki farki birer ornek ile gosteriniz.

**36.** Java'da Generic tip tanimlarken neden `T` kullanilir? `E`, `K`, `V` ne anlama gelir?

**37.** Bounded Types nedir? `<T extends Number>` ifadesi ne anlama gelir? Kare alani hesaplama ornegi uzerinden aciklayiniz.

**38.** Wildcard turleri nelerdir? `Unbounded (?)`, `Upper Bounded (? extends)` ve `Lower Bounded (? super)` arasindaki farki aciklayiniz.

**39.** Type Erasure nedir? Derleme sonrasi Generic tipler ne olur? Asagidaki kod derlendikten sonra nasil gorunur?
```java
public static <E> void printArray(E[] array) { ... }
```

**40.** Type Erasure nedeniyle asagidaki iki method neden overload edilemez?
```java
public void print(List<String> param);
public void print(List<Integer> param);
```

---

## VALUE/REFERENCE TYPE, STACK/HEAP, BOXING (41-48)

**41.** Value Type ve Reference Type arasindaki fark nedir? Ornekler veriniz.

**42.** Stack ve Heap bellekte nasil calisir? Verilerin Stack veya Heap'te saklanmasini belirleyen sey nedir?

**43.** `String` neden reference type'dir? String'in `immutable` olmasi ne demektir?

**44.** Boxing (Kutulama) ve Unboxing (Kutu acma) nedir? Performansa etkileri nelerdir?

**45.** Asagidaki kodda Stack ve Heap uzerinde ne olur?
```java
int n = 123;
Object obj = n; // boxing
int m = (int) obj; // unboxing
```

**46.** Stack LIFO (Last-In-First-Out) mantigi ile calisir. Bu ne anlama gelir ve neden onemlidir?

**47.** Garbage Collector ne ise yarar? Stack'teki veriler neden hemen silinirken Heap'teki veriler GC'ye bagli?

**48.** Java 5 ile gelen Autoboxing ozelligi nedir? `Integer x = 5;` ifadesi arka planda ne yapar?

---

## REFLECTION ve ANNOTATION (49-55)

**49.** Java Reflection nedir ve ne ise yarar? `Gson` kutuphanesi Reflection'i nasil kullanir?

**50.** Reflection ile bir sinifin ismine, constructorlarinin sayisina, field ve methodlarina nasil erisilir?

**51.** Annotation nedir? Java'daki varsayilan annotation'lara (@Override, @Deprecated) ornek veriniz.

**52.** Custom annotation nasil olusturulur? `@Target` ve `@Retention` annotation'lari ne ise yarar?

**53.** `@Retention(RetentionPolicy.RUNTIME)` ile isaretlenen annotation'lara Reflection ile nasil erisilir?

**54.** JavaProje'deki `BilgiRuntime` annotation'i nasil tanimlanmistir ve `TestAnnotation` sinifinda nasil kullanilmistir?

**55.** `@Target(ElementType.METHOD)` ile `@Target(ElementType.TYPE)` arasindaki fark nedir?

---

## COLLECTIONS (56-58)

**56.** Java Collections Framework'teki `List`, `Set` ve `Map` arasindaki farklari aciklayiniz.

**57.** JavaProje'deki `collections/Personel` sinifi hangi koleksiyonlarda kullanilmistir? `Comparable` veya `Comparator` kullanimi var mi?

**58.** `ArrayList` ile `LinkedList` arasindaki performans farki nedir? Hangi durumda hangisi tercih edilmelidir?

---

## SPRING BOOT TEMELLERİ (59-68)

**59.** Spring Boot nedir ve ne avantajlar saglar? Geleneksel Spring Framework'ten farkli nedir?

**60.** `springbootornek` projesindeki katmanli mimariyi (Controller -> Service -> Repository -> Entity) aciklayiniz.

**61.** `@RestController` ile `@Controller` arasindaki fark nedir?

**62.** `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` annotation'lari hangi HTTP metotlarina karsilik gelir?

**63.** `ProjectServiceImpl` sinifinda constructor injection kullanilmistir. Constructor injection ile `@Autowired` field injection arasindaki fark nedir? Hangisi tercih edilmelidir?

**64.** `ProjectRepository` sinifi `JpaRepository<Project, Long>` interface'ini extend eder. Bu ne anlama gelir ve hangi hazir methodlari saglar?

**65.** `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column` annotation'larini Project entity'si uzerinden aciklayiniz.

**66.** `@Temporal(TemporalType.TIMESTAMP)` ne ise yarar?

**67.** `projectRepository.findById(id).get()` kullanimi neden tehlikeli olabilir? Bunun yerine ne kullanilmalidir?

**68.** REST API'de HTTP status kodlari ne anlama gelir? 1XX, 2XX, 3XX, 4XX, 5XX gruplarini aciklayiniz.

---

## AOP - ASPECT ORIENTED PROGRAMMING (69-75)

**69.** AOP (Aspect Oriented Programming) nedir? OOP ile birlikte mi yoksa yerine mi kullanilir?

**70.** Cross-Cutting Concern nedir? Logging, Exception Handling, Security, Caching orneklerini aciklayiniz.

**71.** AOP'daki Concern, Join Point ve Pointcut kavramlarini aciklayiniz.

**72.** `aopornek` projesindeki `ServiceAspect` sinifinda `@Before`, `@After` ve `@AfterReturning` annotation'lari ne ise yarar?

**73.** `@Before("execution(* com.n11bootcamp.aopornek.service.MessageService.mesajVer(..))")` ifadesini parcalara ayirarak aciklayiniz.

**74.** `@After("execution(* com.n11bootcamp.aopornek.service.*.*(..))")` ifadesi neden `MessageService` disindaki tum service'leri de kapsar?

**75.** AOP kullanmanin avantajlari nelerdir? Single Responsibility prensibi ile iliskisi nedir?

---

## JWT AUTHENTICATION (76-82)

**76.** JWT (JSON Web Token) nedir? Hangi 3 parcadan olusur?

**77.** `jwtornek` projesindeki `TokenManager` sinifinda token nasil uretilir? `setSubject`, `setIssuer`, `setExpiration` ne ise yarar?

**78.** `JwtTokenFilter` sinifi `OncePerRequestFilter`'i extend eder. Bu ne anlama gelir ve her istekte ne yapar?

**79.** `Authorization: Bearer <token>` header'indan token nasil cikarilir? `substring(7)` neden kullanilir?

**80.** `SecurityContextHolder.getContext().setAuthentication(upassToken)` ne ise yarar?

**81.** `WebSecurityConfiguration` sinifinda `SessionCreationPolicy.STATELESS` neden kullanilir? JWT ile session arasindaki fark nedir?

**82.** `addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)` ne anlama gelir? Neden "before"?

---

## RABBITMQ (83-90)

**83.** RabbitMQ nedir ve ne zaman kullanilir? AMQP protokolu ne demektir?

**84.** RabbitMQ'nun temel bilesenlerini aciklayiniz: Producer, Consumer, Queue, Exchange, Binding, Routing Key.

**85.** `rabbitmq` projesindeki `RabbitMQConfiguration` sinifinda Queue, Exchange ve Binding nasil tanimlanmistir?

**86.** `DirectExchange` nedir? `TopicExchange` ve `FanoutExchange`'den farki nedir?

**87.** `RabbitMQProducer` sinifinda `rabbitTemplate.convertAndSend(exchange, routingJsonKey, user)` metodu nasil calisir?

**88.** `@RabbitListener(queues = "n11bootcamp_notification")` annotation'i ne ise yarar? Consumer mesaji nasil alir?

**89.** `Jackson2JsonMessageConverter` neden kullanilir? Varsayilan converter'dan farki nedir?

**90.** RabbitMQ'da Queue'nun `durable`, `exclusive` ve `autoDelete` ozellikleri ne anlama gelir?

---

## MICROSERVICES MIMARISI (91-97)

**91.** Microservices mimarisi nedir? Monolithic mimariden farki nedir?

**92.** `discovery-server` projesinde `@EnableEurekaServer` ne ise yarar? `register-with-eureka=false` ve `fetch-registry=false` neden ayarlanmistir?

**93.** Eureka Discovery Server'a bir servis nasil kayit olur? `@EnableDiscoveryClient` annotation'i ne ise yarar?

**94.** API Gateway nedir? `api-gateway` projesinde route'lar nasil tanimlanmistir? `lb://PRODUCT-SERVICE` ifadesindeki `lb://` ne demektir?

**95.** Config Server nedir? `@EnableConfigServer` ne ise yarar? Neden tum servislerin konfigurasyonu merkezi olarak yonetilmelidir?

**96.** `spring.cloud.gateway.discovery.locator.enabled=true` ayari ne ise yarar?

**97.** Microservices mimarisinde servisler arasi iletisim yontemlerini aciklayiniz: Synchronous (REST/Feign) vs Asynchronous (RabbitMQ).

---

## MAVEN ve DI/IoC (98-100)

**98.** Maven nedir? `pom.xml` dosyasinin icerigi ve amaci nedir? Local, Central ve Remote Repository kavramlarini aciklayiniz.

**99.** Dependency Injection (DI) nedir? 3 turunu (Constructor, Setter, Method Injection) aciklayiniz. `springbootornek`'teki constructor injection ornegini gosteriniz.

**100.** Inversion of Control (IoC) nedir? Framework kontrolu neden gelistiriciden alinir? Spring IoC Container bu kavramda nasil rol oynar?
