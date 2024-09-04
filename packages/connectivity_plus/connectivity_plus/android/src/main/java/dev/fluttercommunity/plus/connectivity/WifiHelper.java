package dev.fluttercommunity.plus.connectivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import androidx.core.content.ContextCompat;

public class WifiHelper {
    private final Context context;

    public WifiHelper(Context context) {
        this.context = context;
    }

    public String getCurrentSsid() {
        // Kiểm tra quyền ACCESS_FINE_LOCATION
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return "Quyền vị trí chưa được cấp";
        }

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                String ssid = wifiInfo.getSSID();
                if (ssid != null && !ssid.equals("<unknown ssid>")) {
                    return ssid.replace("\"", "");
                } else {
                    return "SSID không xác định";
                }
            }
        }
        return "Không thể lấy thông tin Wi-Fi";
    }
}

