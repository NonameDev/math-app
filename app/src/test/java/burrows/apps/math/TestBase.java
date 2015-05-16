package burrows.apps.math;

import org.junit.Before;
import org.robolectric.shadows.ShadowApplication;

import static org.robolectric.RuntimeEnvironment.application;
import static org.robolectric.Shadows.shadowOf;
import static org.robolectric.util.ReflectionHelpers.setStaticField;

public class TestBase {

    String GOOGLE_ANALYTICS_SERVICE = "com.google.android.gms.analytics.service.START";

    @Before
    public void setUp() {
        // Turn off Google Analytics
        ShadowApplication shadowApplication = shadowOf(application);
        shadowApplication.declareActionUnbindable(GOOGLE_ANALYTICS_SERVICE);

        // Use the debug configuration
        setStaticField(BuildConfig.class, "DEBUG", false);
    }
}
