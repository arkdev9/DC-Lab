import java.util.*;  
class HashSet1{  
 public static void main(String args[])
 {  
    HashSet<String> set=new HashSet();  
    Scanner sc=new Scanner(System.in);
    int i;
    int n=sc.nextInt();
    for(i=0;i<n;i++)
    {
        String str=sc.next();
        set.add(str);
    }
    Iterator<String> j=set.iterator();
    while(j.hasNext())  
    {  
    System.out.println(j.next());  
    }  
 }  
}  