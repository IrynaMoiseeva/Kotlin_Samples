package com.example.rynningtrackapp.ui
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rynningtrackapp.R
import com.example.rynningtrackapp.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.rynningtrackapp.other.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //will be called if our app was closed and we click on notification
        navigateToTrackingFragmentIfNeeded(intent)

        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        navHostFragment.findNavController()
            .addOnDestinationChangedListener{_, destination, _->
        when (destination.id) {
            R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                bottomNavigationView.visibility = View.VISIBLE
            else -> bottomNavigationView.visibility = View.GONE
        }
        }
    }
//if we click on Notification we will be here
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
    if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT)
    {
        navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
    }
}
}