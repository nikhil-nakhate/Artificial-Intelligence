import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateWinningLeafNodes {

	public static List<ArrayList<Integer>> getWinningLeafStateIndexesList (){
	
		ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
		
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(0, 5, 10));
		List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		List<Integer> list4 = new ArrayList<Integer>(Arrays.asList(1, 5, 9));
		List<Integer> list5 = new ArrayList<Integer>(Arrays.asList(1, 6, 11));
		List<Integer> list6 = new ArrayList<Integer>(Arrays.asList(2, 5, 8));
		List<Integer> list7 = new ArrayList<Integer>(Arrays.asList(2, 6, 10));
		List<Integer> list8 = new ArrayList<Integer>(Arrays.asList(3, 6, 9));
		List<Integer> list9 = new ArrayList<Integer>(Arrays.asList(3, 7, 11));
		List<Integer> list10 = new ArrayList<Integer>(Arrays.asList(5, 6, 7));
		List<Integer> list11 = new ArrayList<Integer>(Arrays.asList(8, 9, 10));
		List<Integer> list12 = new ArrayList<Integer>(Arrays.asList(9, 10, 11));
	
		mainList.add((ArrayList<Integer>)list1);
		mainList.add((ArrayList<Integer>)list2);
		mainList.add((ArrayList<Integer>)list3);
		mainList.add((ArrayList<Integer>)list4);
		mainList.add((ArrayList<Integer>)list5);
		mainList.add((ArrayList<Integer>)list6);
		mainList.add((ArrayList<Integer>)list7);
		mainList.add((ArrayList<Integer>)list8);
		mainList.add((ArrayList<Integer>)list9);
		mainList.add((ArrayList<Integer>)list10);
		mainList.add((ArrayList<Integer>)list11);
		mainList.add((ArrayList<Integer>)list12);
		
	//list1.containsAll(list2);    //if list2 is a part of list1

	
	return mainList;
	
	}
	
}
