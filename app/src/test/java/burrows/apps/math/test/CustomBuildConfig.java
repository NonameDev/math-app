package burrows.apps.math.test;

import burrows.apps.math.BuildConfig;

/**
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 * @since 0.0.1
 */
// Fix for android.content.res.Resources$NotFoundException: no such label burrows.apps.mathapp:string/app_name
// https://github.com/robolectric/robolectric/issues/1623#issuecomment-93726148
// Remove this once Robolectric 3 comes out
public final class CustomBuildConfig {
    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final String APPLICATION_ID = "burrows.apps.math";
    public static final String BUILD_TYPE = BuildConfig.BUILD_TYPE;
    public static final String FLAVOR = BuildConfig.FLAVOR;
    public static final int VERSION_CODE = BuildConfig.VERSION_CODE;
    public static final String VERSION_NAME = BuildConfig.VERSION_NAME;
}