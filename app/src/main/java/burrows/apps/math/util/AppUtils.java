package burrows.apps.math.util;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.StrictMode;
import android.util.Log;
import android.util.TypedValue;

import burrows.apps.math.R;

/**
 * Application specific utilities.
 *
 * @author Jared Burrows
 */
public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    /** Google Play Market short. */
    public static final String GOOGLE_PLAY_MARKET = "market://details?id=";

    /** Google Play URL. */
    public static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=";

    /**
     * Add text to clipboard.
     *
     * @param context Context
     * @param text    Message
     */
    public static boolean addToTextClipBoard(final Context context, final String text) {
        try {
            final ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText(context.getString(R.string.app_name), text));
            return true;
        } catch (final Exception e) {
            // Workaround for some Android 4.3 devices, where writing to the clipboard manager raises an exception
            // if there is an active clipboard listener.
            // java.lang.IllegalStateException: beginBroadcast() called while already in a broadcast at
            Log.e(TAG, "addToTextClipBoard" + Log.getStackTraceString(e));
            return false;
        }
    }

    /**
     * Create the Share intent needed for the ShareActionProvider.
     */
    @SuppressLint("InlinedApi")
    @SuppressWarnings("deprecation")
    public static Intent createShareIntent(final String appPackage) {
        final Intent intent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .addFlags(VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP
                        ? Intent.FLAG_ACTIVITY_NEW_DOCUMENT
                        : Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                .putExtra(Intent.EXTRA_SUBJECT, "Check out the new app I downloaded!");

        // Default to Google Play
        if (appPackage == null) {
            return intent.putExtra(Intent.EXTRA_TEXT, "Take a look at: " + GOOGLE_PLAY_URL + appPackage);
        }

        final String
                storeURL = GOOGLE_PLAY_URL;

        intent.putExtra(Intent.EXTRA_TEXT, "Take a look at: " + storeURL + appPackage);

        return intent;
    }

    /**
     * Get version name and version code.
     *
     * @param context Context
     *
     * @return Version release
     */
    public static String getVersionRelease(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (final Exception e) {
            Log.e(TAG, "getVersionRelease" + Log.getStackTraceString(e));
            return "";
        }
    }

    /**
     * Converts pixels to DP in order to make sure views look the same on all
     * devices.
     *
     * @param context pass reference
     * @param value   DP value
     *
     * @return integer
     */
    public static int getPixels(final Context context, final int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    /**
     * Turn on Strict Mode.
     */
    public static void turnOnStrict() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}
