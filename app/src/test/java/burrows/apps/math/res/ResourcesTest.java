package burrows.apps.math.res;

import android.annotation.TargetApi;
import android.os.Build.VERSION_CODES;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import burrows.apps.math.R;
import burrows.apps.math.test.TestBase;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.robolectric.RuntimeEnvironment.application;

/**
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 * @since 0.0.1
 */
@RunWith(RobolectricGradleTestRunner.class)
public class ResourcesTest extends TestBase {

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Test
    public void test_imageResources() {
        assertThat(application.getDrawable(R.mipmap.ic_launcher), not(nullValue()));
    }

    @Test
    public void test_stringResources() {
        assertThat(application.getString(R.string.app_name), is("MathApp"));
    }
}
