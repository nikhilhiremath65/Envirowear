package components;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActuatorControllerTest {
    ActuatorController actuatorController = new ActuatorController("UPPER");
    @Test
    public void heatActionTest() throws Exception{
        assertEquals("HEAT",
                actuatorController.actuatorAction(35,20,25,true));
    }

    @Test
    public void coolActionTest() throws Exception{
        assertEquals("COOL",
                actuatorController.actuatorAction(22,35,20,false));
    }

    @Test
    public void noneActionTest() throws Exception{
        assertEquals("NONE",
                actuatorController.actuatorAction(35,35.25,20,true));
    }

    @Test
    public void noneActionTest2() throws Exception{
        assertEquals("NONE",
                actuatorController.actuatorAction(35,34.75,20,true));
    }




}