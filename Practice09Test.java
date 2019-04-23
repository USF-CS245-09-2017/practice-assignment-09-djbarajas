import java.util.Random;


public class Practice09Test {
	
	private BinaryHeap heap;  // This is the class you'll implement.
	
	private int test2Size;
	private int test3Size;
	private int test4Size;
	
	
	/**
	 * Constructor: Instantiate the heap and establish the sizes for the tests.
	 */
	public Practice09Test() {
		heap = new BinaryHeap();
		test2Size = 10;
		test3Size = 1000;
		test4Size = 100000;
	}

	
	/**
	 * Basic test: insert a number then remove it and check that it's the same one.
	 * @return true on success; false otherwise.
	 */
	public boolean insertRemoveTest() {
		heap.add(1);
		if (heap.remove() == 1) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Advanced test: insert a few random numbers and ensure that the minimum is returned.
	 * @return true on success; false otherwise.
	 */
	public boolean insertMultipleTest() {
		Random random = new Random();
		int bound = 1000;
		int minimum = random.nextInt(bound);
		int entered = 0;
		
		boolean correct = true;
		
		// Enter some random numbers. Keep track of the minimum and number entered.
		for (int i = 0; i < test2Size; i++) {
			int n = random.nextInt(bound);
			heap.add(n);
			if (i == 0 || n < minimum)
				minimum = n;
			++entered;
		}
		
		// The first element out should be the minimum.
		if (minimum != heap.remove()) {
			correct = false;
		}
		--entered;
		
		for (int i = 1; i < entered; i++) {
			int n = heap.remove();
			if (n > bound) {
				correct = false;
			}
		}
		
		return correct;
	}
	
	
	/**
	 * Full ordering test: insert a few numbers and ensure they are globally in order.
	 * @return true on success; false otherwise.
	 */
	public boolean fullOrderingTest() {
		Random random = new Random();
		boolean correct = true;
		
		// Enter some random numbers. Keep track of the minimum and number entered.
		for (int i = 0; i < test3Size; i++) {
			heap.add(random.nextInt());
		}
		
		// The first element out should be the minimum.
		int less = heap.remove();
		
		// Given the minimum, every other element should have a value larger than the previous
		for (int i = 1; i < test3Size; i++) {
			int current = heap.remove();
			if (current < less) {
				correct = false;
			}
			less = current;
		}
		
		return correct;
	}
	
	
	/**
	 * Timing test: insert a few numbers, remove them... and keep the overall timing.
	 * Median timing is 10ms on modern (somewhat underpowered) machines. 15ms or greater is an outlier.
	 * This could indicate an incorrect design for the heap.
	 * @return true if timing is within norms; false otherwise
	 */
	public boolean timingTest() {
		long start = System.currentTimeMillis();
		Random random = new Random();
		
		// Enter some random numbers. Keep track of the minimum and number entered.
		for (int i = 0; i < test4Size; i++) {
			heap.add(random.nextInt());
		}
		long end = System.currentTimeMillis();
		for (int i = 0; i < test4Size; i++) {
			heap.remove();
		}
		// This should take ~10-15ms on a modern-ish computer. More means the implementation could be flawed.
		if (end-start > 20)
			return false;
		return true;
	}

	
	/**
	 * Runs all tests above. Assigns point values to each test and prints the values to stdout.
	 */
	public void runTests() {
		int grade = 0;
		
		// Test 1: basic "insert and remove"
		try {
			if (insertRemoveTest()) {
				grade += 20;
				System.out.println("[20%] = Passed basic insert / remove test.");
			} else {
				throw new Exception("Fails");
			}
		} catch (Exception e) {
			System.out.println("[   ] = Failed basic insert / remove test.");
		}
		
		// Test 2: advanced "insert and retrieve" (is it a minheap?)
		try {
			if (insertMultipleTest()) {
				grade += 30;
				System.out.println("[30%] = Passed advanced insert / remove test.");
			} else {
				throw new Exception("Fails");
			}
		} catch (Exception e) {
			System.out.println("[   ] = Failed advanced insert / remove test.");
		}
		
		// Test 3: full ordering test (always rearranges to a minheap?)
		try {
			if (fullOrderingTest()) {
				grade += 40;
				System.out.println("[40%] = Passed full ordering test.");
			} else {
				throw new Exception("Fails");
			}
		} catch (Exception e) {
			System.out.println("[   ] = Failed full ordering test.");
		}
		
		// Test 4: timing test (should be under ~ 20ms)
		try {
			if (timingTest()) {
				grade += 10;
				System.out.println("[10%] = Passed timing test.");
			} else {
				throw new Exception("Fails");
			}
		} catch (Exception e) {
			System.out.println("[   ] = Timing looks suspicious. (The grader will review for performance.)");
		}
		
		timingTest();
		
		System.out.println("-----------------------------------------");
		System.out.println("Starting point for this assignment: " + grade + "%.");
	}


	public static void main(String[] args) {
		Practice09Test test = new Practice09Test();
		test.runTests();
	}/*
    private static boolean test(BinaryHeap heap, int expect, int flag) {
        int ele = heap.remove();
        if (ele != expect) {
            System.out.println("Incorrect!!! ---- actual: " + ele + "\texpected: " + expect);
            return false;
        }
        return true;
    }

    private static boolean testException(BinaryHeap heap, int flag) {
        try {
            heap.remove();
            System.out.println("!!! FAIL to throw exception \t flag - " + flag + "!!!");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("--- pass test exception \t flag - " + flag + "---");
            return true;
        }
    }

    public static void main(String[] args) {
        boolean pass = true;
        int flag = -1;
        BinaryHeap heap = new BinaryHeap();
        int[][] a = new int[][]{
                {1},
                {20, 45, 26, 42, 51, 10, 22, 40, 30, 52, 50, 28},
                {3, 1},
                {2, 3, 4, 5, 1, 6}
        };
        for (int x = 0; x < a.length; x++) {
            flag = x;
            int[] temp = a[x];
            for (int t : temp) {
                heap.add(t);
            }
            int[] copy = new int[temp.length];
            System.arraycopy(temp, 0, copy, 0, temp.length);
            Arrays.sort(copy);
            for (int i = 0; i < copy.length; i++) {
                pass = pass && test(heap, copy[i], flag);
            }
            pass = pass && testException(heap, flag);
        }

        int[] b = new int[]{12, 11, 10, 9};
        for (int k : b) {
            heap.add(k);
        }
        flag = 100;
        pass = pass && test(heap, 9, flag);
        flag = 101;
        pass = pass && test(heap, 10, flag);

        b = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0};
        for (int k : b) {
            heap.add(k);
        }
        b = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12};
        for (int i = 0; i < b.length; i++) {
            flag = 200 + i;
            pass = pass && test(heap, b[i], flag);
        }
        pass = pass && testException(heap, flag);
        System.out.println(pass ? "---PASS---" : "FAIL!!!");
    }*/
}
