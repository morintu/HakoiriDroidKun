/**
 * Copyright 2013 Morintu. All Rights Reserved.
 * Do not alter or remove
 * Copyright notices or this file header.
 * [���쌠�\���̕ύX�E�폜�A�܂��͖{�t�@�C���̃w�b�_�̍폜���ւ���B]
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
