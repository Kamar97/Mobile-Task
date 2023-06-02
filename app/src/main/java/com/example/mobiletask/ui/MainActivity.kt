package com.example.mobiletask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobiletask.R
import com.example.mobiletask.databinding.ActivityMainBinding
import com.example.mobiletask.ui.modules.ModulesFragment
import com.example.mobiletask.ui.students.StudentsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // click listeners
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.buttonGetModules.setOnClickListener {
            openModulesFragment()
        }
        binding.buttonGetStudents.setOnClickListener {
            openStudentsFragment()
        }
    }

    private fun openStudentsFragment() {
        navigateToFragment(StudentsFragment())
    }

    private fun openModulesFragment() {
        navigateToFragment(ModulesFragment())
    }

    private fun navigateToFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id,fragment)
            .commitAllowingStateLoss()
    }
}