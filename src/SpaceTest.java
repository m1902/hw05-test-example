import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gradescope.jh61b.grader.GradedTest;

@RunWith(Parameterized.class)
public class SpaceTest {

    @Parameters
    public static Object[] data() {
        return new Object[] { "uniform8.txt", "solarSystem.txt", "binaryStars.txt" };
    }

	private static final double DELTA = 1e-15;
    public String fileName;
    Space s;
    SpaceRef sr;

    public SpaceTest(String fileName)
    {
        this.fileName = fileName;
    }

    @Before
    public void setup(){
        sr = new SpaceRef(fileName);
    }

    @Test(timeout = 1000)
    @GradedTest(name = "Test setRadius", max_score = 0.5)
    public void testSetRadius() {

        s = new Space(fileName);

        assertEquals(s.radius, sr.radius, DELTA);
    }

    @Test(timeout = 1000)
    @GradedTest(name = "Test setBodies", max_score = 0.5)
    public void testSetBodies() {

	    s = new Space(fileName);

        assertEquals(sr.bodies.length, s.bodies.length);

	    for (int i = 0; i < sr.bodies.length; i++) {
            assertTrue(sr.bodies[i].equals(s.bodies[i]));
		}
    }

    @Test(timeout = 1000)
    @GradedTest(name = "Test setSimulate", max_score = 0.5)
    public void testSimulate()
	{
	    double timestep = 1;

	    s = new Space(fileName);

	    for (int j = 0; j < 1; j++) {
	        sr.simulate(timestep);
	        s.simulate(timestep);
	        
	        assertEquals(sr.bodies.length, s.bodies.length);

		    for (int i = 0; i < sr.bodies.length; i++) {
			    assertTrue(sr.bodies[i].equals(s.bodies[i]));
			}
		}
    }

}
