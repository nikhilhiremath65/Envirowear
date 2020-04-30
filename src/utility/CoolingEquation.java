package utility;

public class CoolingEquation {

        public double faren_To_Kelvin(double F) {
        	
            return( (F - 32)/1.8 )+ 273;
        }

        public double Kelvin_To_Faren(double K) {
        	
            return (9.0/5.0)*(K-273)+32;
        }

        public double getNewTemp(double tempEnv , double tempCal, double  k , double  givenTime) {
        	
            return (tempEnv + (tempCal - tempEnv ) * Math.exp ((-k) * givenTime));
        }

        public double getTempChange(double tempEnv, double tempJacket) {
        	
            double k =  0.00150;
            double givenTime = 1;
            double tempEnv_k = faren_To_Kelvin(tempEnv);
            double tempCal_k = faren_To_Kelvin(tempJacket);
            double tempAfterGivenTime = getNewTemp(tempEnv_k , tempCal_k,  k ,  givenTime);
            double tempDec = Kelvin_To_Faren(tempAfterGivenTime);

            return tempJacket - tempDec;
        }
    }

