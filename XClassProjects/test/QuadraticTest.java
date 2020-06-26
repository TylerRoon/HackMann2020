import com.tylerroonprapunt.Quadratic;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QuadraticTest {
    @Test
    public void getA() {
        Quadratic q = new Quadratic(1.0, 2.0, 3.0);
        assertThat(q.getA(), is(1.0));
    }
    @Test
    public void getB() {
        Quadratic q = new Quadratic(1.0, 2.0, 3.0);
        assertThat(q.getB(), is(2.0));
    }
    @Test
    public void getC() {
        Quadratic q = new Quadratic(1.0, 2.0, 3.0);
        assertThat(q.getA(), is(3.0));
    }
    @Test
    public void hasRealRoots(){
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        assertThat(q.hasRealRoots(), is(true));
    }
    @Test
    public void numberOfRoots() {
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        assertThat(q.hasRealRoots(), is(1));
    }
    @Test
    public void getRootsArray() {
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        double root[] = new double[2];
        root[0] = -1;
        root[1] = -1;
        assertThat(q.getRootArray()[0],is(root[0]));
        assertThat(q.getRootArray()[1],is(root[1]));
    }
    @Test
    public void getAxisofSymmetry(){
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        assertThat(q.getAxisOfSymmetry(),is(-1.0));
    }
    @Test
    public void getExtremeValue(){
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        assertThat(q.getExtremeValue(),is(0.0));
    }
    @Test
    public void isMax(){
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        assertThat(q.isMax(),is(false));
    }
    @Test
    public void evaluateWith() {
        Quadratic q = new Quadratic(1.0, 2.0, 1.0);
        assertThat(q.evaluateWith(2.0),is(9.0));
    }
}
