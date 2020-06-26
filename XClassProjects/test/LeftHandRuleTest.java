import org.junit.Test;
import com.tylerroonprapunt.riemann.LeftHandRule;
import org.dalton.polyfun.Polynomial;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LeftHandRuleTest {
    @Test
    public void slice() {
        Polynomial poly = new Polynomial(2);
        LeftHandRule left = new LeftHandRule();
        assertThat(left.slice​(poly, 1, 2), is(1.0));
    }

    @Test
    public void slice2() {
        Polynomial poly = new Polynomial(new double[]{3,-6,3});
        LeftHandRule left = new LeftHandRule();
        assertThat(left.slice​(poly, 1, 2), is(0.0));
    }
}
