package burrows.apps.math.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;

import burrows.apps.math.R;
import burrows.apps.math.test.TestBase;


/**
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 * @since 0.0.1
 */
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest extends TestBase {

    private MainActivity activity;
    private ShadowActivity shadowActivity;

    @Before
    public void setUp() {
        super.setUp();

        activity = Robolectric.setupActivity(MainActivity.class);
        shadowActivity = Shadows.shadowOf(activity);
        MatcherAssert.assertThat(activity, CoreMatchers.not(Matchers.nullValue()));
    }

    // --------------------------------------------
    // Activity Life Cycle States
    // --------------------------------------------

    @Test
    public void test_activity_create_start_onresume_visible() {
        final MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().get();
        MatcherAssert.assertThat(mainActivity, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_activity_create_start_onresume_visible_pause() {
        final MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().resume().visible().pause().get();
        MatcherAssert.assertThat(mainActivity, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_activity_create_start_pause_visible() {
        final MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().pause().get();
        MatcherAssert.assertThat(mainActivity, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_activity_create_start_destroy_visible() {
        final MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().destroy().get();
        MatcherAssert.assertThat(mainActivity, Matchers.not(Matchers.nullValue()));
    }

    // --------------------------------------------
    // Views, onClickListener
    // --------------------------------------------

    @Test
    public void test_coordinatorLayout() {
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.main_content);
        MatcherAssert.assertThat(coordinatorLayout, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_appBarLayout() {
        final AppBarLayout appBarLayout = (AppBarLayout) activity.findViewById(R.id.appbar);
        MatcherAssert.assertThat(appBarLayout, Matchers.not(Matchers.nullValue()));
    }

    @Test
    public void test_toolbar() {
        final Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        MatcherAssert.assertThat(toolbar, Matchers.not(Matchers.nullValue()));
    }

    // --------------------------------------------
    // ActionBar/Toolbar - OptionsMenu
    // --------------------------------------------

    @Test
    public void test_menu_share_app() {
        // must click on the menu button
        shadowActivity.clickMenuItem(R.id.menu_share);

        activity.onOptionsItemSelected(new RoboMenuItem((R.id.menu_share)));
    }

    @Test
    public void test_menu_version_info() {
        // must click on the menu button
        shadowActivity.clickMenuItem(R.id.menu_version_info);

        activity.onOptionsItemSelected(new RoboMenuItem((R.id.menu_version_info)));
    }
}
