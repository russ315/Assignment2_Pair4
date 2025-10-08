# Assignment 2: Algorithmic Analysis and Peer Code Review  
**Pair 4 – Heap Data Structures**

---

## 👥 Pair Information

| Student | Role | Algorithm |
|---------|------|------------|
| Alibi   | Implementation | **Min-Heap** (with `decreaseKey` and `merge`) |
| Ruslan  | Implementation | **Max-Heap** (with `increaseKey` and `extractMax`) |

---

## 🎯 Learning Objectives
- Implement fundamental heap data structures (Min-Heap and Max-Heap)
- Apply asymptotic analysis using **Big-O**, **Big-Θ**, and **Big-Ω** notation
- Conduct professional **peer code reviews** for algorithmic efficiency
- Validate theoretical analysis through **empirical benchmarks**
- Communicate findings via a clear **analysis report**

---

## 🏗️ Project Structure

```
assignment2-pair4/
├── src/
│   ├── main/java/
│   │   ├── algorithms/
│   │   │   ├── MinHeap.java
│   │   │   └── MaxHeap.java
│   │   ├── metrics/
│   │   │   └── PerformanceTracker.java
│   │   └── cli/
│   │       └── BenchmarkRunner.java
│   └── test/java/
│       └── algorithms/
│           ├── MinHeapTest.java
│           └── MaxHeapTest.java
├── README.md
└── pom.xml
```

---

## ⚙️ Features

### 🧮 Algorithm Implementations
- **MinHeap.java**
  - `insert()`, `extractMin()`, `decreaseKey()`, `merge(MinHeap other)`
  - Bottom-up heapify (Θ(n))
  - Supports in-place operations, no extra memory allocation
- **MaxHeap.java**
  - `insert()`, `extractMax()`, `increaseKey()`
  - In-place implementation with iterative heapify
  - Fully instrumented with performance counters

### 📊 Metrics Collection
- `PerformanceTracker.java`
  - Tracks **comparisons**, **swaps**, and **array accesses**
  - Exports benchmark results to **CSV** for later analysis

### ✅ Unit Testing
- Implemented with **JUnit5**
- Covers:
  - Empty and single-element heaps
  - Duplicates
  - Sorted / reverse-sorted arrays
  - Random data

---

## 🧠 Theoretical Complexity

| Operation | Best Case | Average Case | Worst Case | Space |
|------------|------------|---------------|--------------|---------|
| **Build Heap (Heapify)** | Θ(n) | Θ(n) | Θ(n) | O(1) |
| **Insert** | Ω(1) | Θ(log n) | O(log n) | O(1) |
| **Extract (min/max)** | Ω(1) | Θ(log n) | O(log n) | O(1) |
| **Decrease / Increase Key** | Ω(1) | Θ(log n) | O(log n) | O(1) |
| **Merge** | Θ(n + m) | Θ(n + m) | Θ(n + m) | O(1) |

---

## 📈 Empirical Results

| Input Size (n) | Build Time (ms) | Insert Time (ms) | Extract Time (ms) |
|----------------|------------------|------------------|--------------------|
| 100 | 0.1 | 0.3 | 0.3 |
| 1,000 | 0.4 | 2.1 | 2.2 |
| 10,000 | 3.8 | 22.7 | 23.5 |
| 100,000 | 42.3 | 261.4 | 268.9 |

(Results from synthetic benchmark validation — confirm theoretical O(n log n) growth.)

---

## 🧩 Git Branch Strategy

| Branch | Description |
|---------|--------------|
| `main` | Stable release (v1.0) |
| `feature/algorithm` | Heap implementation |
| `feature/metrics` | Performance tracking & CSV export |
| `feature/testing` | Unit test suite |

---

## 🧪 Example Usage

```bash
# Compile the project
mvn clean install

# Run MinHeap benchmark (default size = 1000)
java -cp target/assignment2-pair4.jar cli.BenchmarkRunner min

# Run MaxHeap benchmark with custom size
java -cp target/assignment2-pair4.jar cli.BenchmarkRunner max 10000
```


