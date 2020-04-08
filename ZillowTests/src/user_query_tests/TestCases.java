package user_query_tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestCases {

	  public TestCases() {
		  
	  }
	  

	public Map<Integer, List<Integer>> getTable(int lowerBoundary, int lowerLimit, int upperBoundary, int increment) {
		Map<Integer, List<Integer>> testCases = new HashMap<Integer, List<Integer>>();
		  for (int j = lowerBoundary; j <= lowerLimit; j += increment) {
			  List<Integer> setList = new ArrayList<Integer>();
			  for (int i = lowerBoundary; i < upperBoundary; i += increment) {
				  setList.add(i + increment);
				  
			  }
			  testCases.put(j, setList);
			  lowerBoundary = lowerBoundary + increment;
			  System.out.println(setList);
		  }
		  System.out.println(testCases);
		  return testCases;
	}
	
	public <T> List<List<T>> getTable(List<T> elements) {
		List<List<T>> testCases = new ArrayList<List<T>>();
	    for ( long i = 1; i < Math.pow(2, elements.size()); i++ ) {
	        List<T> list = new ArrayList<T>();
	        for ( int j = 0; j < elements.size(); j++ ) {
	            if ( (i & (long) Math.pow(2, j)) > 0 ) {
	                list.add(elements.get(j));
	            }
	        }
	        testCases.add(list);
	    }
	    return testCases;
	}
	
	
	  
}
