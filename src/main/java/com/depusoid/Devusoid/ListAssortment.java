package com.depusoid.Devusoid;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListAssortment {
	Main m = new Main();
	
	//Big Decimal List from giza database
	List<String> queryCompanyArray = new ArrayList<String>(
			Arrays.asList("canadiansolar", "firstsolar", "jinkosolar", "nexteraenergy", "sunpower", "tesla"));
	List<BigDecimal> BDRevCanadianSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDRevFirstSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDRevJinkoSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDRevNexteraEnergy = new ArrayList<BigDecimal>();
	List<BigDecimal> BDRevSunpower = new ArrayList<BigDecimal>();
	List<BigDecimal> BDRevTesla = new ArrayList<BigDecimal>();

	List<BigDecimal> BDCogsCanadianSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDCogsFirstSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDCogsJinkoSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDCogsNexteraEnergy = new ArrayList<BigDecimal>();
	List<BigDecimal> BDCogsSunpower = new ArrayList<BigDecimal>();
	List<BigDecimal> BDCogsTesla = new ArrayList<BigDecimal>();

	List<BigDecimal> BDDeprecCanadianSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDDeprecFirstSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDDeprecJinkoSolar = new ArrayList<BigDecimal>();
	List<BigDecimal> BDDeprecNexteraEnergy = new ArrayList<BigDecimal>();
	List<BigDecimal> BDDeprecSunpower = new ArrayList<BigDecimal>();
	List<BigDecimal> BDDeprecTesla = new ArrayList<BigDecimal>();
	
	
	//Double replacement
	List<Double> RevCanadianSolar = new ArrayList<Double>();
	List<Double> RevFirstSolar = new ArrayList<Double>();
	List<Double> RevJinkoSolar = new ArrayList<Double>();
	List<Double> RevNexteraEnergy = new ArrayList<Double>();
	List<Double> RevSunpower = new ArrayList<Double>();
	List<Double> RevTesla = new ArrayList<Double>();

	List<Double> CogsCanadianSolar = new ArrayList<Double>();
	List<Double> CogsFirstSolar = new ArrayList<Double>();
	List<Double> CogsJinkoSolar = new ArrayList<Double>();
	List<Double> CogsNexteraEnergy = new ArrayList<Double>();
	List<Double> CogsSunpower = new ArrayList<Double>();
	List<Double> CogsTesla = new ArrayList<Double>();
	
	List<Double> DeprecCanadianSolar = new ArrayList<Double>();
	List<Double> DeprecFirstSolar = new ArrayList<Double>();
	List<Double> DeprecJinkoSolar = new ArrayList<Double>();
	List<Double> DeprecNexteraEnergy = new ArrayList<Double>();
	List<Double> DeprecSunpower = new ArrayList<Double>();
	List<Double> DeprecTesla = new ArrayList<Double>();
	
	//method to convert from Big Decimal to Double
	void convertToDouble() {
	for (int i = 0; i < BDRevCanadianSolar.size(); i++) {
		BigDecimal bdRevCanadianSolar = BDRevCanadianSolar.get(i);
		BigDecimal bdRevFirstSolar = BDRevFirstSolar.get(i);
		BigDecimal bdRevJinkoSolar = BDRevJinkoSolar.get(i);
		BigDecimal bdRevNexteraEnergy = BDRevNexteraEnergy.get(i);
		BigDecimal bdRevSunpower = BDRevSunpower.get(i);
		BigDecimal bdRevTesla = BDRevTesla.get(i);

		BigDecimal bdCogsCanadianSolar = BDCogsCanadianSolar.get(i);
		BigDecimal bdCogsFirstSolar = BDDeprecFirstSolar.get(i);
		BigDecimal bdCogsJinkoSolar = BDDeprecJinkoSolar.get(i);
		BigDecimal bdCogsNexteraEnergy = BDDeprecNexteraEnergy.get(i);
		BigDecimal bdCogsSunpower = BDDeprecSunpower.get(i);
		BigDecimal bdCogsTesla = BDDeprecTesla.get(i);
		
		BigDecimal bdDeprecCanadianSolar = BDDeprecCanadianSolar.get(i);
		BigDecimal bdDeprecFirstSolar = BDDeprecFirstSolar.get(i);
		BigDecimal bdDeprecJinkoSolar = BDDeprecJinkoSolar.get(i);
		BigDecimal bdDeprecNexteraEnergy = BDDeprecNexteraEnergy.get(i);
		BigDecimal bdDeprecSunpower = BDDeprecSunpower.get(i);
		BigDecimal bdDeprecTesla = BDDeprecTesla.get(i);
		
		//convert from BigDecimal to Double value
		Double r0 = bdRevCanadianSolar.doubleValue();
		Double r1 = bdRevFirstSolar.doubleValue();
		Double r2 = bdRevJinkoSolar.doubleValue();
		Double r3 = bdRevNexteraEnergy.doubleValue();
		Double r4 = bdRevSunpower.doubleValue();
		Double r5 = bdRevTesla.doubleValue();
		
		Double c0 = bdCogsCanadianSolar.doubleValue();
		Double c1 = bdCogsFirstSolar.doubleValue();
		Double c2 = bdCogsJinkoSolar.doubleValue();
		Double c3 = bdCogsNexteraEnergy.doubleValue();
		Double c4 = bdCogsSunpower.doubleValue();
		Double c5 = bdCogsTesla.doubleValue();
		
		Double d0 = bdDeprecCanadianSolar.doubleValue();
		Double d1 = bdDeprecFirstSolar.doubleValue();
		Double d2 = bdDeprecJinkoSolar.doubleValue();
		Double d3 = bdDeprecNexteraEnergy.doubleValue();
		Double d4 = bdDeprecSunpower.doubleValue();
		Double d5 = bdDeprecTesla.doubleValue();
		
		RevCanadianSolar.add(r0);
		RevFirstSolar.add(r1);
		RevJinkoSolar.add(r2);
		RevNexteraEnergy.add(r3);
		RevSunpower.add(r4);
		RevTesla.add(r5);
		
		CogsCanadianSolar.add(c0);
		CogsFirstSolar.add(c1);
		CogsJinkoSolar.add(c2);
		CogsNexteraEnergy.add(c3);
		CogsSunpower.add(c4);
		CogsTesla.add(c5);
		
		DeprecCanadianSolar.add(d0);
		DeprecFirstSolar.add(d1);
		DeprecJinkoSolar.add(d2);
		DeprecNexteraEnergy.add(d3);
		DeprecSunpower.add(d4);
		DeprecTesla.add(d5);
	}
	}
	String cs = "SELECT Depreciation FROM canadiansolar WHERE ID = 10";
	String fs = "SELECT Depreciation FROM firstsolar WHERE ID = 10";
	String js = "SELECT Depreciation FROM jinkosolar WHERE ID = 10";
	String ne = "SELECT Depreciation FROM nexteraenergy WHERE ID = 10";
	String sp = "SELECT Depreciation FROM sunpower WHERE ID = 10";
	String t = "SELECT Depreciation FROM tesla WHERE ID = 10";

	String csR = "SELECT Revenue FROM canadiansolar WHERE ID = 10";
	String fsR = "SELECT Revenue FROM firstsolar WHERE ID = 10";
	String jsR = "SELECT Revenue FROM jinkosolar WHERE ID = 10";
	String neR = "SELECT Revenue FROM nexteraenergy WHERE ID = 10";
	String spR = "SELECT Revenue FROM sunpower WHERE ID = 10";
	String tR = "SELECT Revenue FROM tesla WHERE ID = 10";
	
	Set<Integer> canadiansolarBigSet = new HashSet<>();
	Set<Integer> firstsolarBigSet = new HashSet<>();
	Set<Integer> jinkosolarBigSet = new HashSet<>();
	Set<Integer> nexteraenergyBigSet = new HashSet<>();
	Set<Integer> sunpowerBigSet = new HashSet<>();
	Set<Integer> teslaBigSet = new HashSet<>();
	
	
	
	List<Integer> canadiansolarSmallSetConvert = new ArrayList<>();
	List<Integer> firstsolarSmallSetConvert = new ArrayList<>();
	List<Integer> jinkosolarSmallSetConvert = new ArrayList<>();
	List<Integer> nexteraenergySmallSetConvert = new ArrayList<>();
	List<Integer> sunpowerSmallSetConvert = new ArrayList<>();
	List<Integer> teslaSmallSetConvert = new ArrayList<>();
	
	Set<Integer> canadiansolarSmallSet = new HashSet<>();
	Set<Integer> firstsolarSmallSet = new HashSet<>();
	Set<Integer> jinkosolarSmallSet = new HashSet<>();
	Set<Integer> nexteraenergySmallSet = new HashSet<>();
	Set<Integer> sunpowerSmallSet = new HashSet<>();
	Set<Integer> teslaSmallSet = new HashSet<>();

	// CanadianSolar
	/*ResultSet Resultcs = statement.executeQuery(cs);
	Resultcs.next();
	BigDecimal Decimalcs = Resultcs.getBigDecimal("Depreciation");
	depreciationDist.put("Canadian_Solar", Decimalcs);
	*/
	
	
	
	
}
