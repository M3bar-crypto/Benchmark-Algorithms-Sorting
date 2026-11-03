package vscode;

import java.util.Random; // استيراد حزمة توليد الأرقام العشوائية
import java.util.Scanner; // استيراد حزمة قراءة مدخلات المستخدم من لوحة المفاتيح

public class DataStru2_HomeWork_1 {

    public static void main(String[] args) {
        
        int n = 100_000; // تعريف حجم مصفوفة البيانات ليكون 100 ألف عنصر
        Scanner scanner = new Scanner(System.in); // إنشاء كائن لقراءة المدخلات
        Random random = new Random(); // إنشاء كائن لتوليد الأرقام العشوائية

        System.out.println("=== Sorting Algorithms Performance Benchmark (100,000 Elements) ==="); // طباعة عنوان البرنامج
        System.out.println("Press Enter to start running the benchmarks ..."); // طلب الضغط على إنتر لبدء الاختبار
        scanner.nextLine(); // انتظار ضغطة إنتر من المستخدم

        int[] originalRandom = new int[n]; // تعريف مصفوفة للعناصر العشوائية
        int[] originalReverse = new int[n]; // تعريف مصفوفة للعناصر مرتبة تنازلياً
        int[] originalPartially = new int[n]; // تعريف مصفوفة للعناصر مرتبة جزئياً
        int[] originalFully = new int[n]; // تعريف مصفوفة للعناصر مرتبة تصاعدياً بالكامل

        // حلقة لتعبئة المصفوفات الأربع بالبيانات حسب السيناريو المطلوب
        for (int i = 0; i < n; i++) {
            originalRandom[i] = random.nextInt(); // تعبئة المصفوفة العشوائية بأرقام عشوائية
            originalReverse[i] = n - i; // تعبئة المصفوفة بتنازلي من 100 ألف إلى 1
            originalFully[i] = i + 1; // تعبئة المصفوفة بترتيب تصاعدي كامل من 1 إلى 100 ألف
            
            // تعبئة 80% الأولى بترتيب تصاعدي والباقي بأرقام عشوائية لتكوين مرتبة جزئياً
            if (i < (int)(n * 0.8)) {
                originalPartially[i] = i + 1;
            } else {
                originalPartially[i] = random.nextInt();
            }
        }

        // طباعة رأس الجدول الخاص بعرض نتائج الأداء
        System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-13s | %-18s | %-18s | %-18s | %-18s |\n", "Algorithm", "Random", "Reversely Sorted", "Partially Sorted", "Fully Sorted");
        System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");

        // تشغيل اختبار الأداء لخوارزمية ترتيب الاختيار
        runBenchmark("Selection Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 1);

        // تشغيل اختبار الأداء لخوارزمية ترتيب الإدراج
        runBenchmark("Insertion Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 2);

        // تشغيل اختبار الأداء لخوارزمية الترتيب الفقاعي
        runBenchmark("Bubble Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 3);

        // تشغيل اختبار الأداء لخوارزمية الترتيب السريع
        runBenchmark("Quick Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 4);

        // تشغيل اختبار الأداء لخوارزمية ترتيب الدمج
        runBenchmark("Merge Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 5);

        // طباعة خط النهاية للجدول
        System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");

        scanner.close(); // إغلاق كائن المسح
    }

    // دالة لتنفيذ قياس الأداء لكل خوارزمية على الحالات الأربع للمصفوفة
    public static void runBenchmark(String name, int[] rand, int[] rev, int[] part, int[] full, int n, int algType) {
        double timeRandom = executeAlg(rand.clone(), n, algType); // قياس وقت الحالة العشوائية
        double timeReverse = executeAlg(rev.clone(), n, algType); // قياس وقت الحالة المعكوسة
        double timePartially = executeAlg(part.clone(), n, algType); // قياس وقت الحالة المرتبة جزئياً
        double timeFully = executeAlg(full.clone(), n, algType); // قياس وقت الحالة المرتبة بالكامل

        // طباعة النتائج في صف الجدول بتنسيق منظم
        System.out.printf("| %-13s | %-8.3f   ms | %-8.3f   ms | %-8.3f   ms | %-8.3f   ms |\n", 
                    name, timeRandom, timeReverse, timePartially, timeFully);
    }

    // دالة مساعدة لتنفيذ الخوارزمية المطلوبة وحساب الوقت المستغرق بالمللي ثانية
    public static double executeAlg(int[] arr, int n, int algType) {
        long start = System.nanoTime(); // تسجيل وقت البدء بالنانوثانية
        switch (algType) {
            case 1: selectionSort(arr); break; // تنفيذ خوارزمية الاختيار
            case 2: insertionSort(arr); break; // تنفيذ خوارزمية الإدراج
            case 3: bubbleSort(arr); break; // تنفيذ الخوارزمية الفقاعية
            case 4: quickSort(arr, 0, n - 1); break; // تنفيذ الخوارزمية السريعة
            case 5: mergeSort(arr, 0, n - 1); break; // تنفيذ خوارزمية الدمج
        }
        long end = System.nanoTime(); // تسجيل وقت الانتهاء
        return (end - start) / 1_000_000.0; // حساب الفرق وتحويله إلى مللي ثانية وإرجاعه
        
    }

    // تطبيق خوارزمية ترتيب الاختيار (Selection Sort)
    public static void selectionSort(int[] a) {
        int n = a.length; // تخزين طول المصفوفة
        for (int i = 0; i < n - 1; i++) { // حلقة خارجية لتحديد العنصر الحالي
            int minIdx = i; // افتراض أن العنصر الحالي هو الأصغر
            for (int j = i + 1; j < n; j++) { // حلقة دالية للبحث عن العنصر الأصغر في الجزء المتبقي
                if (a[j] < a[minIdx]) {
                    minIdx = j; // تحديث مؤشر العنصر الأصغر
                }
            }
            int temp = a[minIdx]; // تبديل العنصر الأصغر مع العنصر الحالي
            a[minIdx] = a[i];
            a[i] = temp;
        }
    }

    // تطبيق خوارزمية ترتيب الإدراج (Insertion Sort)
    public static void insertionSort(int[] a) {
        int n = a.length; // تخزين طول المصفوفة
        for (int i = 1; i < n; i++) { // البدء من العنصر الثاني حتى نهاية المصفوفة
            int key = a[i]; // حفظ القيمة الحالية المراد إدراجها في مكانها الصحيح
            int j = i - 1; // مؤشر العناصر التي تم فحصها سابقاً
            while (j >= 0 && a[j] > key) { // تحريك العناصر الأكبر إلى اليمين
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key; // إدراج العنصر في مكانه الصحيح
        }
    }

    // تطبيق خوارزمية الترتيب الفقاعي (Bubble Sort) مع خاصية التحقق من التبديل لتحسين الأداء
    public static void bubbleSort(int[] a) {
        int n = a.length; // تخزين طول المصفوفة
        boolean swapped; // متغير منطقي للتحقق هل حدث تبديل أم لا
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // إعادة تعيين المتغير في بداية كل دورة
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) { // مقارنة العناصر المتجاورة
                    int temp = a[j]; // تبديل العناصر إذا كانت غير مرتبة
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true; // الإشارة إلى أنه قد حدث تبديل
                }
            }
            if (!swapped) break; // إذا لم يحدث أي تبديل فهذا يعني أن المصفوفة مرتبة بالكامل فيتم الخروج مبكراً
        }
    }

    // تطبيق خوارزمية الترتيب السريع (Quick Sort)
    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(a, low, high); // تقسيم المصفوفة والحصول على موضع العنصر المحوري
            quickSort(a, low, pi - 1); // ترتيب القسم الأيسر
            quickSort(a, pi + 1, high); // ترتيب القسم الأيمن
        }
    }

    // دالة اختيار عنصر محوري عشوائي وتقسيم المصفوفة
    private static int randomizedPartition(int[] a, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1); // اختيار مؤشر عشوائي للمحور
        int temp = a[pivotIndex]; // تبديل العنصر العشوائي مع العنصر الأخير
        a[pivotIndex] = a[high];
        a[high] = temp;
        return partition(a, low, high); // استدعاء دالة التقسيم الأساسية
    }

