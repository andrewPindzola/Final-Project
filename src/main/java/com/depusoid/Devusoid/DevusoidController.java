package com.depusoid.Devusoid;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@RestController

public class DevusoidController {
	List<Double> userClicksControl = new ArrayList<Double>();
	HashMap<String,Integer> map = new HashMap<String,Integer>();
	
	//Hashmaps
	HashMap<Integer,Double> canadiansolarMap = new HashMap<Integer, Double>();
	HashMap<Integer,Double> firstsolarMap = new HashMap<Integer, Double>();
	HashMap<Integer,Double> jinkosolarMap = new HashMap<Integer, Double>();
	HashMap<Integer,Double> nexteraenergyMap = new HashMap<Integer, Double>();
	HashMap<Integer,Double> sunpowerMap = new HashMap<Integer, Double>();
	HashMap<Integer,Double> teslaMap = new HashMap<Integer, Double>();
	
	@DeleteMapping("/Delete Name by ID")
	public String deletename(@RequestBody int ID) {
		Scanner s = new Scanner(System.in);
		Connection connection = null;
		try(s) {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("delete from user_interest where userID = " + ID + "");
			
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ID + " has been deleted";
		}
	
	
	@PostMapping("/Get Name by ID")
	public String getname(@RequestBody int ID) {
		Scanner s = new Scanner(System.in);
		Connection connection = null;
		String firstname = null;
		String lastname = null;
		
		try(s) {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
			
			String getfirstId = "SELECT firstname FROM user_interest WHERE userID = " + ID;
			ResultSet getfirstResult = statement.executeQuery(getfirstId);
			getfirstResult.next();
			firstname = getfirstResult.getString(1);
			String getlastId = "SELECT lastname FROM user_interest WHERE userID = " + ID;
			ResultSet getlastResult = statement.executeQuery(getlastId);
			getlastResult.next();
			lastname = getlastResult.getString(1);
			System.out.println(firstname);
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstname + " " + lastname;
	}
	
	@GetMapping("/Get Price Differential(s) for CanadianSolar HashMap<ConsumerID,Price Percentage>")
	public HashMap<Integer,Double> getcanadiansolarMap() {
		return canadiansolarMap;
	}
	@GetMapping("/Get Price Differential(s) for FirstSolar HashMap<ConsumerID,Price Percentage>")
	public HashMap<Integer,Double> getfirstsolarMap() {
		return firstsolarMap;
	}
	@GetMapping("/Get Price Differential(s) for JinkoSolar HashMap<ConsumerID,Price Percentage>")
	public HashMap<Integer,Double> getjinkosolarMap() {
		return jinkosolarMap;
	}
	@GetMapping("/Get Price Differential(s) for Nextera Energy HashMap<ConsumerID,Price Percentage>")
	public HashMap<Integer,Double> getnexteraenergyMap() {
		return nexteraenergyMap;
	}
	@GetMapping("/Get Price Differential(s) for Sunpower HashMap<ConsumerID,Price Percentage>")
	public HashMap<Integer,Double> getsunpowerMap() {
		return sunpowerMap;
	}
	@GetMapping("/Get Price Differential(s) for Tesla HashMap<ConsumerID,Price Percentage>")
	public HashMap<Integer,Double> getteslaMap() {
		return teslaMap;
	}
	
	@GetMapping("/Calculate Price Differentials w/ Existing Data")
	public void getlist() {
		Main main = new Main();
		Regression regression = new Regression();
		ListAssortment la = new ListAssortment();
		ConversionMethods cm = new ConversionMethods();
		Scanner s = new Scanner(System.in);
		Connection connection = null;
		
		try (s) {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
			
			String count = "SELECT COUNT(userID) FROM user_interest;";
			ResultSet countResult = statement.executeQuery(count);
			
			countResult.next();
			int countTotal = countResult.getInt(1);
			System.out.println(countTotal + " is the total number of users in the database");
				
						
			for (int ia = 0; ia < la.queryCompanyArray.size(); ia++) {
				String getCompanyClickColumn = "SELECT " + la.queryCompanyArray.get(ia) + " FROM user_interest;";
				ResultSet companyClickResult = statement.executeQuery(getCompanyClickColumn);
				
				
					for (int ib = 0; ib < countTotal; ib++) {
						companyClickResult.next();
						int clickValue = companyClickResult.getInt(1);
						if (ia == 0) {
							main.canadianSolarClicks.add(clickValue);
						} else if (ia == 1) {
							main.firstSolarClicks.add(clickValue);
						} else if (ia == 2) {
							main.jinkoSolarClicks.add(clickValue);
						} else if (ia == 3) {
							main.nexteraEnergyClicks.add(clickValue);
						} else if (ia == 4) {
							main.sunpowerClicks.add(clickValue);
						} else if (ia == 5) {
							main.teslaClicks.add(clickValue);
						}
						
					}
			}
			
			System.out.println(main.canadianSolarClicks);
			System.out.println(main.firstSolarClicks);
			System.out.println(main.jinkoSolarClicks);
			System.out.println(main.nexteraEnergyClicks);
			System.out.println(main.sunpowerClicks);
			System.out.println(main.teslaClicks);
			
			la.canadiansolarBigSet = cm.returnSetFromCompanyColumn(main.canadianSolarClicks);
			la.firstsolarBigSet = cm.returnSetFromCompanyColumn(main.firstSolarClicks);
			la.jinkosolarBigSet = cm.returnSetFromCompanyColumn(main.jinkoSolarClicks);
			la.nexteraenergyBigSet = cm.returnSetFromCompanyColumn(main.nexteraEnergyClicks);
			la.sunpowerBigSet = cm.returnSetFromCompanyColumn(main.sunpowerClicks);
			la.teslaBigSet = cm.returnSetFromCompanyColumn(main.teslaClicks);
			
			System.out.println(main.firstSolarClicks + " IS THE FIRST SOLAR CLICKS");
			
			List<Integer> canadiansolarBigSetConvert = new ArrayList<>(la.canadiansolarBigSet);
			List<Integer> firstsolarBigSetConvert = new ArrayList<>(la.firstsolarBigSet);
			List<Integer> jinkosolarBigSetConvert = new ArrayList<>(la.jinkosolarBigSet);
			List<Integer> nexteraenergyBigSetConvert = new ArrayList<>(la.nexteraenergyBigSet);
			List<Integer> sunpowerBigSetConvert = new ArrayList<>(la.sunpowerBigSet);
			List<Integer> teslaBigSetConvert = new ArrayList<>(la.teslaBigSet);
			
			System.out.println(canadiansolarBigSetConvert);
			
			//sort the converted lists
			
			Collections.sort(canadiansolarBigSetConvert);
			Collections.sort(firstsolarBigSetConvert);
			Collections.sort(jinkosolarBigSetConvert);
			Collections.sort(nexteraenergyBigSetConvert);
			Collections.sort(sunpowerBigSetConvert);
			Collections.sort(teslaBigSetConvert);
			
			
			
			//convert the sorted lists into small list
			
			la.canadiansolarSmallSetConvert = cm.returnSmallListFromSorted(canadiansolarBigSetConvert, main.canadianSolarClicks);
			la.firstsolarSmallSetConvert = cm.returnSmallListFromSorted(firstsolarBigSetConvert, main.firstSolarClicks);
			la.jinkosolarSmallSetConvert = cm.returnSmallListFromSorted(jinkosolarBigSetConvert, main.jinkoSolarClicks);
			la.nexteraenergySmallSetConvert = cm.returnSmallListFromSorted(nexteraenergyBigSetConvert, main.nexteraEnergyClicks);
			la.sunpowerSmallSetConvert = cm.returnSmallListFromSorted(sunpowerBigSetConvert, main.sunpowerClicks);
			la.teslaSmallSetConvert = cm.returnSmallListFromSorted(teslaBigSetConvert, main.teslaClicks);
			
			
			
			//convert into small sets
			
			la.canadiansolarSmallSet.addAll(la.canadiansolarSmallSetConvert);
			la.firstsolarSmallSet.addAll(la.firstsolarSmallSetConvert);
			la.jinkosolarSmallSet.addAll(la.jinkosolarSmallSetConvert);
			la.nexteraenergySmallSet.addAll(la.nexteraenergySmallSetConvert);
			la.sunpowerSmallSet.addAll(la.sunpowerSmallSetConvert);
			la.teslaSmallSet.addAll(la.teslaSmallSetConvert);
			
			
			
			
			
			
			List<Integer> shortlistCanadianSolar = new ArrayList<>(la.canadiansolarSmallSet);
			List<Integer> shortlistFirstSolar = new ArrayList<>(la.firstsolarSmallSet);
			List<Integer> shortlistJinkoSolar = new ArrayList<>(la.jinkosolarSmallSet);
			List<Integer> shortlistNexteraEnergy = new ArrayList<>(la.nexteraenergySmallSet);
			List<Integer> shortlistSunpower = new ArrayList<>(la.sunpowerSmallSet);
			List<Integer> shortlistTesla = new ArrayList<>(la.teslaSmallSet);
			
			/*string test = "select userid from user_interest where company column = smallset.get(i)"
			 * create the variables and the
			*/
		
			
			System.out.println(shortlistCanadianSolar + " ===>/// shortlistCanadianSolar");
			
			List<Integer> IDcountcanadiansolar = new ArrayList<>();
			List<Integer> IDcountfirstsolar = new ArrayList<>();
			List<Integer> IDcountjinkosolar = new ArrayList<>();
			List<Integer> IDcountnexteraenergy = new ArrayList<>();
			List<Integer> IDcountsunpower = new ArrayList<>();
			List<Integer> IDcounttesla = new ArrayList<>();
			
			for (int i = 0; i < shortlistCanadianSolar.size(); i++) {
				String stringID = "SELECT COUNT(userID) FROM user_interest WHERE canadiansolar = " + shortlistCanadianSolar.get(i) + ";";
				ResultSet result = statement.executeQuery(stringID);
				result.next();
				int getCount = result.getInt(1);
				IDcountcanadiansolar.add(getCount);
			}
			for (int i = 0; i < shortlistFirstSolar.size(); i++) {
				String stringID = "SELECT COUNT(userID) FROM user_interest WHERE firstsolar = " + shortlistFirstSolar.get(i) + ";";
				ResultSet result = statement.executeQuery(stringID);
				result.next();
				int getCount = result.getInt(1);
				IDcountfirstsolar.add(getCount);
			}
			for (int i = 0; i < shortlistJinkoSolar.size(); i++) {
				String stringID = "SELECT COUNT(userID) FROM user_interest WHERE jinkosolar = " + shortlistJinkoSolar.get(i) + ";";
				ResultSet result = statement.executeQuery(stringID);
				result.next();
				int getCount = result.getInt(1);
				IDcountjinkosolar.add(getCount);
			}
			for (int i = 0; i < shortlistNexteraEnergy.size(); i++) {
				String stringID = "SELECT COUNT(userID) FROM user_interest WHERE nexteraenergy = " + shortlistNexteraEnergy.get(i) + ";";
				ResultSet result = statement.executeQuery(stringID);
				result.next();
				int getCount = result.getInt(1);
				IDcountnexteraenergy.add(getCount);
			}
			for (int i = 0; i < shortlistSunpower.size(); i++) {
				String stringID = "SELECT COUNT(userID) FROM user_interest WHERE sunpower = " + shortlistSunpower.get(i) + ";";
				ResultSet result = statement.executeQuery(stringID);
				result.next();
				int getCount = result.getInt(1);
				IDcountsunpower.add(getCount);
			}
			for (int i = 0; i < shortlistTesla.size(); i++) {
				String stringID = "SELECT COUNT(userID) FROM user_interest WHERE tesla = " + shortlistTesla.get(i) + ";";
				ResultSet result = statement.executeQuery(stringID);
				result.next();
				int getCount = result.getInt(1);
				IDcounttesla.add(getCount);
			}
			
			List<Integer> IDcanadiansolar = new ArrayList<>();
			List<Integer> IDfirstsolar = new ArrayList<>();
			List<Integer> IDjinkosolar = new ArrayList<>();
			List<Integer> IDnexteraenergy = new ArrayList<>();
			List<Integer> IDsunpower = new ArrayList<>();
			List<Integer> IDtesla = new ArrayList<>();
			

			for (int i = 0; i < shortlistCanadianSolar.size(); i++) {
					String getID = "SELECT userID FROM user_interest WHERE canadiansolar = " + shortlistCanadianSolar.get(i) + ";";
					ResultSet result = statement.executeQuery(getID);
				for (int j = 0; j < IDcountcanadiansolar.get(i); j++) {
					result.next();
					int getIDer = result.getInt(1);
					IDcanadiansolar.add(getIDer);
					if (j + 1 == IDcountcanadiansolar.get(i) && i + 1 != shortlistCanadianSolar.size()) {
						IDcanadiansolar.add(null);
					}
				}
			}
			for (int i = 0; i < shortlistFirstSolar.size(); i++) {
				String getID = "SELECT userID FROM user_interest WHERE firstsolar = " + shortlistFirstSolar.get(i) + ";";
				ResultSet result = statement.executeQuery(getID);
			for (int j = 0; j < IDcountfirstsolar.get(i); j++) {
				result.next();
				int getIDer = result.getInt(1);
				IDfirstsolar.add(getIDer);
				if (j + 1 == IDcountfirstsolar.get(i) && i + 1 != shortlistFirstSolar.size()) {
					IDfirstsolar.add(null);
				}
			}
		}
			for (int i = 0; i < shortlistJinkoSolar.size(); i++) {
				String getID = "SELECT userID FROM user_interest WHERE jinkosolar = " + shortlistJinkoSolar.get(i) + ";";
				ResultSet result = statement.executeQuery(getID);
			for (int j = 0; j < IDcountjinkosolar.get(i); j++) {
				result.next();
				int getIDer = result.getInt(1);
				IDjinkosolar.add(getIDer);
				if (j + 1 == IDcountjinkosolar.get(i) && i + 1 != shortlistJinkoSolar.size()) {
					IDjinkosolar.add(null);
				}
			}
		}
			for (int i = 0; i < shortlistNexteraEnergy.size(); i++) {
				String getID = "SELECT userID FROM user_interest WHERE nexteraenergy = " + shortlistNexteraEnergy.get(i) + ";";
				ResultSet result = statement.executeQuery(getID);
			for (int j = 0; j < IDcountnexteraenergy.get(i); j++) {
				result.next();
				int getIDer = result.getInt(1);
				IDnexteraenergy.add(getIDer);
				if (j + 1 == IDcountnexteraenergy.get(i) && i + 1 != shortlistNexteraEnergy.size()) {
					IDnexteraenergy.add(null);
				}
			}
		}
			for (int i = 0; i < shortlistSunpower.size(); i++) {
				String getID = "SELECT userID FROM user_interest WHERE sunpower = " + shortlistSunpower.get(i) + ";";
				ResultSet result = statement.executeQuery(getID);
			for (int j = 0; j < IDcountsunpower.get(i); j++) {
				result.next();
				int getIDer = result.getInt(1);
				IDsunpower.add(getIDer);
				if (j + 1 == IDcountsunpower.get(i) && i + 1 != shortlistSunpower.size()) {
					IDsunpower.add(null);
				}
			}
		}
			for (int i = 0; i < shortlistTesla.size(); i++) {
				String getID = "SELECT userID FROM user_interest WHERE tesla = " + shortlistTesla.get(i) + ";";
				ResultSet result = statement.executeQuery(getID);
			for (int j = 0; j < IDcounttesla.get(i); j++) {
				result.next();
				int getIDer = result.getInt(1);
				IDtesla.add(getIDer);
				if (j + 1 == IDcounttesla.get(i) && i + 1 != shortlistTesla.size()) {
					IDtesla.add(null);
				}
			}
		}
			
			
			
			List<String> neutralListingCanadianSolar = new ArrayList<>();
			List<String> deproListingCanadianSolar = new ArrayList<>();
			List<String> revoListingCanadianSolar = new ArrayList<>();
			
			List<String> neutralListingFirstSolar = new ArrayList<>();
			List<String> deproListingFirstSolar = new ArrayList<>();
			List<String> revoListingFirstSolar = new ArrayList<>();
			
			List<String> neutralListingJinkoSolar = new ArrayList<>();
			List<String> deproListingJinkoSolar = new ArrayList<>();
			List<String> revoListingJinkoSolar = new ArrayList<>();
			
			List<String> neutralListingNexteraEnergy = new ArrayList<>();
			List<String> deproListingNexteraEnergy = new ArrayList<>();
			List<String> revoListingNexteraEnergy = new ArrayList<>();
			
			List<String> neutralListingSunpower = new ArrayList<>();
			List<String> deproListingSunpower = new ArrayList<>();
			List<String> revoListingSunpower = new ArrayList<>();
			
			List<String> neutralListingTesla = new ArrayList<>();
			List<String> deproListingTesla = new ArrayList<>();
			List<String> revoListingTesla = new ArrayList<>();
			//This is the neutral zone
			
			for (int i = 0; i < IDcanadiansolar.size(); i++) {
				if (IDcanadiansolar.get(i) == null) {
					neutralListingCanadianSolar.add("%");
					deproListingCanadianSolar.add("%");
					revoListingCanadianSolar.add("%");
					i++;
				}
				String getType = "SELECT isUserNeutral, isUserDepro, isUserRevo FROM user_interest WHERE userID = " + IDcanadiansolar.get(i) + ";";
				ResultSet result = statement.executeQuery(getType);
				result.next();
				String stringTypeNeutral = result.getString("isUserNeutral");
				String stringTypeDepro = result.getString("isUserDepro");
				String stringTypeRevo = result.getString("isUserRevo");
				
				neutralListingCanadianSolar.add(stringTypeNeutral);	
				deproListingCanadianSolar.add(stringTypeDepro);
				revoListingCanadianSolar.add(stringTypeRevo);
				
			}
			for (int i = 0; i < IDfirstsolar.size(); i++) {
				if (IDfirstsolar.get(i) == null) {
					neutralListingFirstSolar.add("%");
					deproListingFirstSolar.add("%");
					revoListingFirstSolar.add("%");
					i++;
				}
				String getType = "SELECT isUserNeutral, isUserDepro, isUserRevo FROM user_interest WHERE userID = " + IDfirstsolar.get(i) + ";";
				ResultSet result = statement.executeQuery(getType);
				result.next();
				String stringTypeNeutral = result.getString("isUserNeutral");
				String stringTypeDepro = result.getString("isUserDepro");
				String stringTypeRevo = result.getString("isUserRevo");
				
				neutralListingFirstSolar.add(stringTypeNeutral);	
				deproListingFirstSolar.add(stringTypeDepro);
				revoListingFirstSolar.add(stringTypeRevo);
				
			}
			for (int i = 0; i < IDjinkosolar.size(); i++) {
				if (IDjinkosolar.get(i) == null) {
					neutralListingJinkoSolar.add("%");
					deproListingJinkoSolar.add("%");
					revoListingJinkoSolar.add("%");
					i++;
				}
				String getType = "SELECT isUserNeutral, isUserDepro, isUserRevo FROM user_interest WHERE userID = " + IDjinkosolar.get(i) + ";";
				ResultSet result = statement.executeQuery(getType);
				result.next();
				String stringTypeNeutral = result.getString("isUserNeutral");
				String stringTypeDepro = result.getString("isUserDepro");
				String stringTypeRevo = result.getString("isUserRevo");
				
				neutralListingJinkoSolar.add(stringTypeNeutral);	
				deproListingJinkoSolar.add(stringTypeDepro);
				revoListingJinkoSolar.add(stringTypeRevo);
				
			}
			for (int i = 0; i < IDnexteraenergy.size(); i++) {
				if (IDnexteraenergy.get(i) == null) {
					neutralListingNexteraEnergy.add("%");
					deproListingNexteraEnergy.add("%");
					revoListingNexteraEnergy.add("%");
					i++;
				}
				String getType = "SELECT isUserNeutral, isUserDepro, isUserRevo FROM user_interest WHERE userID = " + IDnexteraenergy.get(i) + ";";
				ResultSet result = statement.executeQuery(getType);
				result.next();
				String stringTypeNeutral = result.getString("isUserNeutral");
				String stringTypeDepro = result.getString("isUserDepro");
				String stringTypeRevo = result.getString("isUserRevo");
				
				neutralListingNexteraEnergy.add(stringTypeNeutral);	
				deproListingNexteraEnergy.add(stringTypeDepro);
				revoListingNexteraEnergy.add(stringTypeRevo);
				
			}
			for (int i = 0; i < IDsunpower.size(); i++) {
				if (IDsunpower.get(i) == null) {
					neutralListingSunpower.add("%");
					deproListingSunpower.add("%");
					revoListingSunpower.add("%");
					i++;
				}
				String getType = "SELECT isUserNeutral, isUserDepro, isUserRevo FROM user_interest WHERE userID = " + IDsunpower.get(i) + ";";
				ResultSet result = statement.executeQuery(getType);
				result.next();
				String stringTypeNeutral = result.getString("isUserNeutral");
				String stringTypeDepro = result.getString("isUserDepro");
				String stringTypeRevo = result.getString("isUserRevo");
				
				neutralListingSunpower.add(stringTypeNeutral);	
				deproListingSunpower.add(stringTypeDepro);
				revoListingSunpower.add(stringTypeRevo);
				
			}
			for (int i = 0; i < IDtesla.size(); i++) {
				if (IDtesla.get(i) == null) {
					neutralListingTesla.add("%");
					deproListingTesla.add("%");
					revoListingTesla.add("%");
					i++;
				}
				String getType = "SELECT isUserNeutral, isUserDepro, isUserRevo FROM user_interest WHERE userID = " + IDtesla.get(i) + ";";
				ResultSet result = statement.executeQuery(getType);
				result.next();
				String stringTypeNeutral = result.getString("isUserNeutral");
				String stringTypeDepro = result.getString("isUserDepro");
				String stringTypeRevo = result.getString("isUserRevo");
				
				neutralListingTesla.add(stringTypeNeutral);	
				deproListingTesla.add(stringTypeDepro);
				revoListingTesla.add(stringTypeRevo);
				
			}
			
			
			System.out.println();
			
			
			int NeutralYescanadiansolar = 0;
			int DeproYescanadiansolar = 0;
			int RevoYescanadiansolar = 0;
			
			int NeutralYesfirstsolar = 0;
			int DeproYesfirstsolar = 0;
			int RevoYesfirstsolar = 0;
			
			int NeutralYesjinkosolar = 0;
			int DeproYesjinkosolar = 0;
			int RevoYesjinkosolar = 0;
			
			int NeutralYesnexteraenergy = 0;
			int DeproYesnexteraenergy = 0;
			int RevoYesnexteraenergy = 0;
			
			int NeutralYessunpower = 0;
			int DeproYessunpower = 0;
			int RevoYessunpower = 0;
			
			int NeutralYestesla = 0;
			int DeproYestesla = 0;
			int RevoYestesla = 0;
			
			List<Integer> neutralArrayCanadianSolar = new ArrayList<>();
			List<Integer> deproArrayCanadianSolar = new ArrayList<>();
			List<Integer> revoArrayCanadianSolar = new ArrayList<>();
			
			List<Integer> neutralArrayFirstSolar = new ArrayList<>();
			List<Integer> deproArrayFirstSolar = new ArrayList<>();
			List<Integer> revoArrayFirstSolar = new ArrayList<>();
			
			List<Integer> neutralArrayJinkoSolar = new ArrayList<>();
			List<Integer> deproArrayJinkoSolar = new ArrayList<>();
			List<Integer> revoArrayJinkoSolar = new ArrayList<>();
			
			List<Integer> neutralArrayNexteraEnergy = new ArrayList<>();
			List<Integer> deproArrayNexteraEnergy = new ArrayList<>();
			List<Integer> revoArrayNexteraEnergy = new ArrayList<>();
			
			List<Integer> neutralArraySunpower = new ArrayList<>();
			List<Integer> deproArraySunpower = new ArrayList<>();
			List<Integer> revoArraySunpower = new ArrayList<>();
			
			List<Integer> neutralArrayTesla = new ArrayList<>();
			List<Integer> deproArrayTesla = new ArrayList<>();
			List<Integer> revoArrayTesla = new ArrayList<>();
			
			
			
			for (int i = 0; i < neutralListingCanadianSolar.size(); i++) {
				int j = 0;
				if (neutralListingCanadianSolar.get(i).equals("%")) {
					i++;
					NeutralYescanadiansolar = 0;
					DeproYescanadiansolar = 0;
					RevoYescanadiansolar = 0;
					j++;
				} if (neutralListingCanadianSolar.get(i).equals("YES")) {
					NeutralYescanadiansolar++;
					neutralArrayCanadianSolar.add(IDcanadiansolar.get(i));
				} else if (deproListingCanadianSolar.get(i).equals("YES")) {
					DeproYescanadiansolar++;
					deproArrayCanadianSolar.add(IDcanadiansolar.get(i));
				} else if (revoListingCanadianSolar.get(i).equals("YES")) {
					RevoYescanadiansolar++;
					revoArrayCanadianSolar.add(IDcanadiansolar.get(i));//each one will be unique, so pick the last index
				} if (NeutralYescanadiansolar >= 1 && DeproYescanadiansolar >= 1 && RevoYescanadiansolar >= 1) {
					double numeral = shortlistCanadianSolar.get(j);
					double priceDifferential = numeral / 10;
					System.out.println(neutralArrayCanadianSolar.get(neutralArrayCanadianSolar.size() - 1) + " is the Neutral userID for CanadianSolar and will pay standard pricing.");
					canadiansolarMap.put(neutralArrayCanadianSolar.get(neutralArrayCanadianSolar.size() - 1), (double) 0);
					System.out.println(deproArrayCanadianSolar.get(deproArrayCanadianSolar.size() - 1) + " is the Depro userID for CanadianSolar and will pay " + priceDifferential + "percent more.");
					canadiansolarMap.put(deproArrayCanadianSolar.get(deproArrayCanadianSolar.size() - 1), priceDifferential);
					System.out.println(revoArrayCanadianSolar.get(revoArrayCanadianSolar.size() - 1) + " is the Revo userID for CanadianSolar and will pay " + priceDifferential + "percent less.");
					canadiansolarMap.put(revoArrayCanadianSolar.get(revoArrayCanadianSolar.size() - 1), -priceDifferential);
					System.out.println();
					NeutralYescanadiansolar = 0;
					DeproYescanadiansolar = 0;
					RevoYescanadiansolar = 0;
				}
			} 
			for (int i = 0; i < neutralListingFirstSolar.size(); i++) {
				int j = 0;
				if (neutralListingFirstSolar.get(i).equals("%")) {
					i++;
					NeutralYesfirstsolar = 0;
					DeproYesfirstsolar = 0;
					RevoYesfirstsolar = 0;
					j++;
				} if (neutralListingFirstSolar.get(i).equals("YES")) {
					NeutralYesfirstsolar++;
					neutralArrayFirstSolar.add(IDfirstsolar.get(i));
				} else if (deproListingFirstSolar.get(i).equals("YES")) {
					DeproYesfirstsolar++;
					deproArrayFirstSolar.add(IDfirstsolar.get(i));
				} else if (revoListingFirstSolar.get(i).equals("YES")) {
					RevoYesfirstsolar++;
					revoArrayFirstSolar.add(IDfirstsolar.get(i));//each one will be unique, so pick the last index
				} if (NeutralYesfirstsolar >= 1 && DeproYesfirstsolar >= 1 && RevoYesfirstsolar >= 1) {
					double numeral = shortlistFirstSolar.get(j);
					double priceDifferential = numeral / 10;
					System.out.println(neutralArrayFirstSolar.get(neutralArrayFirstSolar.size() - 1) + " is the Neutral userID for FirstSolar and will pay standard pricing.");
					firstsolarMap.put(neutralArrayFirstSolar.get(neutralArrayFirstSolar.size() - 1), (double) 0);
					System.out.println(deproArrayFirstSolar.get(deproArrayFirstSolar.size() - 1) + " is the Depro userID for FirstSolar and will pay " + priceDifferential + " percent more.");
					firstsolarMap.put(deproArrayFirstSolar.get(deproArrayFirstSolar.size() - 1), priceDifferential);
					System.out.println(revoArrayFirstSolar.get(revoArrayFirstSolar.size() - 1) + " is the Revo userID for FirstSolar and will pay " + priceDifferential + " percent less.");
					firstsolarMap.put(revoArrayFirstSolar.get(revoArrayFirstSolar.size() - 1), -priceDifferential);
					System.out.println();
					
					NeutralYesfirstsolar = 0;
					DeproYesfirstsolar = 0;
					RevoYesfirstsolar = 0;
				}
			} 
			for (int i = 0; i < neutralListingJinkoSolar.size(); i++) {
				int j = 0;
				if (neutralListingJinkoSolar.get(i).equals("%")) {
					i++;
					NeutralYesjinkosolar = 0;
					DeproYesjinkosolar = 0;
					RevoYesjinkosolar = 0;
					j++;
				} if (neutralListingJinkoSolar.get(i).equals("YES")) {
					NeutralYesjinkosolar++;
					neutralArrayJinkoSolar.add(IDjinkosolar.get(i));
				} else if (deproListingJinkoSolar.get(i).equals("YES")) {
					DeproYesjinkosolar++;
					deproArrayJinkoSolar.add(IDjinkosolar.get(i));
				} else if (revoListingJinkoSolar.get(i).equals("YES")) {
					RevoYesjinkosolar++;
					revoArrayJinkoSolar.add(IDjinkosolar.get(i));//each one will be unique, so pick the last index
				} if (NeutralYesjinkosolar >= 1 && DeproYesjinkosolar >= 1 && RevoYesjinkosolar >= 1) {
					double numeral = shortlistJinkoSolar.get(j);
					double priceDifferential = numeral / 10;
					System.out.println(neutralArrayJinkoSolar.get(neutralArrayJinkoSolar.size() - 1) + " is the Neutral userID for JinkoSolar and will pay standard pricing.");
					jinkosolarMap.put(neutralArrayJinkoSolar.get(neutralArrayJinkoSolar.size() - 1), (double) 0);
					System.out.println(deproArrayJinkoSolar.get(deproArrayJinkoSolar.size() - 1) + " is the Depro userID for JinkoSolar and will pay " + priceDifferential + " percent more.");
					jinkosolarMap.put(deproArrayJinkoSolar.get(deproArrayJinkoSolar.size() - 1), priceDifferential);
					System.out.println(revoArrayJinkoSolar.get(revoArrayJinkoSolar.size() - 1) + " is the Revo userID for JinkoSolar and will pay " + priceDifferential + " percent less.");
					jinkosolarMap.put(revoArrayJinkoSolar.get(revoArrayJinkoSolar.size() - 1), -priceDifferential);
					System.out.println();
					NeutralYesjinkosolar = 0;
					DeproYesjinkosolar = 0;
					RevoYesjinkosolar = 0;
				}
			} 
			for (int i = 0; i < neutralListingNexteraEnergy.size(); i++) {
				int j = 0;
				if (neutralListingNexteraEnergy.get(i).equals("%")) {
					i++;
					NeutralYesnexteraenergy = 0;
					DeproYesnexteraenergy = 0;
					RevoYesnexteraenergy = 0;
					j++;
				} if (neutralListingNexteraEnergy.get(i).equals("YES")) {
					NeutralYesnexteraenergy++;
					neutralArrayNexteraEnergy.add(IDnexteraenergy.get(i));
				} else if (deproListingNexteraEnergy.get(i).equals("YES")) {
					DeproYesnexteraenergy++;
					deproArrayNexteraEnergy.add(IDnexteraenergy.get(i));
				} else if (revoListingNexteraEnergy.get(i).equals("YES")) {
					RevoYesnexteraenergy++;
					revoArrayNexteraEnergy.add(IDcanadiansolar.get(i));//each one will be unique, so pick the last index
				} if (NeutralYesnexteraenergy >= 1 && DeproYesnexteraenergy >= 1 && RevoYesnexteraenergy >= 1) {
					double numeral = shortlistNexteraEnergy.get(j);
					double priceDifferential = numeral / 10;
					System.out.println(neutralArrayNexteraEnergy.get(neutralArrayNexteraEnergy.size() - 1) + " is the Neutral userID for NexteraEnergy and will pay standard pricing.");
					nexteraenergyMap.put(neutralArrayNexteraEnergy.get(neutralArrayNexteraEnergy.size() - 1), (double) 0);
					System.out.println(deproArrayNexteraEnergy.get(deproArrayNexteraEnergy.size() - 1) + " is the Depro userID for NexteraEnergy and will pay " + priceDifferential + " percent more.");
					nexteraenergyMap.put(deproArrayNexteraEnergy.get(deproArrayNexteraEnergy.size() - 1), priceDifferential);
					System.out.println(revoArrayNexteraEnergy.get(revoArrayNexteraEnergy.size() - 1) + " is the Revo userID for NexteraEnergy and will pay " + priceDifferential + " percent less.");
					nexteraenergyMap.put(revoArrayNexteraEnergy.get(revoArrayNexteraEnergy.size() - 1), -priceDifferential);
					System.out.println();
					NeutralYesnexteraenergy = 0;
					DeproYesnexteraenergy = 0;
					RevoYesnexteraenergy = 0;
				}
			} 
			for (int i = 0; i < neutralListingSunpower.size(); i++) {
				int j = 0;
				if (neutralListingSunpower.get(i).equals("%")) {
					i++;
					NeutralYessunpower = 0;
					DeproYessunpower = 0;
					RevoYessunpower = 0;
					j++;
				} if (neutralListingSunpower.get(i).equals("YES")) {
					NeutralYessunpower++;
					neutralArraySunpower.add(IDsunpower.get(i));
				} else if (deproListingSunpower.get(i).equals("YES")) {
					DeproYessunpower++;
					deproArraySunpower.add(IDsunpower.get(i));
				} else if (revoListingSunpower.get(i).equals("YES")) {
					RevoYessunpower++;
					revoArraySunpower.add(IDsunpower.get(i));//each one will be unique, so pick the last index
				} if (NeutralYessunpower >= 1 && DeproYessunpower >= 1 && RevoYessunpower >= 1) {
					double numeral = shortlistSunpower.get(j);
					double priceDifferential = numeral / 10;
					System.out.println(neutralArraySunpower.get(neutralArraySunpower.size() - 1) + " is the Neutral userID for Sunpower and will pay standard pricing.");
					sunpowerMap.put(neutralArraySunpower.get(neutralArraySunpower.size() - 1), (double) 0);
					System.out.println(deproArraySunpower.get(deproArraySunpower.size() - 1) + " is the Depro userID for Sunpower and will pay " + priceDifferential + " percent more.");
					sunpowerMap.put(deproArraySunpower.get(deproArraySunpower.size() - 1), priceDifferential);
					System.out.println(revoArraySunpower.get(revoArraySunpower.size() - 1) + " is the Revo userID for Sunpower and will pay " + priceDifferential + " percent less.");
					sunpowerMap.put(revoArraySunpower.get(revoArraySunpower.size() - 1), -priceDifferential);
					System.out.println();
					NeutralYessunpower = 0;
					DeproYessunpower = 0;
					RevoYessunpower = 0;
				}
			}
			System.out.println(neutralArraySunpower);
			System.out.println(deproArraySunpower);
			System.out.println(revoArraySunpower);
			for (int i = 0; i < neutralListingTesla.size(); i++) {
				int j = 0;
				if (neutralListingTesla.get(i).equals("%")) {
					i++;
					NeutralYestesla = 0;
					DeproYestesla = 0;
					RevoYestesla = 0;
					j++;
				} if (neutralListingTesla.get(i).equals("YES")) {
					NeutralYestesla++;
					neutralArrayTesla.add(IDtesla.get(i));
				} else if (deproListingTesla.get(i).equals("YES")) {
					DeproYestesla++;
					deproArrayTesla.add(IDtesla.get(i));
				} else if (revoListingTesla.get(i).equals("YES")) {
					RevoYestesla++;
					revoArrayTesla.add(IDtesla.get(i));//each one will be unique, so pick the last index
				} if (NeutralYestesla >= 1 && DeproYestesla >= 1 && RevoYestesla >= 1) {
					double numeral = shortlistSunpower.get(j);
					double priceDifferential = numeral / 10;
					System.out.println(neutralArrayTesla.get(neutralArrayTesla.size() - 1) + " is the Neutral userID for Tesla and will pay standard pricing.");
					teslaMap.put(neutralArrayTesla.get(neutralArrayTesla.size() - 1), (double) 0);
					System.out.println(deproArrayTesla.get(deproArrayTesla.size() - 1) + " is the Depro userID for Tesla and will pay " + priceDifferential + " more.");
					teslaMap.put(deproArrayTesla.get(deproArrayTesla.size() - 1), priceDifferential);
					System.out.println(revoArrayTesla.get(revoArrayTesla.size() - 1) + " is the Revo userID for Tesla and will pay " + priceDifferential + " less.");
					teslaMap.put(revoArrayTesla.get(revoArrayTesla.size() - 1), -priceDifferential);
					System.out.println();
					NeutralYestesla = 0;
					DeproYestesla = 0;
					RevoYestesla = 0;
				}
			} 
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@PutMapping("/Add a User")
	public void addUser(@RequestBody UserInterest ui) {
		List<Double> time = new ArrayList<Double>(Arrays.asList(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0));
		Regression regression = new Regression();
		Main main = new Main();
		ListAssortment la = new ListAssortment();
		Scanner s = new Scanner(System.in);
		Connection connection = null;
		
		try(s) {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
		
		//Query from Database into distinct Company lists
		for (int j = 0; j < la.queryCompanyArray.size(); j++) {
			for (int i = 1; i < time.size() + 1; i++) {

				String deprecCS = "SELECT Revenue,COGS,Depreciation FROM " + la.queryCompanyArray.get(j)
						+ " WHERE ID = '" + i + "'";
				ResultSet result = statement.executeQuery(deprecCS);
				result.next();
				BigDecimal dubz = result.getBigDecimal("Depreciation");
				BigDecimal rever = result.getBigDecimal("Revenue");
				BigDecimal cogged = result.getBigDecimal("COGS");
				if (j == 0) {
					la.BDDeprecCanadianSolar.add(dubz);
					la.BDRevCanadianSolar.add(rever);
					la.BDCogsCanadianSolar.add(cogged);
				} else if (j == 1) {
					la.BDDeprecFirstSolar.add(dubz);
					la.BDRevFirstSolar.add(rever);
					la.BDCogsFirstSolar.add(cogged);
				} else if (j == 2) {
					la.BDDeprecJinkoSolar.add(dubz);
					la.BDRevJinkoSolar.add(rever);
					la.BDCogsJinkoSolar.add(cogged);
				} else if (j == 3) {
					la.BDDeprecNexteraEnergy.add(dubz);
					la.BDRevNexteraEnergy.add(rever);
					la.BDCogsNexteraEnergy.add(cogged);
				} else if (j == 4) {
					la.BDDeprecSunpower.add(dubz);
					la.BDRevSunpower.add(rever);
					la.BDCogsSunpower.add(cogged);
				} else if (j == 5) {
					la.BDDeprecTesla.add(dubz);
					la.BDRevTesla.add(rever);
					la.BDCogsTesla.add(cogged);
				}
			}
		}
		
		la.convertToDouble();
		
		//TEST AREA
		Double deproCanadianSolar = regression.deproScore(la.CogsCanadianSolar, la.DeprecCanadianSolar, time);
		Double deproFirstSolar = regression.deproScore(la.CogsFirstSolar, la.DeprecFirstSolar, time);
		Double deproJinkoSolar = regression.deproScore(la.CogsJinkoSolar, la.DeprecJinkoSolar, time);
		Double deproNexteraEnergy = regression.deproScore(la.CogsNexteraEnergy, la.DeprecNexteraEnergy, time);
		Double deproSunpower = regression.deproScore(la.CogsSunpower, la.DeprecSunpower, time);
		Double deproTesla = regression.deproScore(la.CogsTesla, la.DeprecTesla, time);
		
		Double revoCanadianSolar = regression.revoScore(la.CogsCanadianSolar, la.RevCanadianSolar, time);
		Double revoFirstSolar = regression.revoScore(la.CogsFirstSolar, la.RevFirstSolar, time);
		Double revoJinkoSolar = regression.revoScore(la.CogsJinkoSolar, la.RevJinkoSolar, time);
		Double revoNexteraEnergy = regression.revoScore(la.CogsNexteraEnergy, la.RevNexteraEnergy, time);
		Double revoSunpower = regression.revoScore(la.CogsSunpower, la.RevSunpower, time);
		Double revoTesla = regression.revoScore(la.CogsTesla, la.DeprecTesla, time);
		
		
		
		main.deproList.add(deproCanadianSolar);
		main.deproList.add(deproFirstSolar);
		main.deproList.add(deproJinkoSolar);
		main.deproList.add(deproNexteraEnergy);
		main.deproList.add(deproSunpower);
		main.deproList.add(deproTesla);
		
		main.revoList.add(revoCanadianSolar);
		main.revoList.add(revoFirstSolar);
		main.revoList.add(revoJinkoSolar);
		main.revoList.add(revoNexteraEnergy);
		main.revoList.add(revoSunpower);
		main.revoList.add(revoTesla);
		
		
		userClicksControl.add(ui.canadiansolar);
		userClicksControl.add(ui.firstsolar);
		userClicksControl.add(ui.jinkosolar);
		userClicksControl.add(ui.nexteraenergy);
		userClicksControl.add(ui.sunpower);
		userClicksControl.add(ui.tesla);
		
		System.out.println(userClicksControl + " is the demand distribution");
		Double testDepro = regression.rsquared(userClicksControl, main.deproList);
		//Here would be the demand relationship that the initial business logic program has to analyze
		Double testRevo = regression.rsquared(userClicksControl, main.revoList);
		double correlationDifference = Math.abs(testDepro - testRevo);
		
		
			
			if (correlationDifference <= 0.20) {
				System.out.println("This is a neutral user");
				String queryName = "SELECT count(lastname) FROM user_interest WHERE lastname = '" + ui.lastname + "'";
				ResultSet test = statement.executeQuery(queryName);
				test.next();
				int answer = test.getInt(1);
				if (!(answer > 0)) {
					statement.executeUpdate("insert into user_type (lastname, neutral_count, depro_count, revo_count) values ('" + ui.lastname + "', 1, 0, 0);");
					
					
					statement.executeUpdate(
							"insert into user_interest (firstname, lastname,canadiansolar,firstsolar,jinkosolar,nexteraenergy,sunpower,tesla, isUserNeutral, isUserDepro, isUserRevo) "
									+ "values ('" + ui.firstname + "','" + ui.lastname + "','" + ui.canadiansolar + "','" + ui.firstsolar
									+ "','" + ui.jinkosolar + "','" + ui.nexteraenergy + "','" + ui.sunpower + "','" + ui.tesla + "', 'YES', 'NO', 'NO');");
				} else if (answer > 0) {
					System.out.println("User demand update");
					//query statement select revocount from user_type, place it into variable and add 1, then update the revo_count
					String getNeutralCount = "SELECT neutral_count FROM user_type WHERE lastname = '" + ui.lastname + "'";
					ResultSet resultNeutral = statement.executeQuery(getNeutralCount);
					resultNeutral.next();
					int answerNeutral = resultNeutral.getInt(1);
					System.out.println(answerNeutral + " old neutral count");
					answerNeutral++;
					System.out.println(answerNeutral + " new neutral count");
					//update
					
					statement.executeUpdate("UPDATE user_type SET neutral_count = '" + answerNeutral + "' WHERE lastname = '" + ui.lastname + "';");
					
					statement.executeUpdate(
							"insert into user_interest (firstname, lastname,canadiansolar,firstsolar,jinkosolar,nexteraenergy,sunpower,tesla, isUserNeutral, isUserDepro, isUserRevo) "
									+ "values ('" + ui.firstname + "','" + ui.lastname + "','" + ui.canadiansolar + "','" + ui.firstsolar
									+ "','" + ui.jinkosolar + "','" + ui.nexteraenergy + "','" + ui.sunpower + "','" + ui.tesla + "', 'YES', 'NO', 'NO');");
				}
			}
			
			else if (correlationDifference > 0.20) {
				System.out.println("This is NOT a neutral user");
				if (testDepro > testRevo) {
					System.out.println("This is a Depro user");
					String queryName = "SELECT count(lastname) FROM user_interest WHERE lastname = '" + ui.lastname + "'";
					ResultSet test = statement.executeQuery(queryName);
					test.next();
					int answer = test.getInt(1);
					if (!(answer > 0)) {
						statement.executeUpdate("insert into user_type (lastname, neutral_count, depro_count, revo_count) values ('" + ui.lastname + "', 0, 1, 0);");
						
						statement.executeUpdate(
								"insert into user_interest (firstname, lastname,canadiansolar,firstsolar,jinkosolar,nexteraenergy,sunpower,tesla, isUserNeutral, isUserDepro, isUserRevo) "
										+ "values ('" + ui.firstname + "','" + ui.lastname + "','" + ui.canadiansolar + "','" + ui.firstsolar
										+ "','" + ui.jinkosolar + "','" + ui.nexteraenergy + "','" + ui.sunpower + "','" + ui.tesla + "', 'NO', 'YES', 'NO');");
					} else if (answer > 0) {
						System.out.println("User demand update");
						//query statement select revocount from user_type, place it into variable and add 1, then update the revo_count
						String getDeproCount = "SELECT depro_count FROM user_type WHERE lastname = '" + ui.lastname + "'";
						ResultSet resultDepro = statement.executeQuery(getDeproCount);
						resultDepro.next();
						int answerDepro = resultDepro.getInt(1);
						System.out.println(answerDepro + " old depro count");
						answerDepro++;
						System.out.println(answerDepro + " new depro count");
						//update
						
						
						statement.executeUpdate("UPDATE user_type SET depro_count = '" + answerDepro + "' WHERE lastname = '" + ui.lastname + "';");
						
						
						
						//Devusoid Table/////////////
						
						String getRevoDevusoid = "SELECT revo_count FROM user_type WHERE lastname = '" + ui.lastname + "';";
						ResultSet revoDevusoidResult = statement.executeQuery(getRevoDevusoid);
						revoDevusoidResult.next();
						int valueRevoDevusoid = revoDevusoidResult.getInt(1);
						
						String getDeproDevusoid = "SELECT depro_count FROM user_type WHERE lastname = '" + ui.lastname + "';";
						ResultSet deproDevusoidResult = statement.executeQuery(getDeproDevusoid);
						deproDevusoidResult.next();
						int valueDeproDevusoid = deproDevusoidResult.getInt(1);
						
						if ((valueRevoDevusoid > 0) && (valueDeproDevusoid > 0) || ((valueRevoDevusoid > 1) || (valueDeproDevusoid > 1))) {
							int Devusoid = (valueRevoDevusoid - valueDeproDevusoid);
							
							String x = "SELECT COUNT(lastname) FROM devusoid_table WHERE lastname = '" + ui.lastname + "'";
							ResultSet getX = statement.executeQuery(x);
							getX.next();
							int amountx = getX.getInt(1);
							
							if (amountx > 0) {
								statement.executeUpdate("UPDATE devusoid_table SET devusoid = '" + Devusoid + "' WHERE lastname = '" + ui.lastname + "'");
								statement.executeUpdate("INSERT INTO user_junction (lastname, devusoid) VALUES ('" + ui.lastname + "','" + Devusoid + "');");
							} else {
								statement.executeUpdate("INSERT INTO devusoid_table (lastname, devusoid) VALUES ('" + ui.lastname + "','" + Devusoid + "');");
								statement.executeUpdate("INSERT INTO user_junction (lastname, devusoid) VALUES ('" + ui.lastname + "','" + Devusoid + "');");
							}
							
						}
						
						
						
						statement.executeUpdate(
								"insert into user_interest (firstname, lastname,canadiansolar,firstsolar,jinkosolar,nexteraenergy,sunpower,tesla, isUserNeutral, isUserDepro, isUserRevo) "
										+ "values ('" + ui.firstname + "','" + ui.lastname + "','" + ui.canadiansolar + "','" + ui.firstsolar
										+ "','" + ui.jinkosolar + "','" + ui.nexteraenergy + "','" + ui.sunpower + "','" + ui.tesla + "', 'NO', 'YES', 'NO');");
					}
				}
				
				else if (testDepro <= testRevo) {
					System.out.println("This is a Revo user");
					String queryName = "SELECT count(lastname) FROM user_interest WHERE lastname = '" + ui.lastname + "'";
					ResultSet test = statement.executeQuery(queryName);
					test.next();
					int answer = test.getInt(1);
					System.out.println(answer + " IS THE ANSWER");
					if (!(answer > 0)) {
					
					statement.executeUpdate("insert into user_type (lastname, neutral_count, depro_count, revo_count) values ('" + ui.lastname + "', 0, 0, 1);");
					 
					statement.executeUpdate(
							"insert into user_interest (firstname, lastname,canadiansolar,firstsolar,jinkosolar,nexteraenergy,sunpower,tesla, isUserNeutral, isUserDepro, isUserRevo) "
									+ "values ('" + ui.firstname + "','" + ui.lastname + "','" + ui.canadiansolar + "','" + ui.firstsolar
									+ "','" + ui.jinkosolar + "','" + ui.nexteraenergy + "','" + ui.sunpower + "','" + ui.tesla + "', 'NO', 'NO', 'YES');");
				} else if (answer > 0) {
					System.out.println("DOOOFUS");
					//query statement select revocount from user_type, place it into variable and add 1, then update the revo_count
					String getRevoCount = "SELECT revo_count FROM user_type WHERE lastname = '" + ui.lastname + "'";
					ResultSet resultRevo = statement.executeQuery(getRevoCount);
					resultRevo.next();
					int answerRevo = resultRevo.getInt(1);
					System.out.println(answerRevo + " old revo count");
					answerRevo++;
					System.out.println(answerRevo + " new revo count");
					//update
					
					statement.executeUpdate("UPDATE user_type SET revo_count = '" + answerRevo + "' WHERE lastname = '" + ui.lastname + "';");
					
					String getRevoDevusoid = "SELECT revo_count FROM user_type WHERE lastname = '" + ui.lastname + "';";
					ResultSet revoDevusoidResult = statement.executeQuery(getRevoDevusoid);
					revoDevusoidResult.next();
					int valueRevoDevusoid = revoDevusoidResult.getInt(1);
					
					String getDeproDevusoid = "SELECT depro_count FROM user_type WHERE lastname = '" + ui.lastname + "';";
					ResultSet deproDevusoidResult = statement.executeQuery(getDeproDevusoid);
					deproDevusoidResult.next();
					int valueDeproDevusoid = deproDevusoidResult.getInt(1);
					
					if ((valueRevoDevusoid > 0) && (valueDeproDevusoid > 0) || ((valueRevoDevusoid > 1) || (valueDeproDevusoid > 1))) {
						int Devusoid = (valueRevoDevusoid - valueDeproDevusoid);
						
						String x = "SELECT COUNT(lastname) FROM devusoid_table WHERE lastname = '" + ui.lastname + "'";
						ResultSet getX = statement.executeQuery(x);
						getX.next();
						int amountx = getX.getInt(1);
						
						if (amountx > 0) {
							statement.executeUpdate("UPDATE devusoid_table SET devusoid = '" + Devusoid + "' WHERE lastname = '" + ui.lastname + "'");
							statement.executeUpdate("INSERT INTO user_junction (lastname, devusoid) VALUES ('" + ui.lastname + "','" + Devusoid + "');");
						} else {
							statement.executeUpdate("INSERT INTO devusoid_table (lastname, devusoid) VALUES ('" + ui.lastname + "','" + Devusoid + "');");
							statement.executeUpdate("INSERT INTO user_junction (lastname, devusoid) VALUES ('" + ui.lastname + "','" + Devusoid + "');");
						}
						
					}
					
					
					statement.executeUpdate(
							"insert into user_interest (firstname, lastname,canadiansolar,firstsolar,jinkosolar,nexteraenergy,sunpower,tesla, isUserNeutral, isUserDepro, isUserRevo) "
									+ "values ('" + ui.firstname + "','" + ui.lastname + "','" + ui.canadiansolar + "','" + ui.firstsolar
									+ "','" + ui.jinkosolar + "','" + ui.nexteraenergy + "','" + ui.sunpower + "','" + ui.tesla + "', 'NO', 'NO', 'YES');");
					
				}
				}
			}
			System.out.println("ROW CREATION SUCCESSFUL");
			System.out.println("/////// " + userClicksControl + " is what " + ui.firstname + " " + ui.lastname + " scores as USER DEMAND CLICKS");
			System.out.println(userClicksControl);
			userClicksControl.clear();
		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@GetMapping("/Get Devusoidal Events")
	public HashMap<String,Integer> getDev() {
		Scanner s = new Scanner(System.in);
		Connection connection = null;
		
		
		try(s) {
			connection = JdbcConnection.getJdbcConnection();
			Statement statement = connection.createStatement();
			
			String junctionCount = "SELECT COUNT(lastname) FROM user_junction";
			ResultSet countSet = statement.executeQuery(junctionCount);
			int count = countSet.getInt(1);
			
			for (int i = 1; i <= count; i++) {
				String lastname = "SELECT lastname FROM user_junction";
				ResultSet nameSet = statement.executeQuery(lastname);
				String name = nameSet.getString(i);
				
				String devusoid = "SELECT devusoid FROM user_junction";
				ResultSet devSet = statement.executeQuery(devusoid);
				int dev = devSet.getInt(i);
				
				map.put(name, dev);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	} 
	
	
	

}
