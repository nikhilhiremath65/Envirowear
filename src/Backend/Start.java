package Backend;


import java.util.concurrent.TimeUnit;

import Controller.LowerBodyController;
import Controller.UpperBodyController;

public class Start {


    public Start() {

        float[] ls1 = {0,0,0,0,0};
        float[] ls2 = {0,0,0,0,0};
        float[] us1 = {20,21,22,23,24};
        float[] us2 = {25,21,22,23,24};
        float upperSensorAverage=-1,lowerSensorAverage=-1,upperOut=0,lowerOut=0;

        UpperBodyController upperBodyController = new UpperBodyController(Data.getInstance().getUserSetUpper());


        LowerBodyController lowerBodyController = new LowerBodyController(Data.getInstance().getUserSetUpper());

        Sensor enviroSensor = new Sensor(24);
        System.out.println("assaas");

//        System.out.println("Give 5 input sensor values");
//        Scanner sc = new Scanner(System.in);
//        for(int i=0;i<5;i++){
//            ls1[i] = sc.nextFloat();
//
//        }
//        System.out.println("Give 5 input sensor values");
//
//        for(int i=0;i<5;i++){
//            ls2[i]= sc.nextFloat();
//
//        }
//        System.out.println("Give 5 input sensor values");
//        for(int i=0;i<5;i++){
//            us1[i] = sc.nextFloat();
//
//        }
//        System.out.println("Give 5 input sensor values");
//        for(int i=0;i<5;i++){
//            us2[i] = sc.nextFloat();
//
//        }
        int i = 0;
        Actuator actuator = new Actuator();
        while(true) {
        	try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            if(Data.getInstance().isUpperStatus() || Data.getInstance().isLowerStatus()) {


                if (Data.getInstance().isUpperStatus()) {
                    if (i < 5)
                        upperBodyController.setSensorTemp(us1[i], us2[i]);
                    else
                        upperBodyController.setSensorTemp(upperOut, upperOut);
                    upperBodyController.safetyCheckSensor();
                    upperSensorAverage = upperBodyController.calcSensorAverage();
                }

                if (Data.getInstance().isLowerStatus()) {
                    if (i < 5)
                        lowerBodyController.setSensorTemp(ls1[i], ls2[i]);

                    else {

                        lowerBodyController.setSensorTemp(lowerOut, lowerOut);
                    }

                    lowerBodyController.sensorSafetyCheck();
                    lowerSensorAverage = lowerBodyController.calcSensorAverage();
                }

                if (Data.getInstance().isUpperStatus() && upperSensorAverage != -1) {

                    upperSensorAverage = upperBodyController.safetyChecks(upperSensorAverage);
                    upperOut = actuator.checkAction(Data.getInstance().getUserSetUpper(), upperSensorAverage, "UPPER",Data.getInstance().getEnvirTemp());
                    Data.getInstance().setCurrUpperTemp(upperOut);
                }

                if (Data.getInstance().isLowerStatus() && lowerSensorAverage != -1) {

                    lowerSensorAverage = lowerBodyController.otherSafetyChecks(lowerSensorAverage);
                    lowerOut = actuator.checkAction(Data.getInstance().getUserSetLower(), lowerSensorAverage, "LOWER",Data.getInstance().getEnvirTemp());
                    Data.getInstance().setCurrLowerTemp(lowerOut);
                }
                if (upperSensorAverage != -1 && (Data.getInstance().isLowerStatus() || Data.getInstance().isUpperStatus())) {
                    System.out.println("Upper Sensor Average: " + upperSensorAverage + " Lower Sensor Average: " + lowerSensorAverage);
                    System.out.println("Upper output: " + upperOut + "  Lower output: " + lowerOut);
                    System.out.println(i);
                }

                if (Data.getInstance().getUserSetUpper() == upperOut) {
                    Data.getInstance().setCurrUpperTemp(upperOut);
                    Data.getInstance().setUpperStatus(false);
                }
                if (Data.getInstance().getUserSetLower() == lowerOut) {
                    Data.getInstance().setCurrLowerTemp(lowerOut);
                    Data.getInstance().setLowerStatus(false);
                }


                i++;
            }


        }

    }
}
