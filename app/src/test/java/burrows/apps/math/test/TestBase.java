package burrows.apps.math.test;

import org.junit.Before;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.util.ReflectionHelpers;

import burrows.apps.math.BuildConfig;

/**
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 * @since 0.0.1
 */
public class TestBase {

    private static final String GOOGLE_ANALYTICS_SERVICE = "com.google.android.gms.analytics.service.START";

    @Before
    public void setUp() {
        // Turn off Google Analytics
        final ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
        shadowApplication.declareActionUnbindable(GOOGLE_ANALYTICS_SERVICE);

        // Use the debug configuration
        ReflectionHelpers.setStaticField(BuildConfig.class, "DEBUG", false);
    }
}
