import org.junit.Test;
import org.dalton.polyfun.Polynomial;
import com.tylerroonprapunt.riemann.TrapezoidRule;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrapezoidRuleTest{
    @Test
    public void slice(){
        Polynomial poly = new Polynomial(2);
        TrapezoidRule trap = new TrapezoidRule();
        assertThat(trap.slice​(poly, 1, 2),is(2.5));
    }
}
