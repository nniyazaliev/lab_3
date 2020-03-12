import java.util.Arrays;
import java.util.Random;

public class SortingExperiments {
    public static void main(String[] args) {
        Integer[] arr = new Integer[10000];
        Integer[] arr2 = new Integer[10000];

        fillArray(arr);
        fillArray(arr2);

        long start = System.nanoTime();
        sortCycle(arr);
        System.out.println("Время цикличной сортировки: " + (System.nanoTime() - start) + " нано секунд");

        start = System.nanoTime();
        Arrays.sort(arr2);
        System.out.println("Время Array.sort(): " + (System.nanoTime() - start) + " нано секунд");

        System.out.println("Cycle Sort: " + Arrays.toString(arr));
    }

    public static Integer[] fillArray(Integer[] arr) {
        Random r = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(100);
        }

        return arr;
    }

    public static Integer[] sortCycle(Integer[] arr) {
        for (int cycleStart = 0; cycleStart < arr.length - 1; cycleStart++) {
            int value = arr[cycleStart];

            int position = cycleStart;
            for (int i = cycleStart + 1; i < arr.length; i++) {
                if (arr[i] < value) {
                    position++;
                }
            }

            if (position == cycleStart) {
                continue;
            }

            while (value == arr[position]) {
                position++;
            }

            int tmp = arr[position];
            arr[position] = value;
            value = tmp;

            while (position != cycleStart) {
                position = cycleStart;
                for (int i = cycleStart + 1; i < arr.length; i++) {
                    if (arr[i] < value) {
                        position++;
                    }
                }

                while (value == arr[position]) {
                    position++;
                }

                tmp = arr[position];
                arr[position] = value;
                value = tmp;
            }
        }

        return arr;
    }
}
