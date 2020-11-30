package com.example.binding

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //en esta parte se guarda en myBound true/false para detectar si está o no vinculado el servicio
    private lateinit var myService: MyService
    private var myBound: Boolean = false
    private val mySConection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyService.LocalBinder
            myService = binder.getService()
            myBound = true
        }
        override fun onServiceDisconnected(arg0: ComponentName) {
            myBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //aquí se inicia el servicio, y acto seguido se hace bind        
        val intent = Intent(applicationContext, MyService::class.java)
        startService(intent)
        Intent(this, MyService::class.java).also { intent ->
            bindService(intent, mySConection, Context.BIND_AUTO_CREATE)
        }
        
        button.setOnClickListener {
            //aquí se accede al metodo del servicio 
            //(nota: da error si se intenta ejecutar la función justo después de hacer bind, 
            //por eso esta asignado a un boton)            
           if (myBound) {myService.HolaMundo()}

        }
    }
}
