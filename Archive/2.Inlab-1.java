import java.util.*;

public class Lab1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the search word:");
		String str1 = sc.next();
		String str;
		int i, count = 0;
		for (i = 0; i < 6; i++) {
			str = sc.next();
			if (str.equals(str1)) {
				count++;
			}
		}
		System.out.println(" The word maven occured" + count + "times");
	}
}
