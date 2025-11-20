package com.lucas.restaurantlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lucas.restaurantlist.features.login.LoginFragment
import com.lucas.restaurantlist.ui.theme.RestaurantListTheme

import com.lucas.restaurantlist.features.login.SessionManagerPreferences
import com.lucas.restaurantlist.features.storefeed.StoreFeedFragment
import org.koin.android.ext.android.inject

import androidx.databinding.DataBindingUtil
import com.lucas.restaurantlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val prefs: SessionManagerPreferences by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set up the Toolbar as ActionBar
        setSupportActionBar(binding.root.findViewById(R.id.toolbar))

        val fragment = if (prefs.getLoginToken().isNullOrEmpty()) {
            LoginFragment()
        } else {
            StoreFeedFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}