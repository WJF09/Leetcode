import java.util.*;

public class Test {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        int[] ans = new int[queries.length];
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> diag1 = new HashMap<>();
        Map<Integer, Integer> diag2 = new HashMap<>();
        Set<Long> set = new HashSet<>();
        for (int[] lamp : lamps) {
            if (!set.add(hash(lamp[0], lamp[1]))) {
                continue;
            }
            row.put(lamp[0], row.getOrDefault(lamp[0], 0) + 1);
            col.put(lamp[1], col.getOrDefault(lamp[1], 0) + 1);
            diag1.put(lamp[0] - lamp[1], diag1.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            diag2.put(lamp[0] + lamp[1], diag2.getOrDefault(lamp[0] + lamp[1], 0) + 1);
        }
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int r = query[0];
            int c = query[1];
            if (row.getOrDefault(r,0) > 0) {
                ans[i] = 1;
            } else if (col.getOrDefault(c,0) > 0) {
                ans[i] = 1;
            } else if (diag1.getOrDefault(r - c,0) > 0) {
                ans[i] = 1;
            } else if (diag2.getOrDefault(r + c,0) > 0) {
                ans[i] = 1;
            }
            //关灯
            for (int x = r - 1; x <= r + 1; x++) {
                for (int y = c - 1; y <= c + 1; y++) {
                    if (x < 0 || x >= n || y < 0 || y >= n) {
                        continue;
                    }
                    if (set.remove(hash(x, y))) {
                        row.put(x, row.get(x) - 1);
                        if (row.get(x) == 0) {
                            row.remove(x);
                        }
                        col.put(y, col.get(y) - 1);
                        if (col.get(y) == 0) {
                            col.remove(y);
                        }
                        diag1.put(x - y, diag1.get(x-y) - 1);
                        if (diag1.get(x - y) == 0) {
                            diag1.remove(x - y);
                        }
                        diag2.put(x + y, diag2.get(x+y) - 1);
                        if (diag2.get(x + y) == 0) {
                            diag2.remove(x + y);
                        }
                    }
                }
            }
        }

        return ans;
    }

    public long hash(int i, int j) {
        return (long) i + ((long) j << 32);
    }
}
