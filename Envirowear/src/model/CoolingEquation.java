package model;

public class CoolingEquation {

        public double Degree_To_Kelvin(double D) {
        	
            return (D + 273.1);
        }

        public double  Kelvin_To_Degree(double K) {
        	
            return (K - 273.1);
        }

        public double getNewTemp(double tempEnv , double tempCal, double  k , double  givenTime) {
        	
            return (tempEnv + (tempCal - tempEnv ) * Math.exp ((-k) * givenTime));
        }

        public double getTempChange(double tempEnv, double tempJacket) {
        	
            double k =  0.00150;
            double givenTime = 1  ;
            
            double tempEnv_k = Degree_To_Kelvin(tempEnv);
            double tempCal_k = Degree_To_Kelvin(tempJacket);
            double tempAfterGivenTime = getNewTemp(tempEnv_k , tempCal_k,  k ,  givenTime);
            double tempDec = Kelvin_To_Degree(tempAfterGivenTime);
            

            return (double) (tempJacket - tempDec);
        }
    }

