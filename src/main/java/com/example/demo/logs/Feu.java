package com.example.demo.logs;

import com.google.common.base.Predicate;
import com.google.common.base.Utf8;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.apache.commons.math3.special.Gamma.GAMMA;
import static org.apache.tomcat.util.net.openssl.ciphers.Encryption.SEED;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Warmup(iterations = 1)
@Measurement(iterations = 2)
public class Feu {

    ExecutorService executorService = Executors.newFixedThreadPool(4);
    int n = 0;

    private static Unsafe unsafe = null;

    public String returnString() {
        try {
            n++;
            Thread.sleep(100);
            System.out.println("输出" + n + "次");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "2233";
    }

    @TearDown
    public void finish() {
        executorService.shutdown();
    }

    @Benchmark
    public void test_acc() {

        CompletableFuture.supplyAsync(this::returnString, executorService)
                .thenApply(Integer::parseInt)
                .thenApply(Math::sqrt)
                .thenAccept(System.out::println)
                .join();
    }

    @Benchmark
    public void test_ac() {
        CompletableFuture.supplyAsync(this::returnString)
                .thenApply(Integer::parseInt)
                .thenApply(Math::sqrt)
                .thenAccept(System.out::println)
                .join();
    }

    public static void main(String[] args) {
        Options options = new OptionsBuilder().include(Feu.class.getSimpleName()).build();
        try {
            new Runner(options).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() {
        Instant start = Instant.now();
        test_acc();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }


    @Test
    public void aVoid() {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert theUnsafe != null;
        theUnsafe.setAccessible(true);
        try {
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Class<?> threadClass = Thread.class;
        try {
            System.out.println(unsafe.objectFieldOffset(threadClass.getDeclaredField("threadLocalRandomSeed")));
            System.out.println(unsafe.objectFieldOffset(threadClass.getDeclaredField("threadLocalRandomProbe")));
            System.out.println(unsafe.objectFieldOffset(threadClass.getDeclaredField("threadLocalRandomSecondarySeed")));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}


