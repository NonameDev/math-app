package burrows.apps.math.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import burrows.apps.math.R;
import burrows.apps.math.util.AppUtils;

/**
 * The main {@link android.app.Activity} for Math App.
 *
 * @author <a href="mailto:jaredsburrows@gmail.com">Jared Burrows</a>
 * @since 0.0.1
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    /** Padding for About me. */
    private static final int TEN = 10;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);

        // Use the ToolBar instead of the ActionBar
        final Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

    }

    @Override
    protected void onPause() {
        // Close about me dialog
        if (this.alertDialog != null && this.alertDialog.isShowing()) {
            this.alertDialog.dismiss();
        }

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);

        // This is the share action provider - dropdown of share.
        final ShareActionProvider shareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(menu.findItem(R.id.menu_share));
        shareAction.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        shareAction.setShareIntent(AppUtils.createShareIntent(this.getPackageName()));

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * http://tools.android.com/tips/non-constant-fields
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int itemId = item.getItemId();

        try {
            // About dialog with version info
            if (itemId == R.id.menu_version_info) {

                this.alertDialog = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(this.getMessage())
                        .setTitle(this.getString(R.string.app_name))
                        .create();
                this.alertDialog.show();
                return true;
            }
        } catch (final Exception e) {
            Log.e(TAG, "onOptionsItemSelected" + Log.getStackTraceString(e));

            Toast.makeText(this, "Could not open About dialog", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Get message for Dialog in About me.
     *
     * @return Textview
     */
    private TextView getMessage() {
        final String version = this.getString(R.string.text_version) + " " + AppUtils.getVersionRelease(this);
        final String copyRight = "\u00A9 " + Calendar.getInstance().get(Calendar.YEAR);
        final String trademark = "<a href=\"http://www.burrowsapps.com\">Burrows Applications</a>\u2122";
        final String breakLine = "<br/><br/>";
        final int padding = AppUtils.getPixels(this, TEN);

        final TextView message = new TextView(this);
        message.setTextColor(Color.BLACK);
        message.setLinkTextColor(Color.BLUE);
        message.setText(Html.fromHtml(version + breakLine + copyRight + " " + trademark));
        message.setMovementMethod(LinkMovementMethod.getInstance());
        message.setPadding(padding, padding, padding, padding);
        return message;
    }
}
