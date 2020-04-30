package interfaces;

public interface ISimulator {
    public double simulateJacketTempChange(double userSetTemp, double sensorAverage, double tempEnv,boolean power);
    public double simulateBodyTempChange(double bodyTemp,double jacketTemp);
}
