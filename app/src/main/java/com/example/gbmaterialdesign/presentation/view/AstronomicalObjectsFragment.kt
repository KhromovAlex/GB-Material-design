package com.example.gbmaterialdesign.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.databinding.FragmentAstronomicalObjectsBinding
import com.example.gbmaterialdesign.presentation.adapter.ViewPagerAdapter
import com.example.gbmaterialdesign.presentation.adapter.ZoomOutPageTransformer

class AstronomicalObjectsFragment : Fragment() {
    private var _binding: FragmentAstronomicalObjectsBinding? = null
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
        _binding = FragmentAstronomicalObjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.apply {
            adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
            setPageTransformer(true, ZoomOutPageTransformer())
        }
    }
}
