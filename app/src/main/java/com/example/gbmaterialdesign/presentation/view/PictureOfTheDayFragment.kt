package com.example.gbmaterialdesign.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.gbmaterialdesign.R
import com.example.gbmaterialdesign.common.AppData
import com.example.gbmaterialdesign.data.model.PODServerResponseData
import com.example.gbmaterialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.gbmaterialdesign.presentation.MainActivity
import com.example.gbmaterialdesign.presentation.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProviders.of(this).get(PictureOfTheDayViewModel::class.java)
    }
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.loadData()
            showLoading()
        }
        setBottomAppBar(view)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.buttonReload.setOnClickListener {
            viewModel.loadData()
        }

        binding.searchWikiInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("$URL_WIKI${binding.searchWikiInputLayout.editText?.text?.toString() ?: ""}")
            })
        }
    }

    private fun renderData(appData: AppData<PODServerResponseData>?) {
        when (appData) {
            is AppData.Success<PODServerResponseData> -> {
                val serverResponseData = appData.data
                val url = serverResponseData.url
                val description = serverResponseData.explanation
                val title = serverResponseData.title
                if (url.isNullOrEmpty()) {
                    showError()
                } else {
                    showSuccess(url, description ?: "", title ?: "")
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

    private fun showLoading() {
        binding.errorState.visibility = View.GONE
        binding.loadingState.visibility = View.VISIBLE
        binding.successState.visibility = View.GONE
    }

    private fun showError() {
        binding.errorState.visibility = View.VISIBLE
        binding.loadingState.visibility = View.GONE
        binding.successState.visibility = View.GONE

        Glide.with(this)
            .load(R.drawable.ic_baseline_cloud_off_24)
            .centerCrop()
            .into(binding.podImage)
    }

    private fun showSuccess(url: String, description: String, title: String) {
        binding.errorState.visibility = View.GONE
        binding.loadingState.visibility = View.GONE
        binding.successState.visibility = View.VISIBLE

        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(binding.podImage)

        binding.bottomSheetContainer.bottomSheetDescription.text = description
        binding.bottomSheetContainer.bottomSheetDescriptionHeader.text = title
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

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PictureOfTheDayFragment()

        const val URL_WIKI = "https://ru.wikipedia.org/wiki/"
    }
}
