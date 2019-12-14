import java.util.*;
import java.io.*;
class WordCount 
{
public static void main(String[] args) throws FileNotFoundException 
{
Scanner console = new Scanner(System.in);
System.out.print("What is the name of the text file? ");
String fileName = console.nextLine();
Scanner input = new Scanner(new File(fileName));
Map<Integer, Integer> wordCounts = new TreeMap<Integer, Integer>();
while (input.hasNext()) 
{
String next = input.next().toLowerCase();
int next1=next.length();
if (!wordCounts.containsKey(next1))
{
wordCounts.put(next1,1);
}
else
{
wordCounts.put(next1, wordCounts.get(next1) + 1);
}
}
System.out.println("Total words = " + wordCounts.size());
System.out.print("Minimum number of occurrences for printing? ");
int min = console.nextInt();
System.out.println("Length" + "\t" + "Word");
for (int word : wordCounts.keySet()) 
{
int count = wordCounts.get(word);
if (count >= min) 
{
System.out.println(word + "\t" + count);
}
}
}
}