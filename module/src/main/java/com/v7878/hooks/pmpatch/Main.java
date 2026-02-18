package com.v7878.hooks.pmpatch;

import static com.v7878.zygisk.ZygoteLoader.PACKAGE_SYSTEM_SERVER;

import android.util.Log;

import com.v7878.r8.annotations.DoNotObfuscate;
import com.v7878.r8.annotations.DoNotObfuscateType;
import com.v7878.r8.annotations.DoNotShrink;
import com.v7878.r8.annotations.DoNotShrinkType;
import com.v7878.zygisk.ZygoteLoader;
import com.v7878.hooks.pmpatch.hooks.LocationHook;


@DoNotShrinkType
@DoNotObfuscateType
public class Main {
    public static String TAG = "LOCATION_MOCK_HIDE";

    @SuppressWarnings("unused")
    @DoNotShrink
    @DoNotObfuscate
    public static void premain() {
        EntryPoint.premain();
    }

    @SuppressWarnings({"unused", "ConfusingMainMethod"})
    @DoNotShrink
    @DoNotObfuscate
    public static void main() {
        String packageName = ZygoteLoader.getPackageName();
        Log.i(TAG, "Injected into " + packageName);
        try {
            EntryPoint.mainCommon();
            if (PACKAGE_SYSTEM_SERVER.equals(packageName)) {
                Log.i(TAG, "Initializing System Server Hooks...");
                // 這裡原本是 SystemServerInit.init();
                // 改成你的 Hook
                LocationHook.initSystemServer(); 
            }
            else { 
                Log.i(TAG, "Initializing App Hooks for: " + packageName);
                // 這裡原本是 ApplicationInit.init();
                // 改成你的 Hook
                LocationHook.initAppHooks();
            }
        } catch (Throwable th) {
            Log.e(TAG, "Exception", th);
        }
        Log.i(TAG, "Done");
    }
}
