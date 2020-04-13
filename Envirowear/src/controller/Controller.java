package controller;

public class Controller {
	
    private float userSetInput;

    public Controller(float userSetInput){
    	
        this.userSetInput = userSetInput;
    }

    public float sensorAverage(float avg1, float avg2){
    	
        return (avg1+avg2)/2;
    }


}
