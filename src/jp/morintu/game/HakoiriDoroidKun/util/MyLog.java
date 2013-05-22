/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [著作権表示の変更・削除、または本ファイルのヘッダの削除を禁ずる。]
 */
package jp.morintu.game.HakoiriDoroidKun.util;

import android.util.Log;

public final class MyLog
{
    private final static boolean RELEASE = false;

    public static void d(boolean DEBUG, String TAG, String str) {
        if (RELEASE) {
            return;
        }

        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public static void i(boolean DEBUG, String TAG, String str) {
        if (RELEASE) {
            return;
        }

        if (DEBUG) {
            Log.i(TAG, str);
        }
    }

    public static void e(boolean DEBUG, String TAG, String str) {
        if (RELEASE) {
            return;
        }

        if (DEBUG) {
            Log.e(TAG, str);
        }
    }
}
