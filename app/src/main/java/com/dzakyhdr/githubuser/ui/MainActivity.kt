package com.dzakyhdr.githubuser.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.dzakyhdr.githubuser.SettingPreference
import com.dzakyhdr.githubuser.databinding.ActivityMainBinding
import com.dzakyhdr.githubuser.ui.setting.SettingViewModel
import com.dzakyhdr.githubuser.ui.setting.SettingViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}