package burrows.apps.math.res;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;

import burrows.apps.math.R;
import burrows.apps.math.test.TestBase;

/**
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 * @since 0.0.1
 */
@RunWith(RobolectricGradleTestRunner.class)
public class ResourcesTest extends TestBase {

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Test
    public void test_imageResources() {
        MatcherAssert.assertThat(RuntimeEnvironment.application.getDrawable(R.mipmap.ic_launcher), CoreMatchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_stringResources() {
        MatcherAssert.assertThat(RuntimeEnvironment.application.getString(R.string.app_name), Matchers.is("MathApp"));
    }
}
