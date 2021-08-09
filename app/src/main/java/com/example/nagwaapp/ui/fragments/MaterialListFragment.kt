package com.example.nagwaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nagwaapp.R
import com.example.nagwaapp.adapter.MaterialListAdapter
import com.example.nagwaapp.databinding.FragmentMaterialListBinding
import com.example.nagwaapp.interfaces.OnItemClicked
import com.example.nagwaapp.models.Material
import com.example.nagwaapp.viewmodel.MaterialViewModel

class MaterialListFragment : Fragment(), OnItemClicked {

    private lateinit var binding : FragmentMaterialListBinding
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val viewModel: MaterialViewModel by lazy {
        ViewModelProvider(this).get(MaterialViewModel::class.java)
    }
    private lateinit var materialsListAdapter: MaterialListAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        //val to bind the view with the data
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_material_list, container, false
        )
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        setMaterialRecyclerView()

        viewModel.status.observe(viewLifecycleOwner, { status ->
            when(status){
                "loading" -> {
                    binding.matieralListRecyclerView.visibility = View.INVISIBLE
                    binding.errorMessageTextView.visibility = View.INVISIBLE
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                "failed" -> {
                    binding.matieralListRecyclerView.visibility = View.INVISIBLE
                    binding.errorMessageTextView.visibility = View.VISIBLE
                    binding.loadingProgressBar.visibility = View.INVISIBLE
                }
                "done" -> {
                    setupObserver()
                    binding.matieralListRecyclerView.visibility = View.VISIBLE
                    binding.errorMessageTextView.visibility = View.INVISIBLE
                    binding.loadingProgressBar.visibility = View.INVISIBLE
                }
            }
        })


        viewModel.getMaterialList()

        return binding.root
    }

    //setup the observer to get the data from the api
    private fun setupObserver(){
        viewModel.materialList.observe(viewLifecycleOwner, { materialList ->
            if (materialList != null) {
                retrieveMaterialList(materialList)
            }
        })
    }

    //set the main parameters for the recyclerView with the adapter
    private fun setMaterialRecyclerView() {
        binding.matieralListRecyclerView.layoutManager = LinearLayoutManager(context)
        materialsListAdapter = MaterialListAdapter(arrayListOf(), this)
        binding.matieralListRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.matieralListRecyclerView.context,
                (binding.matieralListRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.matieralListRecyclerView.adapter = materialsListAdapter
    }

    //get the material list from ViewModel
    private fun retrieveMaterialList(materialModel: ArrayList<Material>) {
        materialsListAdapter.apply {
            addMaterial(materialModel)
            notifyDataSetChanged()
        }
    }

    //item click to go for details activity
    override fun onItemClick(material: Material) {
        this.findNavController().navigate(
            MaterialListFragmentDirections
                .actionListToDetails(material))
    }
}