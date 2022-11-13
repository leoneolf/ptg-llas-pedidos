package com.ptg.llas_pedidos.core

import android.util.Log
import com.ptg.llas_pedidos.core.Constants.TAG

class Utils {
    companion object {
        fun print(e: Exception?) {
            Log.e(TAG, e?.message ?: e.toString())
        }
    }
}