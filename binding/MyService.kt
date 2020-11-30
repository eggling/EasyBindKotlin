package com.example.binding

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    private val binder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(applicationContext, "Bind Realizado", Toast.LENGTH_SHORT).show()
        return binder
    }
    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    fun HolaMundo(){
        Toast.makeText(applicationContext, "Hola mundo", Toast.LENGTH_SHORT).show()
    }
}
