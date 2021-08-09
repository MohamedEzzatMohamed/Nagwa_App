package com.example.nagwaapp.ui.fragments

import android.annotation.SuppressLint
import android.media.session.MediaController
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.nagwaapp.R
import com.example.nagwaapp.databinding.FragmentDetailsMaterialBinding
import com.example.nagwaapp.databinding.FragmentMaterialListBinding
import com.example.nagwaapp.models.Material
import com.example.nagwaapp.viewmodel.DetailsViewModel
import com.example.nagwaapp.viewmodel.MaterialViewModel

//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class DetailsMaterialFragment : Fragment() {

    private val TAG: String = DetailsMaterialFragment::class.java.simpleName

    private lateinit var arguments : Material

    private lateinit var binding : FragmentDetailsMaterialBinding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details_material, container, false
        )
        binding.lifecycleOwner = this
        arguments = DetailsMaterialFragmentArgs.fromBundle(requireArguments()).materialItem
        Log.d(TAG, "onCreateView: $arguments")
        viewModel.setMaterialDetails(arguments)
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.materialLiveData.observe(viewLifecycleOwner, {  material ->
            binding.materialDetailNameTextView.text = material.name
            binding.materialWebView.loadUrl(material.url)
        })

        viewModel.timerStart.observe(viewLifecycleOwner, {
            if(it){
                binding.downloadLinearLayout.visibility = View.VISIBLE
            } else{
                binding.downloadLinearLayout.visibility = View.INVISIBLE
            }
        })

        viewModel.counter.observe(viewLifecycleOwner, {
            binding.progressHorizontalProgressBar.progress = it.toInt()
            binding.percentageTextView.text = "$it%"
        })

        viewModel.materialStatus.observe(viewLifecycleOwner, {  status ->
            when (status) {
                "downloading" -> binding.materialStatusImageView.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_watch_later))
                "downloaded" -> binding.materialStatusImageView.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_downloaded))
                else -> binding.materialStatusImageView.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_download))
            }

        })


    }


}