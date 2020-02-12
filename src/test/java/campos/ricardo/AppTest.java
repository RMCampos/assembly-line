package campos.ricardo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testTime() {
        Time time = Time.fromString("2:00 pm");
        time.addMinutes(20);
        assertEquals("14:20", TimeUtil.format(time));

        time.addMinutes(50);
        assertEquals("15:10", TimeUtil.format(time));

        time.addMinutes(59);
        assertEquals("16:09", TimeUtil.format(time));
    }

    public void testTimeUtil() {
        assertEquals("00:00", TimeUtil.format(Time.ZERO));

        Time time1115 = Time.fromString("11:15");
        assertEquals(Time.ZERO, time1115);
        assertEquals("", TimeUtil.format(null));

        Time time = Time.fromString("2:00 pm");
        assertEquals("14:00", TimeUtil.format(time));
    }
}
