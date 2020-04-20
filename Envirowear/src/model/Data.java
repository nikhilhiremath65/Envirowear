package model;

import java.util.ArrayList;

import view.TempChangeObserver;

public class Data {
    private static Data dataObj;


    private double tBR;
    private double envTemp;

    private double userSetUTemp;
    private double userSetLTemp;
    private double upperBodyTemp;
    private double lowerBodyTemp;

    private ArrayList<Double> upperBodySensor1List;
    private ArrayList<Double> upperBodySensor2List;
    private ArrayList<Double> lowerBodySensor1List;
    private ArrayList<Double> lowerBodySensor2List;



    private boolean graphStatus;
    private boolean upperControllerStatus;
    private boolean lowerControllerStatus;

    private ArrayList<Double> upperBodyTempList;
    private ArrayList<Double> lowerBodyTempList;

    private Data() {


        this.tBR = 0;


        this.graphStatus = false;
        this.envTemp = 20;

        this.upperControllerStatus = false;
        this.lowerControllerStatus = false;

        this.upperBodyTempList = new ArrayList<Double>();
        this.lowerBodyTempList = new ArrayList<Double>();
    }

    public static Data getInstance() {
        if (dataObj == null) {
            dataObj = new Data();
        }
        return dataObj;
    }


    public double getEnvTemp() {
        return envTemp;
    }

    public void setEnvTemp(double envTemp) {
        this.envTemp = envTemp;
    }

    public void setUpperSensor1(ArrayList<Double> upperSensor1) {
        this.upperBodySensor1List = upperSensor1;
    }

    public ArrayList<Double> getUpperSensor2() {
        return upperBodySensor2List;
    }

    public void setUpperSensor2(ArrayList<Double> upperSensor2) {
        this.upperBodySensor2List = upperSensor2;
    }

    public ArrayList<Double> getLowerSensor1() {
        return lowerBodySensor1List;
    }

    public void setLowerSensor1(ArrayList<Double> lowerSensor1) {
        this.lowerBodySensor1List = lowerSensor1;
    }

    public ArrayList<Double> getLowerSensor2() {
        return lowerBodySensor2List;
    }

    public void setLowerSensor2(ArrayList<Double> lowerSensor2) {
        this.lowerBodySensor2List = lowerSensor2;
    }

    public void setCurrUpperTemp(double currUpperTemp) {
        if(this.graphStatus) {
            this.upperBodyTemp = currUpperTemp;
            String value = Double.toString(currUpperTemp);
            TempChangeObserver.getInstance().notifyObserver(0, value);

            this.upperBodyTempList.add((double) currUpperTemp);
            TempChangeObserver.getInstance().notifyGraphs(this.upperBodyTempList, true);
        }
    }

    public double getCurrLowerTemp() {
        return lowerBodyTemp;
    }

    public void setCurrLowerTemp(double currLowerTemp) {
        if(this.graphStatus) {
            this.lowerBodyTemp = currLowerTemp;
            String value = Double.toString(currLowerTemp);
            TempChangeObserver.getInstance().notifyObserver(1, value);

            this.lowerBodyTempList.add((double) currLowerTemp);
            TempChangeObserver.getInstance().notifyGraphs(this.lowerBodyTempList, false);
        }
    }


    public double getUserSetUpper() {
        return userSetUTemp;

    }

    public void setUserSetUpper(double userSetUpper) {
        this.userSetUTemp = userSetUpper;
    }

    public double getUserSetLower() {
        return userSetLTemp;
    }

    public void setUserSetLower(double userSetLower) {
        this.userSetLTemp = userSetLower;
    }


    public double getCurrUpperTemp() {
        return upperBodyTemp;
    }

    public boolean isUpperStatus() {
        return upperControllerStatus;
    }

    public void setUpperStatus(boolean upperStatus) {
        this.upperControllerStatus = upperStatus;
    }

    public boolean isLowerStatus() {
        return lowerControllerStatus;
    }

    public void setLowerStatus(boolean lowerStatus) {
        this.lowerControllerStatus = lowerStatus;
    }

    public ArrayList<Double> getUpperSensor1() {
        return upperBodySensor1List;
    }

    public boolean isGraphStatus() {
        return graphStatus;
    }

    public void setGraphStatus(boolean graphStatus) {
        this.graphStatus = graphStatus;
    }

    public double gettBR() {
        return tBR;
    }

    public void settBR(double tBR) {
        this.tBR = tBR;
    }
}