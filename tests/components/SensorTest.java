package components;

import org.junit.Test;

import static org.junit.Assert.*;

public class SensorTest {
    Sensor sensor = new Sensor(22);


    @Test
    public void sensorTest1() {
        assert  sensor.getTemp()==22;
    }

    @Test
    public void sensorTest2() {
        sensor.setTemp(25);
        assert sensor.getTemp()==25;
    }
}