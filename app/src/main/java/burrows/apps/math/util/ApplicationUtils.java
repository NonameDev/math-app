package burrows.apps.math.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import burrows.apps.math.BuildConfig;

/**
 * Application utilities.
 *
 * @author <a href="mailto:jared.burrows@ngc.com">Jared Burrows</a>
 * @since 0.0.1
 */
public class ApplicationUtils {

    /** Logging tag. */
    private static final String TAG = ApplicationUtils.class.getSimpleName();

    /**
     * Add text to clipboard. Supports all APIs.
     *
     * @param context Context.
     * @param text    Message to be copied.
     */
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void addToTextClipBoard(final Context context, final String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            final android.content.ClipData clipData = android.content.ClipData.newPlainText(context.getPackageName().replace("burrows.apps.", ""), text);
            clipboardManager.setPrimaryClip(clipData);
        } else {
            final android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(text);
        }
    }

    /**
     * Turn on Strict Mode for testing debug builds.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void turnOnStrict() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    /**
     * Get version name and version code.
     *
     * @param context Context
     *
     * @return Version name of the application.
     */
    public static String getVersionRelease(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (final NameNotFoundException exception) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "getVersionRelease");
                exception.printStackTrace();
            }
            return "";
        }
    }
}
