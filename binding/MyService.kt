package com.example.binding

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    //objeto binder, se declara una clase interna abajo que herede de Binder()
    private val binder = LocalBinder()
    //al hacer bind se devuelve el objeto binder
    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(applicationContext, "Bind Realizado", Toast.LENGTH_SHORT).show()
        return binder
    }
    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService 
    }
    //función interna del servicio
    fun HolaMundo(){
        Toast.makeText(applicationContext, "Hola mundo", Toast.LENGTH_SHORT).show()
    }
}
