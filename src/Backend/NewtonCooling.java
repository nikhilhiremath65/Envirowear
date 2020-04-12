package Backend;

public class NewtonCooling {


        public double Degree_To_Kelvin(double D)
        {
            return (D + 273.1);
        }

        public double  Kelvin_To_Degree(double K)
        {
            return (K - 273.1);
        }

        public double  newtonsLawOfCooling( double tempEnv ,double tempCal,double  k ,double  givenTime)
        {
            return(tempEnv + (tempCal - tempEnv ) * Math.exp ((-k) * givenTime));
        }


        public float  getCoolingTemp(float tempEnv,float tempJacket)
        {
            double tempForGivenTime = 0,  k =  0.00150 ,  givenTime = 120  ;

            double tempEnv_k = Degree_To_Kelvin(tempEnv);

            double tempCal_k = Degree_To_Kelvin(tempJacket);


            double tempAfterGivenTime = newtonsLawOfCooling(tempEnv_k , tempCal_k,  k ,  givenTime);

            double degreeTemp = Kelvin_To_Degree(tempAfterGivenTime);

            return (float) degreeTemp;
        }
    }

