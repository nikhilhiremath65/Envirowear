package components;

import org.junit.Test;

import static org.junit.Assert.*;

public class OtherSafetyChecksTest {

    OtherSafetyChecks otherSafetyChecks = new OtherSafetyChecks("LOWER");

    @Test
    public void testGetAverage() throws  Exception{
        assert otherSafetyChecks.getAverage(71.6,77) == 74.3;
    }

}