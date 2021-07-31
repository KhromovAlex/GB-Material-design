package com.example.gbmaterialdesign.presentation.view

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val outValue = TypedValue()
        requireActivity().theme.resolveAttribute(R.attr.themeName, outValue, true)

        when (outValue.string) {
            getString(R.string.default_theme) -> {
                binding.themeChipGroup.check(R.id.default_chip)
            }
            getString(R.string.green_theme) -> {
                binding.themeChipGroup.check(R.id.green_chip)
            }
            getString(R.string.pink_theme) -> {
                binding.themeChipGroup.check(R.id.pink_chip)
            }
        }

        val clickListener: View.OnClickListener = View.OnClickListener {
            when (it.id) {
                R.id.default_chip -> {
                    val preferences =
                        requireActivity().getPreferences(AppCompatActivity.MODE_PRIVATE)
                    preferences.edit().putInt("theme", R.style.Theme_GBMaterialDesign).apply()
                    requireActivity().recreate()
                }
                R.id.pink_chip -> {
                    val preferences =
                        requireActivity().getPreferences(AppCompatActivity.MODE_PRIVATE)
                    preferences.edit().putInt("theme", R.style.Theme_Pink).apply()
                    requireActivity().recreate()
                }
                R.id.green_chip -> {
                    val preferences =
                        requireActivity().getPreferences(AppCompatActivity.MODE_PRIVATE)
                    preferences.edit().putInt("theme", R.style.Theme_Green).apply()
                    requireActivity().recreate()
                }
            }
        }

        for (i in 0 until binding.themeChipGroup.childCount) {
            binding.themeChipGroup[i].setOnClickListener(clickListener)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(
                    requireActivity().supportFragmentManager,
                    "tag"
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
