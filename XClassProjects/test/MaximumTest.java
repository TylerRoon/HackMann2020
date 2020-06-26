import org.junit.Test;
import com.tylerroonprapunt.riemann.MaximumRule;
import org.dalton.polyfun.Polynomial;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaximumTest {
    @Test
    public void slice(){
        Polynomial poly = new Polynomial(2);
        MaximumRule max = new MaximumRule();
        assertThat(max.slice​(poly, 1, 2),is(4.0));
    }
}
