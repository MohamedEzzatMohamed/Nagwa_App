package com.example.nagwaapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nagwaapp.databinding.FragmentDetailsMaterialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMaterialFragment : Fragment() {

    private var _binding: FragmentDetailsMaterialBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        _binding = FragmentDetailsMaterialBinding.inflate(
            inflater, container, false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialDetailNameTextView.text = "this is a test"
        binding.materialVideoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4")
        binding.materialVideoView.start();

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}