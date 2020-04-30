package utility;

import junit.framework.TestCase;
import org.junit.Test;

public class HeatingEquationTest {
    HeatingEquation heatingEquation = new HeatingEquation();

    @Test
    public void testGetJacketTempChangeExtremeDiff1() {
        assert 0.2639999999999958== (heatingEquation.getJacketTempChange(77,96.8));
    }
    @Test
    public void testGetJacketTempChangeExtremeDiff2() {
        assert -0.19200000000000017 == (heatingEquation.getJacketTempChange(77, 62.6));
    }

    @Test
    public void testGetJacketTempChangeLowDiff() {
        assert -0.07200000000000273 == (heatingEquation.getJacketTempChange(77,71.6));
    }


}