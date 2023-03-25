package com.depusoid.Devusoid;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConversionMethods {

	Set<Integer> returnSetFromCompanyColumn(List<Integer> list) {
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				for (int x = 0; x < list.size(); x++) {
					if (list.get(i) == list.get(j) && list.get(j) == list.get(x) && (i != j) && (j != x) && (i != x)) {
						set.add(i);
						set.add(j);
						set.add(x);
					}
				}
			}
		}
		return set;
	}
	
	List<Integer> returnSmallListFromSorted(List<Integer> oldList, List<Integer> demandClicks) {
		List<Integer> newList = new ArrayList<>();
		for (int i = 0; i < oldList.size(); i++) {
			
			newList.add(demandClicks.get(oldList.get(i)));
		}
		return newList;
	}
	
	
	
	
	/*for (int i = 0; i < shortlistCanadianSolar.size(); i++) {
		String stringID = "SELECT COUNT(userID) FROM user_interest WHERE canadiansolar = " + shortlistCanadianSolar.get(i) + ";";
		ResultSet result = statement.executeQuery(stringID);
		result.next();
		int getCount = result.getInt(1);
		IDcountcanadiansolar.add(getCount);
	}
	*/
}
