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
import com.example.gbmaterialdesign.data.model.MarsServerResponseData
import com.example.gbmaterialdesign.databinding.FragmentMarsBinding
import com.example.gbmaterialdesign.presentation.viewmodel.MarsViewModel

class MarsFragment : Fragment() {
    private val viewModel: MarsViewModel by lazy {
        ViewModelProviders.of(this).get(MarsViewModel::class.java)
    }
    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    private var isExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
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

        binding.marsImage.setOnClickListener {
            isExpanded = !isExpanded
            TransitionManager.beginDelayedTransition(
                binding.scrollContainer, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )

            val params: ViewGroup.LayoutParams = binding.marsImage.layoutParams
            params.height =
                if (isExpanded) params.height * 2 else params.height / 2
            binding.marsImage.layoutParams = params
            binding.marsImage.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }

    }

    private fun renderData(appData: AppData<MarsServerResponseData>?) {
        when (appData) {
            is AppData.Success<MarsServerResponseData> -> {
                val serverResponseData = appData.data
                val url = serverResponseData.photos?.first()?.imgSrc
                val title = serverResponseData.photos?.first()?.camera?.fullName

                if (url == null) {
                    showError()
                } else {
                    showSuccess(url, title ?: "")
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

    private fun showSuccess(url: String, title: String) {
        binding.errorState.visibility = View.GONE
        binding.loadingState.visibility = View.GONE
        binding.successState.visibility = View.VISIBLE

        GlideApp.with(requireContext())
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_cloud_off_24)
            .error(R.drawable.ic_baseline_cloud_off_24)
            .into(binding.marsImage)

        binding.marsTitle.text = title
    }

    private fun showLoading() {
        binding.errorState.visibility = View.GONE
        binding.loadingState.visibility = View.VISIBLE
        binding.successState.visibility = View.GONE
    }

    companion object {
        const val routeName: String = "Mars"
    }
}
