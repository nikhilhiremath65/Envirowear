package components;

import org.junit.Test;

import static org.junit.Assert.*;

public class SafetyTest {

    Safety safety = new Safety("LOWER");

    @Test
    public void checkUserSetTemp() throws Exception{

        assertTrue(safety.checkUserSetTemp(75));
    }
    @Test
    public void checkUserSetTemp2() throws Exception{

        assertFalse(safety.checkUserSetTemp(33));
    }
    @Test
    public void checkUserSetTemp3() throws Exception{

        assertFalse(safety.checkUserSetTemp(111));
    }

    @Test
    public void sensorNullTempTest() throws Exception{
        assert safety.checkSensorNullTemp(27,24) == 27;
    }
}