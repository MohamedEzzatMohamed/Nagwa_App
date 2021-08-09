package com.example.nagwaapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.nagwaapp.R
import com.example.nagwaapp.databinding.FragmentDetailsMaterialBinding
import com.example.nagwaapp.models.Material
import com.example.nagwaapp.utils.Constant
import com.example.nagwaapp.viewmodel.DetailsViewModel


class DetailsMaterialFragment : Fragment() {

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
        viewModel.setMaterialDetails(arguments)
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var url: String
        viewModel.materialLiveData.observe(viewLifecycleOwner, {  material ->
            binding.materialDetailNameTextView.text = material.name
            if(material.type == "PDF")
                url = Constant.PDF_READER_URL + material.url
            else
                url = material.url
            binding.materialWebView.loadUrl(url)
        })

        viewModel.timerStart.observe(viewLifecycleOwner, {
            if(it){
                binding.downloadLinearLayout.visibility = View.VISIBLE
            } else{
                binding.downloadLinearLayout.visibility = View.INVISIBLE
            }
        })

        viewModel.counter.observe(viewLifecycleOwner, { percentage ->
            binding.progressHorizontalProgressBar.progress = percentage
            binding.percentageTextView.text = getString(R.string.loading_percentage, percentage)
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