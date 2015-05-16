package burrows.apps.math.activity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import burrows.apps.math.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.robolectric.Robolectric.setupActivity;

/**
 * @author <a href="mailto:jared.burrows@ngc.com">Jared Burrows</a>
 * @since 0.0.1
 */
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest extends TestBase {

    MainActivity mainActivity;

    @Before
    public void setUp() {
        super.setUp();

        mainActivity = setupActivity(MainActivity.class);
    }

    @Test
    public void test_example() {
        assertThat(mainActivity, not(nullValue()));
    }
}
