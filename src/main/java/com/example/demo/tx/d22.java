package com.example.demo.tx;


import com.google.common.primitives.Ints;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

class Car2 {
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car2 create(final Supplier<Car2> supplier) {
        return supplier.get();
    }

    public static void collide(final Car2 car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car2 another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " +this.toString());
    }
}

public class d22 {


    public static void main(String[] args) {
        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        Car2 car  = Car2.create(Car2::new);
        Car2 car1 = Car2.create(Car2::new);
        Car2 car2 = Car2.create(Car2::new);
        Car2 car3 =new Car2();
        List<Car2> cars = Arrays.asList(car,car1,car2,car3);
        System.out.println("===================构造器引用========================");
        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(Car2::collide);
        System.out.println("===================静态方法引用========================");
        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(Car2::repair);
        System.out.println("==============特定类的任意对象的方法引用================");
        //特定对象的方法引用：它的语法是instance::method实例如下：
        final Car2 police = Car2.create(Car2::new);
        cars.forEach(police::follow);
        System.out.println("===================特定对象的方法引用===================");

        Random random =new Random();
        random.ints().limit(10).forEach(System.out::println);

        int[] numbers = {100, 200, 300, 400, 500, -600, -700, -800, -900, -1000};
        int numberMax = arrayMax(
                () ->  Ints.asList(numbers).stream().max((a, b) -> b - a).get()
        );
        System.out.println("数组中的最大值为：" + numberMax);


        DoubleSupplier doubleSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return Math.random();
            }
        };

        doubleSupplier = () -> Math.random();
        doubleSupplier = Math::random;
    }

    public static Integer arrayMax(Supplier<Integer> integerSupplier) {
        return integerSupplier.get();
    }
}
