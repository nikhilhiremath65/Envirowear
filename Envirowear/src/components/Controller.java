package components;

public class  Controller{
	
    private double userSetInput;
    private double avgSen1=0;
    private double avgSen2=0;
    private int count = 0;

    Sensor sensor1;
    Sensor sensor2;
    Average average;
    Safety safety;


    public Controller(double userSetInput){
    	average = new Average();
        this.userSetInput = userSetInput;
    }

    public double calcSensorAverage(){
        double result = -1;
        count++;
        avgSen1+= sensor1.getTemp();
        avgSen2+= sensor2.getTemp();

        if(count == 5){
            avgSen1/=count;
            avgSen2/=count;
            count = 0;
            result = average.getAverage(avgSen1,avgSen2);
            avgSen1 = 0;
            avgSen2 = 0;
        }
        return result;
    }

}
