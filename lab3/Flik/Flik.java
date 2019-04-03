/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // Integer is object wrapper for int,
        // so equals should be used instead of == for equality
        return a.equals(b);
    }
}
