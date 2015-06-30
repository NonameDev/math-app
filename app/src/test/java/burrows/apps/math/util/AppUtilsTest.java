package burrows.apps.math.util;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build.VERSION_CODES;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import burrows.apps.math.BuildConfig;
import burrows.apps.math.test.TestBase;


@SuppressWarnings("deprecation")
@RunWith(RobolectricGradleTestRunner.class)
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

    @TargetApi(VERSION_CODES.LOLLIPOP)
    @Test
    public void test_createShareIntent() {
        Intent intent = AppUtils.createShareIntent("package");
        MatcherAssert.assertThat(intent.getAction(), Matchers.is(Intent.ACTION_SEND));
        MatcherAssert.assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_NEW_DOCUMENT, Matchers.not(0));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_SUBJECT), Matchers.is("Check out the new app I downloaded!"));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_TEXT), Matchers.is(CoreMatchers.containsString("Take a look at: ")));
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = VERSION_CODES.JELLY_BEAN)
    public void test_createShareIntent_lowAPI_googleplay_missing_package_misisng_store() {
        Intent intent = AppUtils.createShareIntent(null);
        MatcherAssert.assertThat(intent.getAction(), Matchers.is(Intent.ACTION_SEND));
        MatcherAssert.assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, Matchers.not(0));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_SUBJECT), Matchers.is("Check out the new app I downloaded!"));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_TEXT), Matchers.is("Take a look at: " + AppUtils.GOOGLE_PLAY_URL + null));
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = VERSION_CODES.JELLY_BEAN)
    public void test_createShareIntent_lowAPI_googleplay_missing_package() {
        Intent intent = AppUtils.createShareIntent(null);
        MatcherAssert.assertThat(intent.getAction(), Matchers.is(Intent.ACTION_SEND));
        MatcherAssert.assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, Matchers.not(0));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_SUBJECT), Matchers.is("Check out the new app I downloaded!"));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_TEXT), Matchers.is("Take a look at: " + AppUtils.GOOGLE_PLAY_URL + null));
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = VERSION_CODES.JELLY_BEAN)
    public void test_createShareIntent_lowAPI_googleplaymisisng_store() {
        Intent intent = AppUtils.createShareIntent("blah");
        MatcherAssert.assertThat(intent.getAction(), Matchers.is(Intent.ACTION_SEND));
        MatcherAssert.assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, Matchers.not(0));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_SUBJECT), Matchers.is("Check out the new app I downloaded!"));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_TEXT), Matchers.is("Take a look at: " + AppUtils.GOOGLE_PLAY_URL + "blah"));
    }

    @Test
    @Config(constants = BuildConfig.class, sdk = VERSION_CODES.JELLY_BEAN)
    public void test_createShareIntent_lowAPI_googleplay() {
        Intent intent = AppUtils.createShareIntent("package");
        MatcherAssert.assertThat(intent.getAction(), Matchers.is(Intent.ACTION_SEND));
        MatcherAssert.assertThat(intent.getFlags() & Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, Matchers.not(0));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_SUBJECT), Matchers.is("Check out the new app I downloaded!"));
        MatcherAssert.assertThat((String) intent.getExtras().get(Intent.EXTRA_TEXT), Matchers.is("Take a look at: " + AppUtils.GOOGLE_PLAY_URL + "package"));
    }

    @Test
    public void test_getVersionRelease_invalid() {
        MatcherAssert.assertThat(AppUtils.getVersionRelease(null), Matchers.is(""));
    }

    @Test
    public void test_getVersionRelease_valid() {
        if (BuildConfig.DEBUG) {
            MatcherAssert.assertThat(AppUtils.getVersionRelease(RuntimeEnvironment.application), Matchers.is("1-DEBUG"));
        } else {
            MatcherAssert.assertThat(AppUtils.getVersionRelease(RuntimeEnvironment.application), Matchers.is("1.0"));
        }
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
