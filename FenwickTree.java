
public class FenwickTree {

    public long[] a;

    public FenwickTree(int length) {
        a= new long[length];
    }

    public void update(int index, long val) {
        while (index < a.length) {
            a[index] =a[index]+ val;
            index = index | (index + 1);
        }
    }

    public long get(int r) {
        if(r == -1)
            return 0;
        long res = 0;
        while (r >= 0) {
            res =a[r]+ res;
            r = (r & (r + 1)) - 1;
        }
        return res;
    }
}
