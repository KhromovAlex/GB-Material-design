package com.example.gbmaterialdesign.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.common.AppData
import com.example.gbmaterialdesign.common.GlideApp
import com.example.gbmaterialdesign.data.model.EarthServerResponseData
import com.example.gbmaterialdesign.databinding.FragmentEarthBinding
import com.example.gbmaterialdesign.presentation.viewmodel.EarthViewModel
import java.util.*


class EarthFragment : Fragment() {
    private val viewModel: EarthViewModel by lazy {
        ViewModelProviders.of(this).get(EarthViewModel::class.java)
    }
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            showLoading()
            viewModel.loadData()
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.buttonReload.setOnClickListener {
            viewModel.loadData()
        }

        binding.earthImage.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                binding.scrollContainer, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = binding.earthImage.layoutParams
            params.height =
                if (isExpanded) params.height * 2 else params.height / 2
            binding.earthImage.layoutParams = params
            binding.earthImage.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }

    }

    private fun renderData(appData: AppData<EarthServerResponseData>?) {
        when (appData) {
            is AppData.Success<EarthServerResponseData> -> {
                val serverResponseData = appData.data
                val nameImg = serverResponseData.image
                val date = serverResponseData.date
                val title = serverResponseData.title

                if (nameImg == null || date == null) {
                    showError()
                } else {
                    showSuccess(nameImg, date, title ?: "")
                }
            }
            is AppData.Loading -> {
                showLoading()
            }
            is AppData.Error -> {
                showError()
            }
        }
    }

    private fun showError() {
        binding.errorState.visibility = View.VISIBLE
        binding.loadingState.visibility = View.GONE
        binding.successState.visibility = View.GONE
    }

    private fun showSuccess(nameImg: String, date: Date, title: String) {
        binding.errorState.visibility = View.GONE
        binding.loadingState.visibility = View.GONE
        binding.successState.visibility = View.VISIBLE

        GlideApp.with(requireContext())
            .load(getUrlImage(nameImg, date))
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_cloud_off_24)
            .error(R.drawable.ic_baseline_cloud_off_24)
            .into(binding.earthImage)

        binding.earthTitle.text = title
    }

    private fun showLoading() {
        binding.errorState.visibility = View.GONE
        binding.loadingState.visibility = View.VISIBLE
        binding.successState.visibility = View.GONE
    }

    private fun getUrlImage(nameImage: String, date: Date): String {
        val cal = Calendar.getInstance()
        cal.time = date
        val year = cal[Calendar.YEAR]
        var month = (cal[Calendar.MONTH] + 1).toString()
        month = if (month.length < 2) "0$month" else month
        val day = cal[Calendar.DAY_OF_MONTH]

        return "https://epic.gsfc.nasa.gov/archive/natural/$year/$month/$day/jpg/$nameImage.jpg"
    }

    companion object {
        const val routeName: String = "Earth"
    }
}
