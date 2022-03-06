package burrows.apps.math.util;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import burrows.apps.math.BuildConfig;
import burrows.apps.math.test.TestBase;


@RunWith(AndroidJUnit4.class)
public class AppUtilsTest extends TestBase {

    @Test
    public void test_constructor() {
        AppUtils appUtils = new AppUtils();
        MatcherAssert.assertThat(appUtils, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_addToTextClipBoard_invalid() {
        MatcherAssert.assertThat(AppUtils.addToTextClipBoard(null, "test"), Matchers.is(false));
    }

    @Test
    public void test_addToTextClipBoard_valid() {
        MatcherAssert.assertThat(AppUtils.addToTextClipBoard(RuntimeEnvironment.application, "test"), Matchers.is(true));
        MatcherAssert.assertThat(AppUtils.addToTextClipBoard(RuntimeEnvironment.application, null), Matchers.is(true));
    }

    @Test
    public void test_createShareIntent() {
        Intent intent = AppUtils.createShareIntent("package");
        MatcherAssert.assertThat(intent.getAction(), Matchers.is(Intent.ACTION_SEND));
        MatcherAssert.assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_NEW_DOCUMENT, Matchers.not(0));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_SUBJECT), Matchers.is("Check out the new app I downloaded!"));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_TEXT), Matchers.is(CoreMatchers.containsString("Take a look at: ")));
    }

    @Test
    public void test_getVersionRelease_invalid() {
        MatcherAssert.assertThat(AppUtils.getVersionRelease(null), Matchers.is(""));
    }

    @Test
    public void test_getVersionRelease_valid() {
        MatcherAssert.assertThat(AppUtils.getVersionRelease(RuntimeEnvironment.application), Matchers.is("1.0"));
    }

    @Test
    public void test_turnOnStrict() {
        AppUtils.turnOnStrict();
    }

    @Test
    public void test_getPixels() {
        MatcherAssert.assertThat(AppUtils.getPixels(RuntimeEnvironment.application, 60), Matchers.is(60));
    }
}
