public class ArrayDequeTest {
  /**
   * Performs some basic linked list tests.
   */
  /* Utility method for printing out empty checks. */
  public static boolean checkEmpty(boolean expected, boolean actual) {
    if (expected != actual) {
      System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
      return false;
    }
    return true;
  }

  /* Utility method for printing out empty checks. */
  public static boolean checkSize(int expected, int actual) {
    if (expected != actual) {
      System.out.println("size() returned " + actual + ", but expected: " + expected);
      return false;
    }
    return true;
  }

  public static boolean checkGetRecursive(String expected, String actual) {
    if (expected.equals(actual) == false) {
      System.out.println("Get recurisve() returned " + actual + ", but expected: " + expected);
      return false;
    }
    return true;
  }

  public static boolean checkGet(String expected, String actual) {
    if (expected.equals(actual) == false) {
      System.out.println("Get() returned " + actual + ", but expected: " + expected);
      return false;
    }
    return true;
  }

  /* Prints a nice message based on whether a test passed.
   * The \n means newline. */
  public static void printTestStatus(boolean passed) {
    if (passed) {
      System.out.println("Test passed!\n");
    } else {
      System.out.println("Test failed!\n");
    }
  }

  /**
   * Adds a few things to the list, checking isEmpty() and size() are correct,
   * finally printing the results.
   * <p>
   * && is the "and" operation.
   */
  public static void addIsEmptySizeTest() {
    System.out.println("Running add/isEmpty/Size test.");

    ArrayDeque<String> lld1 = new ArrayDeque<String>();

    boolean passed = checkEmpty(true, lld1.isEmpty());

    lld1.addFirst("front");

    // The && operator is the same as "and" in Python.
    // It's a binary operator that returns true if both arguments true, and false otherwise.
    passed = checkSize(1, lld1.size()) && passed;
    passed = checkEmpty(false, lld1.isEmpty()) && passed;

    lld1.addLast("middle");
    passed = checkSize(2, lld1.size()) && passed;

    lld1.addLast("back");
    passed = checkSize(3, lld1.size()) && passed;

    System.out.println("Printing out deque: ");
    lld1.printDeque();

    printTestStatus(passed);
  }

  /**
   * Adds an item, then removes an item, and ensures that dll is empty afterwards.
   */
  public static void addRemoveTest() {

    System.out.println("Running add/remove test.");

    ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
    // should be empty
    boolean passed = checkEmpty(true, lld1.isEmpty());

    lld1.addFirst(10);
    // should not be empty
    passed = checkEmpty(false, lld1.isEmpty()) && passed;

    lld1.removeFirst();
    // should be empty
    passed = checkEmpty(true, lld1.isEmpty()) && passed;

    printTestStatus(passed);
  }


  public static void getTest() {
    System.out.println("Running get test.");

    ArrayDeque<String> lld1 = new ArrayDeque<String>();

    boolean passed = checkEmpty(true, lld1.isEmpty());

    lld1.addFirst("front1");
    passed = checkGet("front1", lld1.get(0)) && passed;

    lld1.addFirst("front2");
    passed = checkGet("front2", lld1.get(0)) && passed;

    lld1.addLast("middle");
    passed = checkGet("middle", lld1.get(2)) && passed;

    lld1.addLast("back1");
    passed = checkGet("back1", lld1.get(3)) && passed;

    lld1.addLast("back2");
    passed = checkGet("back2", lld1.get(4)) && passed;

    System.out.println("Printing out deque: ");
    lld1.printDeque();

    printTestStatus(passed);
  }

  public static void testExpand() {
    System.out.println("Running expand test.");

    ArrayDeque<String> lld1 = new ArrayDeque<String>();

    boolean passed = checkEmpty(true, lld1.isEmpty());

    for(int i = 0; i < 10000; i++){
      lld1.addLast("e");
    }
    passed = checkSize(10000, lld1.size()) && passed;

    for(int i = 0; i < 10000; i++){
      lld1.addFirst("e");
    }
    passed = checkSize(20000, lld1.size()) && passed;

    System.out.println("Printing out deque: ");

    for(int i = 0 ; i < 19999; i++) {
      lld1.removeLast();
    }
    passed = checkSize(1, lld1.size()) && passed;

    lld1.printDeque();

    printTestStatus(passed);
  }


  public static void main(String[] args) {
    System.out.println("Running tests.\n");
    addIsEmptySizeTest();
    addRemoveTest();
    getTest();
    testExpand();
  }
}
