import com.gradescope.jh61b.grader.GradedTestListenerJSON;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BodyTest.class, SpaceTest.class, NBodyTest.class})
public class RunTests {
    public static void main(String[] args) {
        JUnitCore r = new JUnitCore();
        r.addListener(new GradedTestListenerJSON());
        r.run(RunTests.class);
    }
}