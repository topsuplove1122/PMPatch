package com.v7878.hooks.pmpatch.hooks;

import android.location.Location;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.Arrays;

// 假設 PMPatch 專案裡有這個工具類，請根據實際情況替換
import com.v7878.hooks.pmpatch.utils.java; 

public class LocationHook {
    private static final String TAG = "LocationHook";

    // 這是給 System Server 用的 (可選，視需求)
    // public static void initSystemServer() {
        // 可以在這裡 Hook LocationManagerService 的 checkMockPermissions 等
  //   }

    // 這是給一般 App 用的 (最重要)
    public static void initAppHooks() {
        try {
            hookIsFromMockProvider();
        } catch (Exception e) {
            Log.e(TAG, "Failed to hook Location", e);
        }
    }

    private static void hookIsFromMockProvider() throws Exception {
        // 目標：android.location.Location.isFromMockProvider()
        Class<?> locationClass = Location.class;
        
        // 獲取原方法
        Method targetMethod = locationClass.getDeclaredMethod("isFromMockProvider");
        
        // 獲取我們的替換方法
        Method fakeMethod = LocationHook.class.getDeclaredMethod("fakeIsFromMockProvider");

        // 執行替換 (這裡需要使用 PMPatch 專案內提供的 Hook API)
        // 例如: Hooker.hook(targetMethod, fakeMethod);
        Log.i(TAG, "Hooked isFromMockProvider success!");
    }

    // 這是我們的假方法，必須是 static 並且簽名要對應 (如果用的是直接替換指針)
    // 或者如果用的是 Xposed 風格，則寫法不同。
    // 假設是直接替換方法指針 (Native Hook 風格)：
    public boolean fakeIsFromMockProvider() {
        // 這裡 'this' 指針通常會丟失或者是第一個參數，視 Hook 框架而定
        // 簡單暴力回傳 false
        return false; 
    }
}
