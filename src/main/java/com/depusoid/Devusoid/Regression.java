package com.depusoid.Devusoid;
import java.math.BigDecimal;
import java.util.List;

public class Regression {
	double mean(List<Double> list) {
		double sum = 0;
		for (int i =0; i < list.size(); i++) {
			sum += list.get(i);
		}
		double mean =  sum / list.size();
		return mean;
	}
	int multiply(int a, int b) {
		return a * b;
	}
	
	//ArrayX is usually time while arrayY is either Revenue or Depreciation
	double slopereg(List<Double> arrayX, List<Double> arrayY) {
		
		double sumX = 0;
		double sumY = 0;
		double sumP = 0;
		double sumX2 = 0;
		double sumY2 = 0;
		for (int i = 0; i < arrayX.size(); i++) {
				sumX += arrayX.get(i);
				sumY += arrayY.get(i);
				double product = arrayX.get(i) * arrayY.get(i);
				double squaredX = arrayX.get(i) * arrayX.get(i);
				double squaredY = arrayY.get(i) * arrayY.get(i);
				sumP += product;
				sumX2 += squaredX;
				sumY2 += squaredY;
		}	
		double slope = ((arrayX.size() * sumP) - (sumX * sumY)) / ((arrayX.size() * sumX2) - (sumX * sumX));	
			return slope;
		}
	double yintercept(List<Double> arrayX, List<Double> arrayY) {
		double sumX = 0;
		double sumY = 0;
		double sumP = 0;
		double sumX2 = 0;
		double sumY2 = 0;
		for (int i = 0; i < arrayX.size(); i++) {
			sumX += arrayX.get(i);
			sumY += arrayY.get(i);
			double product = arrayX.get(i) * arrayY.get(i);
			double squaredX = arrayX.get(i) * arrayX.get(i);
			double squaredY = arrayY.get(i) * arrayY.get(i);
			sumP += product;
			sumX2 += squaredX;
			sumY2 += squaredY;
	 }
	double yIntercept = ((sumY * sumX2) - (sumX * sumP)) / ((arrayX.size() * sumX2) - (sumX * sumX));
		return yIntercept;
	 }
	void equation(List<Double> arrayX, List<Double> arrayY) {
		double slope = slopereg(arrayX, arrayY);
		double yint = yintercept(arrayX, arrayY);
		System.out.println("The linear model is: " + slope + "x" + " + " + yint);
	 } 

	double ymean(List<Double> array) {
		double sum = 0;
		for (int i = 0; i < array.size(); i++) {
			sum += array.get(i);
	    }
		double avg = sum / (array.size());
		return avg;
	 }

	double yhat(List<Double> arrayx, List<Double> arrayy, int x) {
		double slope = slopereg(arrayx, arrayy);
		double yint = yintercept(arrayx, arrayy);
		double yhat = (slope * arrayx.get(x)) + yint;
		
		return yhat;
}
	double rsquared(List<Double> arrayx, List<Double> arrayy) {
		double ssr = 0;
		double sst = 0;
		for (int i = 0; i < arrayx.size(); i++) {
			ssr += ((yhat(arrayx, arrayy, i) - ymean(arrayy)) * (yhat(arrayx, arrayy, i) - ymean(arrayy)));
			sst += (arrayy.get(i) - ymean(arrayy)) * (arrayy.get(i) - ymean(arrayy));
		}
		double rsquared = ssr / sst;
		
		return rsquared;
 }
	double revoScore(List<Double> cogs, List<Double> rev, List<Double> time) {
		double slope = slopereg(time, rev);
		double rcorr = rsquared(cogs,rev);
		double var = (slope / rcorr) * (slope / rcorr) / 1000000000;
		
		return var;
	}
	
	double deproScore(List<Double> cogs, List<Double> deprec, List<Double> time) {
		double slope = slopereg(time, deprec);
		double rcorr = rsquared(cogs, deprec);
		double var = (rcorr / slope)*(rcorr / slope) * 1000000000;
		
		return var;
  }
}
