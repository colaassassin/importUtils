package com.example.demo.tx;


import com.google.common.base.Predicate;

import java.io.UnsupportedEncodingException;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;



class Car{

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    int age;

    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
}

class User{
    private String name;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Address{
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

public class demo {
    public static void main(String[] args) {

        //demo demo = new demo();

        Car car = Car.create(Car::new);
        car.age = 10001;
        System.out.println(car.age);
        Car c22 = Car.create(Car::new);
        System.out.println(c22.age);


        System.out.println("输出所有数据");
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        eval(list, n -> true);
        System.out.println("输出奇数");
        eval(list, e -> e % 2 == 1);
        System.out.println("偶数");
        eval(list, e -> e % 2 == 0);
        System.out.println("寄数2");
        eval(list.stream().distinct().collect(Collectors.toList()), e -> e % 2 == 1);





        System.out.println("大于4");
        eval(list, e -> e > 4);

        System.out.println("end");




        int mix = 10;
        list.forEach(e -> e += mix);




        Random random =new Random();
        //random.ints().limit(10).forEach(System.out::println);
        //random.ints().limit(10).sorted().forEach(System.out::println);

        list.sort((a, b) -> b - a);
        list.forEach(System.out::println);


        User user = null;

        try {
            Optional.ofNullable(user).orElseThrow(()->new Exception("用户不存在"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        user = Optional.ofNullable(user).orElseGet(() -> createUser());
        user.setName("ccc");
        System.out.println(user.getName());
        user = Optional.ofNullable(user).orElse(createUser());
        System.out.println(user.getName());






        Short.valueOf("1.2");

        Address address = new Address();
        address.setCity("acc");
        user.setAddress(address);
        Optional.ofNullable(user)
                .map(u-> u.getAddress())
                .map(a-> a.getCity())
                .orElseThrow(()->new NullPointerException("取指错误"));


        testLocalDateTime();

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);



        StringBuilder stringBuilder =new StringBuilder();
        for (int i =0; i <10; ++i) {
            stringBuilder.append(UUID.randomUUID());
        }
        byte[] mimeBytes = new byte[0];
        try {
            mimeBytes = stringBuilder.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
    }


    public static void testLocalDateTime() {
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);
        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);
        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();
        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER,12);
        System.out.println("date3: " + date3);
        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22,15);
        System.out.println("date4: " + date4);
        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

    private static User createUser() {
        User user = new User();
        user.setName("aaa");
        return user;
    }


    public static void eval(List<Integer> list, Predicate<Integer> predicate){
        list.forEach(e -> {
            if(predicate.test(e))
                System.out.print(e + " ");
        });
}
    
}




