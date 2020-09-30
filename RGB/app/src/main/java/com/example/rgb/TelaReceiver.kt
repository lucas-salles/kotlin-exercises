package com.example.rgb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TelaReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.sendBroadcast(Intent("TELA"));
    }
}