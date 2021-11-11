
package com.example.protodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.protodo.FragmentListToDo
//import com.example.projecttodo.FragmentToDo
//import com.example.projecttodo.R
import com.example.protodo.R
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (currentFragment == null) {
            val fragment = FragmentListToDo()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .commit()
        }
    }
}