    // دالة التقسيم الأساسية للترتيب السريع
    private static int partition(int[] a, int low, int high) {
        int pivot = a[high]; // اختيار العنصر الأخير كقيمة محورية
        int (i) = (low - 1); // مؤشر العناصر الأصغر من المحور
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i]; // تبديل العناصر لتكون الأصغر على اليسار
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1]; // وضع العنصر المحوري في مكانه الصحيح النهائي
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1; // إرجاع موضع المحور
    }

    // تطبيق خوارزمية ترتيب الدمج (Merge Sort) القائمة على التقسيم والحل
    public static void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; // إيجاد المنتصف
            mergeSort(a, l, m); // تقسيم وترتيب النصف الأيسر
            mergeSort(a, m + 1, r); // تقسيم وترتيب النصف الأيمن
            merge(a, l, m, r); // دمج النصفين المرتبين
        }
    }

    // دالة دمج الجزأين المرتبين في مصفوفة واحدة مترابطة
    public static void merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1; // حجم النصف الأيسر
        int n2 = r - m; // حجم النصف الأيمن

        int[] L = new int[n1]; // إنشاء مصفوفة مؤقتة لليسار
        int[] R = new int[n2]; // إنشاء مصفوفة مؤقتة لليمين

        for (int i = 0; i < n1; ++i)
            L[i] = a[l + i]; // نسخ بيانات النصف الأيسر
        for (int j = 0; j < n2; ++j)
            R[j] = a[m + 1 + j]; // نسخ بيانات النصف الأيمن

        int i = 0, j = 0;
        int k = l;

        // دمج العناصر بترتيب تصاعدي بين المصفوفتين المؤقتتين
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        // نسخ العناصر المتبقية من النصف الأيسر إن وجدت
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }

        // نسخ العناصر المتبقية من النصف الأيمن إن وجدت
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }
}
