public class MysetTester{
	private static Myset set1= new Myset();
	private static Myset set2= new Myset();
	private static String[] tester = {"a","b","c","d","e"};
	

	public static void main(String argc[]){
		set1.Insert(tester[4]);
		set1.Insert(tester[2]);
		set1.Insert(tester[0]);
		set2.Insert(tester[1]);
		set2.Insert(tester[2]);
		set2.Insert(tester[3]);
		// try{
		// 	set.Delete(tester[4]);
		// }catch(Exception e){
		// 	System.out.print("hyo");
		// }

		(set1.Union(set2)).tester();
		(set1.Intersection(set2)).tester();

		// try{
		// 	set.Delete("a");
		// }catch(Exception e){
		// 	System.out.print("heyo");
		// }

	}
}