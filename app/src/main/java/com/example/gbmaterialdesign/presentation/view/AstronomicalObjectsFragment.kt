package com.example.gbmaterialdesign.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gbmaterialdesign.databinding.FragmentAstronomicalObjectsBinding
import com.example.gbmaterialdesign.presentation.adapter.ViewPagerAdapter
import com.example.gbmaterialdesign.presentation.adapter.ZoomOutPageTransformer

class AstronomicalObjectsFragment : Fragment() {
    private var _binding: FragmentAstronomicalObjectsBinding? = null
    private val binding get() = _binding!!

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
