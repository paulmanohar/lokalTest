package com.lokal.lokaltest.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.bluelinelabs.logansquare.LoganSquare;
import com.lokal.lokaltest.R;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class CodeSnippet {

    //Time AM or PM
    private String PM = "PM";
    private String AM = "AM";
    private String TAG = getClass().getSimpleName();
    private Context mContext;

    public CodeSnippet(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Checking the internet connectivity
     *
     * @return true if the connection is available otherwise false
     */
    public boolean hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    /*public ByteArrayBody getCompressedImage(String path) {

        Bitmap imageBitmap = getBitmap(path);

        if (imageBitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayBody bab;
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            bab = new ByteArrayBody(data, "" + System.currentTimeMillis() + "displayPicture.jpg");
            return bab;
        }
        return null;
    }

    public ByteArrayBody getCompressedImage(File file) {
        Bitmap imageBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (imageBitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ByteArrayBody bab;
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] data = bos.toByteArray();
            bab = new ByteArrayBody(data, "" + System.currentTimeMillis() + " displayPicture.jpg");
            return bab;
        }
        return null;
    }*/

    /***/
    public <T> String getJsonStringFromObject(T object) {
        try {
            return LoganSquare.serialize(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***/
    public <T> T getObjectFromJsonString(String jsonString, Class<T> classType) {
        try {
            return LoganSquare.parse(jsonString, classType);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Check which type of connection the device is connected to
     */
    public String whichNetworkConnection() {

        ConnectivityManager cm =(ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo wifiNetwork = cm.getActiveNetworkInfo();
        if (wifiNetwork != null && wifiNetwork.isConnectedOrConnecting()) {
            return mContext.getString(R.string.network_wifi);
        }

        NetworkInfo mobileNetwork = cm.getActiveNetworkInfo();
        if (mobileNetwork != null && mobileNetwork.isConnectedOrConnecting()) {
            return mContext.getString(R.string.network_mobile);
        }

        return null;
    }

    public String getNetworkType() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork && activeNetwork.isConnectedOrConnecting()) {
            return activeNetwork.getTypeName();
        }
        return null;
    }

    private Intent getSettingsIntent(String settings) {
        return new Intent(settings);
    }

    private void startActivityBySettings(Context context, String settings) {
        context.startActivity(getSettingsIntent(settings));
    }

    private void startActivityBySettings(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public void showGpsSettings(Context context) {
        startActivityBySettings(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

    public void showNetworkSettings() {
        Intent chooserIntent = Intent.createChooser(getSettingsIntent(Settings.ACTION_DATA_ROAMING_SETTINGS),
                "Complete action using");
        List<Intent> networkIntents = new ArrayList<>();
        networkIntents.add(getSettingsIntent(Settings.ACTION_WIFI_SETTINGS));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, networkIntents.toArray(new Parcelable[]{}));
        startActivityBySettings(mContext, chooserIntent);
    }

    public boolean isSpecifiedDelay(long exisingTime, long specifiedDelay) {
        return specifiedDelay >= (Calendar.getInstance().getTimeInMillis() - exisingTime);
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    public void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    public boolean isNull(Object object) {
        return null == object || object.toString().compareTo("null") == 0;
    }

    public final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean isAboveMarshmallow() {
        int currentapiVersion = Build.VERSION.SDK_INT;
        // Do something for marshmallow and above versions
// do something for phones running an SDK before marshmallow
        return currentapiVersion >= Build.VERSION_CODES.M;
    }

    public boolean isAboveLollipop() {
        int currentapiVersion = Build.VERSION.SDK_INT;
        // Do something for marshmallow and above versions
// do something for phones running an SDK before marshmallow
        return currentapiVersion >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Fetch the drawable object for the given resource id.
     *
     * @param resourceId to which the value is to be fetched.
     * @return drawable object for the given resource id.
     */

    public Drawable getDrawable(int resourceId) {
        return ResourcesCompat.getDrawable(mContext.getResources(), resourceId, null);
    }

    /**
     * Returns the current date.
     *
     * @return Current date
     */

    public Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Fetch the string value from a xml file returns the value.
     *
     * @param resId to which the value has to be fetched.
     * @return String value of the given resource id.
     */

    public String getString(int resId) {
        return mContext.getResources().getString(resId);
    }

    /**
     * Fetch the color value from a xml file returns the value.
     *
     * @param colorId to which the value has to be fetched.
     * @return Integer value of the given resource id.
     */

    public int getColor(int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }

    public boolean isValidLocation(double latitude, double longitude) {
        if (latitude == 0.0 || longitude == 0.0) {
            return false;
        }
        return true;
    }

    public String getTotalAmount(String serviceCost) {
        /// TODO: 3/7/2017  need to calulate total amount if tax applied

        return serviceCost;
    }


    private interface OnGooglePlayServiceListener {
        void onInstallingService();

        void onCancelServiceInstallation();
    }

    public static RequestBody getRequestBody(String data) {
        if (!(TextUtils.isEmpty(data))) {
            return RequestBody.create(MediaType.parse("text/plain"), data);
        }
        return null;
    }

    public static RequestBody getRequestBody(Boolean data) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data));
    }



    public static RequestBody getRequestBody(int data) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data));
    }

    public static RequestBody getRequestBody(float data) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data));
    }

    public static RequestBody getRequestBody(File data) {
        if (data != null) {
            return RequestBody.create(MediaType.parse("image/*"), data);
        }
        return null;
    }

}
