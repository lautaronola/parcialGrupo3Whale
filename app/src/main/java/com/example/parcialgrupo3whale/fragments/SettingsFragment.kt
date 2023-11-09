package com.example.parcialgrupo3whale.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import android.view.View
import android.content.Context
import com.example.parcialgrupo3whale.R
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.SwitchPreferenceCompat
import com.example.parcialgrupo3whale.activities.MainActivity
class SettingsFragment : PreferenceFragmentCompat() {

    //     Inflo el diseño de preferencias con root_pref..
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    // ocultamos el actionBar al mostrar el fr
    override fun onResume() {
        super.onResume()

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.hide()
    }

    // mostramos el actionBar al salir del fr
    override fun onPause() {
        super.onPause()

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()

        val goBackButton = findPreference<Preference>("back_key")
        goBackButton?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            navController.popBackStack(R.id.nav_graph_xml, false)

            if (activity is MainActivity) (activity as MainActivity).setBottomNavViewVisibility(View.VISIBLE)

            navController.navigateUp()
            true
        }

        val toggleNightMode = findPreference<SwitchPreferenceCompat>("dark_mode")
        toggleNightMode?.setOnPreferenceChangeListener { _, newValue ->
            val nightMode = if (newValue as Boolean) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(nightMode)

            // Save the night mode preference
            val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("night_mode", newValue)
            editor.apply()

            true
        }
    }
}