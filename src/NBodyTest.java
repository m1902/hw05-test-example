import com.gradescope.jh61b.grader.GradedTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class NBodyTest
{

    @Parameters
    public static Object[] data()
    {
        return new Object[][]
        {
            {0.0, 2500.0, "solarSystem.txt"},
            {25000.0, 2500.0, "solarSystem.txt"},
            {50000.0, 2500.0, "solarSystem.txt"},
            {75000.0, 2500.0, "solarSystem.txt"},
            {25000.0, 2500.0, "binaryStars.txt"},
            {50000.0, 2500.0, "binaryStars.txt"},
            {10.0e4, 2500.0, "binaryStars.txt"},
            {25000.0, 2500.0, "uniform8.txt"},
            {50000.0, 2500.0, "uniform8.txt"},
            {10.0e4, 2500.0, "uniform8.txt"},
        };
    }

    public double simulationTime;
    public double timeStep;
    public String fileName;
    Space s;
    SpaceRef sr;

    public NBodyTest(double simulationTime, double timeStep, String fileName)
    {
        this.fileName = fileName;
        this.simulationTime = simulationTime;
        this.timeStep = timeStep;
    }

    @Before
    public void setup()
    {
        sr = new SpaceRef(fileName);
    }

    @Test(timeout = 1000)
    @GradedTest(name = "Test NBody testSimulate", max_score = 0.5)
    public void testSimulateNBody()
    {
        s = new Space(fileName);

        for (double elapsedTime = 0; elapsedTime < simulationTime; elapsedTime += timeStep)
        {

            sr.simulate(timeStep);
            s.simulate(timeStep);

            assertEquals(sr.bodies.length, s.bodies.length);

            for (int i = 0; i < sr.bodies.length; i++)
            {
                assertTrue(sr.bodies[i].equals(s.bodies[i]));
            }
        }
    }

    @Test(timeout = 1000)
    @GradedTest(name = "Test NBody testSimulateToString", max_score = 0.5)
    public void testSimulateToString1()
    {
        s = new Space(fileName);

        for (double elapsedTime = 0; elapsedTime < simulationTime; elapsedTime += timeStep)
        {
            sr.simulate(timeStep);
            s.simulate(timeStep);

            assertEquals(sr.toString(), s.toString());
        }
    }
}
