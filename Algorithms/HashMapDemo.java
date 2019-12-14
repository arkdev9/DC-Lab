import java.util.*;

class HashMapDemo {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n, i, j;
		String s1;
		n = in.nextInt();
		Map<Integer, String> hm = new HashMap<Integer, String>();
		for (i = 1; i <= n; i++) {
			j = in.nextInt();
			s1 = in.next();
			hm.put(new Integer(j), s1);
		}

		Set<Map.Entry<Integer, String>> st = hm.entrySet();

		for (Map.Entry<Integer, String> me : st) {
			System.out.print(me.getKey() + ":");
			System.out.println(me.getValue());
		}
	}
}