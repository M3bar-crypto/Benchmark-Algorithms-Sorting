# Benchmark-Algorithms-Sorting

Name : Mustafa Osama Mustafa Ali - 
id : 3167104 - 
Groub : B 

The developed program creates a comprehensive performance benchmark to evaluate and compare five fundamental sorting algorithms (Bubble Sort, Selection Sort, Insertion Sort, Quick Sort, and Merge Sort) using a large array containing 100,000 elements. 
The code structure is based on the following components :
____________________________
Data Generation: Four versions of the arrays are created, representing four distinct data scenarios, to test the robustness and speed of each algorithm under various conditions:

Random: Contains entirely random numbers.

Reversely Sorted: Elements are arranged in descending order (from largest to smallest).

Partially Sorted: Approximately 80% of the elements are sorted, with some scattered random elements.

Fully Sorted: Elements are arranged in ascending order (from smallest to largest).

Timing: The code utilizes the system’s high-precision timer, `System.nanoTime()`, both before and after the execution of each algorithm.
The result is then converted into milliseconds (ms) to ensure measurement accuracy.

Execution Management and Output Formatting: The arrays are passed through a central function (`executeAlg`) to run the required algorithm. The results are subsequently printed in a precisely formatted table that adheres to the specified requirements.
____________________________
Program Readings : 
<img alt="image" src="https://github.com/user-attachments/assets/7ecd64db-9caa-47b5-9214-6f8e3da9b0d1" />

Illustrative graph :
<img alt="image" src="https://github.com/user-attachments/assets/24e34652-ddc2-48e8-9123-967b46324a23" />

Questions that have been noted?
Question: Why is Bubble Sort considered the fastest when an array is already fully sorted, yet the slowest in other scenarios?
Answer: At its core, the Bubble Sort algorithm relies on comparing adjacent elements and swapping their positions. 
Algorithmic and mathematical analysis explains the sharp contrast in its performance as follows:
Why it is the fastest when an array is fully sorted: 
Thanks to an optimization—a boolean flag (often called `swapped`)—the algorithm begins its first pass, comparing elements without needing to perform any swaps. Consequently, the `swapped` variable remains `false`, triggering an immediate exit from the loop after just one pass ($n-1$ comparisons).
This optimization reduces the actual time complexity in this specific case to (O(n)) (linear complexity), allowing it to finish in a fraction of a millisecond—outpacing even more advanced algorithms during that single pass.
Why it is the slowest in other scenarios (such as random or reverse-ordered data):
When data is random or in reverse order, the algorithm loses this advantage and must execute its full cycle using nested loops, which increases the time complexity to (O(n^2)) (quadratic complexity).
With a large dataset—for instance, 100,000 elements—an (O(n^2)) complexity results in millions of comparisons and swaps, which is extremely time-consuming. This places it at the bottom of the ranking, making it significantly slower than advanced, divide-and-conquer-based algorithms such as Merge Sort and Quick Sort 
(O(n \log n)).




