package com.synechisveltiosi.leetcode.leetcode;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class S0001TwoSumArrayBenchmarkTest {

    private static final int TEST_TARGET = 99_999;
    private static final int BENCHMARK_TARGET = 199_997;
    private static final int FAST_BENCHMARK_ITERATIONS = 5;

    private S0001TwoSumArraySolution.TwoPointerSolution twoPointerSolution;
    private S0001TwoSumArraySolution.BruteForceSolution bruteForceSolution;
    private S0001TwoSumArraySolution.HashTableSolution hashTableSolution;

    private int[] nums;

    private static Stream<Arguments> twoSumInputs() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1}),
                Arguments.of(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2})
        );
    }

    @Setup(Level.Trial)
    @BeforeEach
    public void init() {
        twoPointerSolution = new S0001TwoSumArraySolution.TwoPointerSolution();
        bruteForceSolution = new S0001TwoSumArraySolution.BruteForceSolution();
        hashTableSolution = new S0001TwoSumArraySolution.HashTableSolution();
        nums = createBenchmarkInput();
    }

    @Benchmark
    public int[] bruteForce() {
        return bruteForceSolution.twoSumBruteForce(nums, BENCHMARK_TARGET);
    }

    @Benchmark
    public int[] hashMap() {
        return hashTableSolution.twoSumWithMap(nums, BENCHMARK_TARGET);
    }

    @Benchmark
    public int[] twoPointer() {
        return twoPointerSolution.twoSumWithTwoPointer(nums, BENCHMARK_TARGET);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("twoSumInputs")
    void twoSumBruteForce(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, bruteForceSolution.twoSumBruteForce(nums, target));
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("twoSumInputs")
    void twoSumWithMap(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, hashTableSolution.twoSumWithMap(nums, target));
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("twoSumInputs")
    void twoSumWithTwoPointer(int[] nums, int target, int[] expected) {
        assertArrayEquals(expected, twoPointerSolution.twoSumWithTwoPointer(nums, target));
    }

    @Order(4)
    @Test
    void bruteForceReturnsExpectedIndexes() {
        assertValidTwoSum(bruteForceSolution.twoSumBruteForce(nums, TEST_TARGET), TEST_TARGET);
    }

    @Order(5)
    @Test
    void hashMapReturnsExpectedIndexes() {
        assertValidTwoSum(hashTableSolution.twoSumWithMap(nums, TEST_TARGET), TEST_TARGET);
    }

    @Order(6)
    @Test
    void twoPointerReturnsExpectedIndexes() {
        assertValidTwoSum(twoPointerSolution.twoSumWithTwoPointer(nums, TEST_TARGET), TEST_TARGET);
    }

    @Order(7)
    @Test
    void printBenchmarkSummary() {
        BenchmarkResult bruteForce = benchmark("twoSumBruteForce", this::bruteForce, 1);
        BenchmarkResult hashMap = benchmark("twoSumWithMap", this::hashMap, FAST_BENCHMARK_ITERATIONS);
        BenchmarkResult twoPointer = benchmark("twoSumWithTwoPointer", this::twoPointer, FAST_BENCHMARK_ITERATIONS);

        System.out.println();
        System.out.printf("%-25s %s%n", "Benchmark", "Score");
        System.out.printf("%-25s %.2f ms%n", bruteForce.name(), bruteForce.millis());
        System.out.printf("%-25s %.2f ms%n", hashMap.name(), hashMap.millis());
        System.out.printf("%-25s %.2f ms%n", twoPointer.name(), twoPointer.millis());
        System.out.println();
        System.out.println("Brute Force  : O(n²)");
        System.out.println("HashMap      : O(n)      ← fastest");
        System.out.println("Two Pointer  : O(n log n)");
    }

    private void assertValidTwoSum(int[] indexes, int target) {
        assertEquals(2, indexes.length);
        assertNotEquals(indexes[0], indexes[1]);
        assertEquals(target, nums[indexes[0]] + nums[indexes[1]]);
    }

    private BenchmarkResult benchmark(String name, Supplier<int[]> benchmark, int iterations) {
        long elapsedNanos = 0;
        for (int iteration = 0; iteration < iterations; iteration++) {
            long start = System.nanoTime();
            int[] indexes = benchmark.get();
            elapsedNanos += System.nanoTime() - start;

            assertValidTwoSum(indexes, BENCHMARK_TARGET);
        }
        return new BenchmarkResult(name, elapsedNanos / 1_000_000.0 / iterations);
    }

    private int[] createBenchmarkInput() {
        int[] values = IntStream.range(0, 100_000).toArray();
        for (int i = 0; i < values.length - 2; i++) {
            int swapIndex = (i * 31 + 17) % (values.length - 2);
            int value = values[i];
            values[i] = values[swapIndex];
            values[swapIndex] = value;
        }
        values[values.length - 2] = 99_998;
        values[values.length - 1] = 99_999;
        return values;
    }

    private record BenchmarkResult(String name, double millis) {
    }

}
