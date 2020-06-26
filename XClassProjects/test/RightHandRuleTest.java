import org.junit.Test;
import com.tylerroonprapunt.riemann.RightHandRule;
import org.dalton.polyfun.Polynomial;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RightHandRuleTest {
    @Test
    public void slice(){
        Polynomial poly = new Polynomial(2);
        RightHandRule right = new RightHandRule();
        assertThat(right.slice​(poly, 1, 3),is(4.0));
    }
}
