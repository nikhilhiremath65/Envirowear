package view;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DegCtoDegF {
	public double degreeCtoF(double c) {
		double f = (c * 9/5) + 32;
		BigDecimal bd = new BigDecimal(f).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	public double degreeFtoC(double f) {
		double c = (f - 32) * 5/9;
		BigDecimal bd = new BigDecimal(c).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
