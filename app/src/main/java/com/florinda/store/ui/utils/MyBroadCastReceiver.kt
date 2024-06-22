package com.florinda.store.ui.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadCastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        // run on main thread
        Toast.makeText(context , "Air Plane Changed" , Toast.LENGTH_LONG).show()
    }
}