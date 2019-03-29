import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class seriesSet {

    static Map<Long, Integer> sets = new HashMap<>();
    static Map<Long, Long> fact = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();


        for (int i = 0; i < tests; i++) {
            int m = scanner.nextInt();
            int d = scanner.nextInt();
            addDataIfValid(d, m);
        }

        long result = 0;

        Set<Long> keySet = sets.keySet();

        for (Long key : keySet) {
            Integer integer = sets.get(key);
            result = result + combinationsFind(integer, 2);

        }
        System.out.println(result);

    }

    private static long combinationsFind(int n, int r) {
        if (n < r) return 0;
        return (simpleFact(n).divide(simpleFact(r).multiply(simpleFact(n - r)))).longValue();
    }

    private static void addDataIfValid(Integer d, Integer m) {
        if (m == 0 || m == 1) return;

        int base = m;
        char[] chars = d.toString().toCharArray();

        for (char aChar : chars) {
            if (Integer.parseInt(aChar + "") >= base) {
                return;
            }
        }
        long l = binaryToInt(d, base);

        Integer setsOrDefault = sets.getOrDefault(l, 0);
        if (setsOrDefault == 0) sets.put(l, 1);
        else sets.put(l, setsOrDefault + 1);

    }

    private static long binaryToInt(long value, int base) {
        String n = value + "";
        long result = 0;

        char[] chars = new StringBuffer(n).reverse().toString().toCharArray();


        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            result += Integer.parseInt(aChar + "") * Math.pow(base, i);
        }

        return result;
    }

    private static long convertToBinary(long value, int base) {
        long n = value;

        String result = "";
        while (n != 0) {
            result = (n % base) + result;
            n = n / base;
        }
        return Long.parseLong(result);

    }


    private static long gcdOfValues(long[] arr) {

        long result = arr[0];

        for (int i = 1; i < arr.length; i++) {
            result = gcd(result, arr[i]);
        }
        return result;
    }

    static long directFibOfN(long n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;

        if ((n & 1) == 1) {
            long newN = n / 2;
            System.out.println("n=" + n + ": odd");
            return (long) ((long) Math.pow(directFibOfN(newN), 2) + Math.pow(directFibOfN(newN + 1), 2));
        } else {
            System.out.println("n=" + n + ": even");
            long newN = n / 2;
            return (2 * directFibOfN(newN - 1) + directFibOfN(newN)) * directFibOfN(newN);
        }


    }

    static long fiboA(long a, long b, long n) {
        if (n == 0) return a;
        else if (n == 1) return b;
        long c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;

            a = b;
            b = c;

        }
        return c % 10;

    }

    private static long gcd(long a, long b) {

        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static long fact(long x) {
        if (x == 1) return 1;
        Long orDefault = fact.getOrDefault(x, 0L);
        if (orDefault > 0) return orDefault;
        else {
            long l = x * fact(x - 1);
            fact.put(x, l);
            return l;
        }
    }

    private static BigInteger simpleFact(long x) {

        BigInteger result = BigInteger.ONE;
        for (int i = 1; i < x; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
