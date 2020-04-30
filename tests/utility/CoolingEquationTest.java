package utility;

import junit.framework.TestCase;
import org.junit.Test;

public class CoolingEquationTest {
    CoolingEquation coolingEquation = new CoolingEquation();
    @Test
    public void testGetTempChangeExtremeEnv1() {
        assert  -0.029677736133294275 == (coolingEquation.getTempChange(96.8,77));
    }

    @Test
    public void testGetTempChangeExtremeEnv2() {
        assert  0.02428178410909254 == (coolingEquation.getTempChange(60.8,77));
    }

    @Test
    public void testGetTempChangeNormEnv() {
        assert  -0.005395952024201733 == (coolingEquation.getTempChange(80.6,77));
    }
}