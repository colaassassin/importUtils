package com.example.demo.logs;

import jdk.nashorn.internal.ir.CallNode;
import lombok.Data;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.stream.*;


import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static java.util.stream.Collector.Characteristics.*;

class LogDesign{



    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private List<String> data = new ArrayList<>();

    public static LogDesign create(final Supplier<LogDesign> supplier){
        return supplier.get();
    }
    public  void stout_info(){
        logger.info("The data is " + data.toString());
        logger.info(() -> "The data is " + data.toString());
    }
}


public class logsTest {

    public static void main(String[] args) {
        System.out.println("logs_test_start===========");

        LogDesign l  = LogDesign.create(LogDesign::new);

        List<LogDesign> logDesigns = Arrays.asList(l);

        //logDesigns.forEach(LogDesign::stout_info);

        Consumer<LogDesign> consumer = LogDesign::stout_info;
        logDesigns.forEach(consumer);

        consumer.accept(logDesigns.get(0));

        Function<Integer, Integer> add2  = x -> x + 2;

        add2.apply(10);



    }


    @Test
    public void Test(){
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");

        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));

        lengthMap.forEach((key,value) -> System.out.printf("%5s: %s%n", key, value));



        Map<String, Object> map = new HashMap<String, Object>();
        map.put("acc","1");
        map.put("ac","1");

        for(String s : map.keySet()){
            Object o = map.get(s);
            String s1 = o.toString();
            Integer a = Integer.parseInt(s1);
            Float sc = Float.parseFloat("1.222");
            map.put(s, a);
        }


    }


    @Test
    public void Test_log(){
        Logger log = Logger.getLogger(this.getClass().getName());
        Consumer<String> printer = System.out::println;
        Consumer<String> logger = log::info;

        Consumer<String> printThenLog = printer.andThen(logger);
        Stream.of("this", "is", "a", "stream", "of", "strings").forEach(printThenLog);
    }

    @Test
    public void Steam_test(){
//        IntStream.rangeClosed(2, 1_0_0_0_0)
//                .forEach(System.out::println);
        String s = "sss";
        Object o = "sss";
        System.out.println(s.equals(o));
        System.out.println(Objects.equals(s, o));
    }

    public class Person {
        private String name;

        public Person() {}

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Team {
        private final NumberFormat nf = NumberFormat.getCurrencyInstance();

        private int id;
        private String name;

        private double salary;

        // 构造函数、getter与setter

        @Override
        public String toString() {
            return "Team{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + nf.format(salary) +
                    '}';
        }



    @Test
    public void ReBuild_test(){
        Person before = new Person("Grace Hopper");
        List<Person> people = Stream.of(before)
                .collect(toList());
        Person after = people.get(0);
        assertTrue(before == after);
        before.setName("Grace Murray Hopper");
        assertEquals("Grace Murray Hopper", after.getName());

        //装箱 将其转换成Stream<Integer>类型
        List<Integer> collect2 = IntStream.of(3, 1, 4, 1, 5, 9)
                .boxed()
                .collect(toList());

        //将元素转换成Obj类
        List<Integer> collect3 = IntStream.of(3, 1, 4, 1, 5, 9)
                .mapToObj(Integer::valueOf)
                .collect(toList());

        //三参式  先将流中是数据添加到临时数组中，然后通过ArrayList::addAll方法将值赋予接受数组
        ArrayList<Integer> collect = IntStream.of(3, 1, 4, 1, 5, 9)
                .collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);

        collect.forEach(System.out::println);

        //直转数组
        int[] ints = IntStream.of(3, 1, 4, 1, 5, 9).toArray();





        List<Integer> collect1 = Stream.of(3, 1, 4, 1, 5, 9).collect(toList());

       Stream.generate(Math::random);


        List<BigDecimal> list = Stream.iterate(BigDecimal.TEN, n -> n.add(BigDecimal.TEN))
                .limit(10)
                .collect(toList());

        list.forEach(System.out::println);



        Stream.iterate(LocalDate.now(),localDate -> localDate.plusDays(1L))
                .limit(10)
                .forEach(System.out::println);


        //数据流接口操作
        //串行流
        collect1.stream().filter(e -> e > 2).peek(System.out::println).collect(toList());;


        int sum = IntStream.rangeClosed(1, 50)
                .peek(n -> System.out.printf("original: %d%n", n))
                .map(n -> n * 2)
                .peek(n -> System.out.printf("doubled : %d%n", n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.printf("filtered: %d%n", n))
                .sum();

        System.out.println();
        BigDecimal reduce = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .reduce(BigDecimal.ZERO, (a, ac) -> {
                    System.out.print("first  " + a);
                    System.out.println("    second  " + ac);
                    return a.add(ac);
                });
        System.out.println(reduce);


    }


    @Test
    public void String_test() {

        String ac = "accccccaavvc";
        StringBuilder sb = new StringBuilder();
        for (char c : ac.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        System.out.println(sb.reverse());

        int[] a = {1,2};
        Arrays.stream(a).filter(e -> e>1).forEach(System.out::println);

        Optional<Integer> first = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5)
                .filter(e -> e > 1)
                .findFirst();

        System.out.println(first);

        first.orElseGet(()-> {
            System.out.println("null");
            return 1;
        } );

        List<String> strings = Arrays.asList("acc", "bbcc", "ccs", "sscc");

        HashSet<String> strings1 = new HashSet<>(strings);
        HashSet<String> strings2 = new HashSet<>(strings1);

        IntStream.rangeClosed(0, 50).forEachOrdered(e -> strings2.add(String.valueOf(e)));
        strings2.retainAll(strings1);

        // 这些集合是相等的，但具有不同的元素排序
        System.out.println(strings1.equals(strings2));
        System.out.println("Before: " + strings1);
        System.out.println("After : " + strings2);


    }


    public Integer deply(Integer n){
        try {
            System.out.println("延长时间 " + n);
            Thread.sleep((long) ((Math.random()) * 40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  n;
    }

    @Test
    public void void_test() {
        Optional<Integer> any = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).unordered().parallel().map(this::deply).findAny();
        System.out.println("Any：" + any);
    }




    public class PalindromeEvaluator{
        public boolean isPalindrome(String s) {
            String forward = s.toLowerCase().codePoints()
                    .filter(Character::isLetterOrDigit)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString();
            String backward = new StringBuilder(forward).reverse().toString();
            return forward.equals(backward);
        }

    }


    private PalindromeEvaluator demo = new PalindromeEvaluator();

    @Test
    public void isPalindrome() throws Exception {
        assertTrue(
                Stream.of("Madam, in Eden, I'm Adam",
                        "Go hang a salami; I'm a lasagna hog",
                        "Flee to me, remote elf!",
                        "A Santa pets rats as Pat taps a star step at NASA")
                        .allMatch(demo::isPalindrome));
        assertFalse(demo.isPalindrome("This is NOT a palindrome"));
    }



    }


    @Test
    public void contaic() {
        Stream<String> stringStream = Stream.of("1", "2");
        Stream<Integer> integerStream = Stream.of(1, 2, 34, 5);


        IntSummaryStatistics intSummaryStatistics = integerStream.mapToInt(Integer::valueOf).summaryStatistics();

        System.out.println(intSummaryStatistics.getMax());


        Stream<String> stringStream1 = Stream.of("accdd", "ccdde", "cvgb");

        Stream<Object> empty = Stream.empty();


//        List<? extends Serializable> collect = Stream.concat(stringStream, integerStream).collect(Collectors.toList());
//
//        List<? extends Serializable> collect1 = Arrays.asList("1", "2",1, 2, 34, 5);
//
//
//        assertEquals(collect, collect1);
//
//        collect.forEach(System.out::println);
//
//
//        List<? extends Serializable> collect2 = Stream.of(stringStream, stringStream1, integerStream)
//                .reduce(Stream.empty(), Stream::concat)
//                .collect(Collectors.toList());


        Stream<? extends Serializable> stream = Stream.of(stringStream, stringStream1, integerStream)
                .flatMap(Function.identity());
        stream.forEach(System.out::println);


    }


    public class Customer {
        private String name;
        private List<Order> orders = new ArrayList<>();

        public Customer(String name) {
            this.name = name;
        }

        public String getName() { return name; }
        public List<Order> getOrders() { return orders; }

        public Customer addOrder(Order order) {
            orders.add(order);
            return this;
        }
    }

    public class Order {
        private int id;

        public Order(int id) {
            this.id = id;
        }

        public int getId() { return id; }
    }


    @Test
    public void cou() {
        Customer customer = new Customer("delutdesu");

        customer.addOrder(new Order(1))
                .addOrder(new Order(2))
                .addOrder(new Order(3));



        assertFalse(Stream.empty().anyMatch(e -> true));
    }


    @Test
    public void dxStream() {


        long smillis = System.currentTimeMillis();

        OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(n -> n * 2)
                .filter(n -> n % 3 == 0)
                .findFirst();

        long emillis = System.currentTimeMillis();

        System.out.println("运行时间" + (emillis - smillis) + "ms");
        System.out.println(firstEvenDoubleDivBy3);




    }


    @Data
    public class Golfer {
        private String first;
        private String last;
        private int score;

        public Golfer(String first, String last, int score) {
            this.first = first;
            this.last = last;
            this.score = score;
        }
        // 其他方法


    }


    @Test
    public void comp() {
       List<Golfer> golfers = Arrays.asList(
                new Golfer("Jack", "Nicklaus", 68),
                new Golfer("Tiger", "Woods", 70),
                new Golfer("Tom", "Watson", 70),
                new Golfer("Ty", "Webb", 68),
                new Golfer("Bubba", "Watson", 70)
        );


        List<Golfer> collect = golfers.stream()
                .sorted(comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .collect(toList());
        collect.forEach(System.out::println);


    }

    @Data
    public class Employee {
        private String name;
        private Integer salary;
        private String department;
        private Home home;

        public Optional<Home> GetHome2(){
            return Optional.ofNullable(home);
        }

        public Employee(String name, Integer salary, String department, Home home) {
            this.name = name;
            this.salary = salary;
            this.department = department;
            this.home = home;
        }
        // 其他方法

    }


    @Data
    public class Home{
        private String address;

        public Home(String address) {
            this.address = address;
        }
    }


    @Test
    public void acot() {

        List<Employee> employees = Arrays.asList(
                null,
                new Employee("Cersei",     250_000, "Lannister", new Home("add")),
                new Employee("Jamie",      150_000, "Lannister", new Home("adda")),
                new Employee("Tyrion",       1_000, "Lannister", new Home("addf")),
                new Employee("Tywin",    1_000_000, "Lannister", new Home("adda3")),
                new Employee("Jon Snow",    75_000, "Stark", new Home("addw")),
                new Employee("Robb",       120_000, "Stark", new Home("adewd")),
                new Employee("Eddard",     125_000, "Stark", new Home("ad3d")),
                new Employee("Sansa",            0, "Stark", new Home("add2d")),
                new Employee("Arya",         1_000, "Stark", new Home("adddaw")));

        List<Employee> employees2 = new ArrayList<>();

        Employee defaultEmployee = new Employee("A man (or woman) has no name", 0, "Black and White", new Home("adddc"));


        OptionalInt max = employees.stream()
                .filter(Objects::nonNull)
                .mapToInt(Employee::getSalary)
                .max();
        int x = max.orElse(0);
        System.out.println(x);


        List<Employee> collect = employees.stream()
                .filter(Objects::nonNull).collect(toList());

        Optional<Employee> reduce = employees.stream()
               .filter(Objects::nonNull)
               .reduce(BinaryOperator.maxBy(comparingInt(Employee::getSalary)));



       Optional.ofNullable(employees)
                .flatMap(e -> SpfMap(e, Employee::getHome))
                .map( e -> SpMap(e, Home::getAddress))
                .orElseGet(() -> {
                    System.out.println("空");
                    return null;
                });




        Employee employee = reduce.orElse(defaultEmployee);
        System.out.println(reduce.flatMap(Employee::GetHome2).map(Home::getAddress));
        System.out.println(employee);

    }



    public Optional<List<Home>> File(List<Employee> employees){
        List<Home> returnHome = new ArrayList<>();
        if(Optional.ofNullable(employees).isPresent()){
            employees.forEach(e -> {
             if(Optional.ofNullable(e).isPresent())
                returnHome.add(e.getHome());
            });
        }
        return Optional.of(returnHome);
    }


    public List<String> HomeAdder(List<Home> homes){
        List<String> returnAddress = new ArrayList<>();
        if(Optional.ofNullable(homes).isPresent()){
            homes.forEach(e -> returnAddress.add(e.getAddress()));
        }
        return returnAddress;
    }


    public static  <T, U> Optional<List<T>> SpfMap(List<U> u, Function<? super U,? extends T> function){
        List<T> t= new ArrayList<>();
        if(Optional.ofNullable(u).isPresent()){
            u.forEach(e -> {
                if(Optional.ofNullable(e).isPresent())
                    t.add(function.apply(e));
            });
        }
        return Optional.of(t);
    }


    public static  <T, U> List<T> SpMap(List<U> u, Function<? super U,? extends T> function){
        List<T> t= new ArrayList<>();
        if(Optional.ofNullable(u).isPresent()){
            u.forEach(e -> {
                if(Optional.ofNullable(e).isPresent())
                    t.add(function.apply(e));
            });
        }
        return t;
    }




    @Test
    public void agbv() {
        try(Stream<String> lines = Files.lines(Paths.get("src/acfile/ttt"))){
            lines.map(e -> e.split(" ")).
                    flatMap(Arrays::stream).
                    filter(s -> s.length() > 5)
                    .peek(System.out::println)
                    .collect(Collectors.groupingBy(
                            String::length, Collectors.counting()
                    )).forEach((e, c) -> System.out.println("长度:" + e +"   合计"+ c));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Test
    public void FileWalk() {
        try (Stream<Path> paths = Files.walk(Paths.get("src/main/java"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int doubleIt(int n) {
        try {
            Thread.sleep(100);
            System.out.println("计算" + n + "花费: 0.1s");
        } catch (InterruptedException ignore) {
        }
        return n * 2;
    }

    @Test
    public void duration() {
        Instant now = Instant.now();
        int total = IntStream.of(3, 1, 4, 1, 5, 9)
                    .map(logsTest::doubleIt)
                    .sum();
        Instant end = Instant.now();
        System.out.println( "total = " +  total + "  speed: " + Duration.between(now, end));

    }
    

    @Test
    public void PathFind() {
        try(Stream<Path> text = Files.find(Paths.get("src/main/java"), Integer.MAX_VALUE, (path, file) -> file.isDirectory() && path.toString().contains("test"))){
            text.forEach(System.out::println);
            LocalDate of = LocalDate.of(2020, Month.JANUARY, 22);
            LocalDate localDate = of.plusDays(1_000_000);
            System.out.println(localDate);
            LocalDateTime now = LocalDateTime.of(2021, Month.JANUARY, 9, 10, 10, 10, 10);
            System.out.println(now);
            LocalDateTime localDateTime = now.plusNanos(20);

            System.out.println(localDateTime);
            System.out.println(localDateTime.withNano(2000));

            TemporalAdjuster t = temporal -> temporal.
                    plus(Period.ofDays(10));

            System.out.println(localDateTime.getDayOfWeek());

            LocalDateTime end = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

            System.out.println(ZonedDateTime.now());


            System.out.println(Month.OCTOBER.plus(100));

            System.out.println(end.atZone(ZoneId.of("America/New_York")));


            System.out.println(end);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void gofg() {
        try(Stream<String> lines = Files.lines(Paths.get("src/acfile/ttt"))){
            lines.map(e -> e.split("\040"))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.partitioningBy(e -> e.length() % 2 == 0)).forEach((k, v) -> System.out.println(k + "\n" + v));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Test
    public void st() {
        eventLength();
        List<String> strings = Arrays.asList("t", "c");
        List<String> strings2 = Arrays.asList("t", "c");
        System.out.println(Stream.of(strings, strings2).collect(new ToListCollector()));
        System.out.println(Objects.equals(strings,strings2));
        //Objects.requireNonNull(null, "cc");
        Optional.ofNullable(null).orElseGet(null);



        List<String> strings3 = Arrays.asList(
                "this", null, "is", "a", null, "list", "of", "strings", null);

        List<String> nonNullStrings = strings3.stream()
                .filter(Objects::nonNull)
    .collect(Collectors.toList());

        List<String> string4 =
                Arrays.asList("this", "is", "a", "list", "of", "strings");
        assertTrue(Objects.deepEquals(string4, nonNullStrings));

    }

    public void eventLength(String... strings){
        System.out.println(strings.length);
    }



    @Test
    public void random_test() {
        Random r = new Random();
        r.ints(5)
                .sorted()
                .forEach(System.out::println);

        r.doubles(5, 0, 0.5)
                .sorted()
                .forEach(System.out::println);

        List<Long> collect = r.longs(5)
                .boxed().collect(toList());
        System.out.println(collect);

        List<Object> collect1 = r.ints(5, 10, 20).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        System.out.println(collect1);
    }


    private final Map<Long, BigInteger> cache = new HashMap<>();

    private BigInteger fib(long i){
        if(i==0) return BigInteger.ZERO;
        if(i==1) return BigInteger.ONE;

        return cache.computeIfAbsent(i, n -> fib(n -2 ).add(fib(n - 1)));
    }

    @Test
    public void fib_test() {
        System.out.println(fib(10));

        String passage = "NSA agent walks into a bar. Bartender says, " +
                "'Hey, I have a new joke for you.' NSA agent says, 'heard it'.";
        Map<String, Integer> counts = wordCount(passage, "NSA", "agent", "joke");
        counts.forEach((word, count) -> System.out.println(word + "=" + count));

    }


    @Test
    public void aa() {
//        System.setProperty(
//                "java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        ForkJoinPool forkJoinPool = new ForkJoinPool(15);
        ForkJoinTask<Long> submit = forkJoinPool.submit(() -> LongStream.rangeClosed(1, 3_000_000)
                .parallel()
                .sum());


            try{

                Long aLong = submit.get();
                System.out.println(aLong);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                forkJoinPool.shutdown();
            }
        System.out.println(forkJoinPool.getPoolSize());
    }


    public Map<String, Integer> wordCount(String passage, String... words){
        HashMap<String, Integer> wc = new HashMap<>();
        Arrays.stream(words).forEach(e -> wc.put(e, 0));
        List<String> strings = Arrays.asList(words);
        Arrays.stream(passage.split(" ")).forEach(word -> {
            
        });
//        String specialS = passage.replaceAll("\\w", " ");
//        Arrays.stream(specialS.split("\\s+")).forEach(word1 ->
//                wc.merge(word1, 1, Integer::sum));;
        return wc;
    }


    @Test
    public void acc() {
        String a = null;
        AtomicInteger atc = new AtomicInteger();
        System.out.println(a);
        Optional<AtomicInteger> atc1 = Optional.ofNullable(atc);

        int andIncrement = atc.incrementAndGet();
        System.out.println(andIncrement);
        System.out.println(atc1.get().incrementAndGet());


        System.out.println(jugeOfNull(a));
    }



    @Test
    public void adjusters() throws Exception {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        System.out.println(start + " " + DayOfWeek.of(start.get(ChronoField.DAY_OF_WEEK)));
        LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(end+ " " + DayOfWeek.of(end.get(ChronoField.DAY_OF_WEEK)));
        end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        System.out.println(end+ " " + DayOfWeek.of(end.get(ChronoField.DAY_OF_WEEK)));
        end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        System.out.println(end+ " " + DayOfWeek.of(end.get(ChronoField.DAY_OF_WEEK)));
    }



    public <T> T jugeOfNull(T a){
        return Optional.ofNullable(a).isPresent() ? a : null;
    }


    private static final int SIZE = 1000000;


    public static void testCompletableFuture() {
        System.out.println("----start----" + LocalDateTime.now());
        //由于方法后面的add操作，这里的变量相当于全局变量，造成了线程安全问题出现
        List<Integer> list = new ArrayList<>();
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        IntStream.range(0, SIZE).forEach(i -> {
            //设置随机返回数字
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                Random random = new Random();
                return random.nextInt(Integer.MAX_VALUE);
            }).thenAccept(list::add);       //添加到list，出现线程安全问题
            futureList.add(future);
        });
        //主线程阻塞等待所有异步线程完成任务
        futureList.forEach(CompletableFuture::join);
        System.out.println("testCompletableFuture - size :" + list.size());
        System.out.println("----end----" + LocalDateTime.now());
        System.out.println();
    }


    @Test
    public void test_async() {
        testCompletableFuture();
    }
}

class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                IDENTITY_FINISH, CONCURRENT));
    }
}

interface Company{
    default String getName(){
        return "Initech";
    }
}

interface Employee{
    String getFirst();
    String getLast();

    void convertCaffeineToCodeForMoney();

    default String getName(){
        return String.format("%s %s",getFirst(),getLast());
    }
}

class CompanyEmployee implements Company, Employee {
    private String first;
    private String last;

    @Override
    public void convertCaffeineToCodeForMoney() {
        System.out.println("Coding...");
    }

    @Override
    public String getFirst() {
        return first;
    }

    @Override
    public String getLast() {
        return last;
    }


    @Override
    public String getName() {
        return Company.super.getName();
    }
}










class TsmAdjuster implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek of = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int daysToAdd;
        if (of == DayOfWeek.FRIDAY)
            daysToAdd = 3;
        else if (of == DayOfWeek.SATURDAY)
            daysToAdd = 2;
        else
            daysToAdd = 1;
        return temporal.plus(daysToAdd, ChronoUnit.DAYS);

    }
}


