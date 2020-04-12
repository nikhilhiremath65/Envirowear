package Envirowear;

public class ThermoFirstLaw {

    public float  TempChange(float oldTemp)
    {
        double newTemp, energyReleased = 406, jacketMass = 1.1, specficHeatCapacity = 1400 ;
        newTemp = (energyReleased / (jacketMass * specficHeatCapacity)) + oldTemp;
        return (float) newTemp;
    }
}
