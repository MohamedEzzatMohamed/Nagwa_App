package com.example.nagwaapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nagwaapp.R
import com.example.nagwaapp.adapter.MaterialListAdapter
import com.example.nagwaapp.databinding.FragmentMaterialListBinding
import com.example.nagwaapp.interfaces.OnItemClicked
import com.example.nagwaapp.models.Material
import com.example.nagwaapp.viewmodel.MaterialViewModel

//@AndroidEntryPoint
class MaterialListFragment : Fragment(), OnItemClicked {

    private val TAG : String = MaterialListFragment::class.java.simpleName

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
                "Done" -> {
                    binding.matieralListRecyclerView.visibility = View.VISIBLE
                    binding.errorMessageTextView.visibility = View.INVISIBLE
                    binding.loadingProgressBar.visibility = View.INVISIBLE
                    setupObserver()
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
                setMaterialRecyclerView(materialList)

            }
        })
    }

    //set the main parameters for the recyclerView with the adapter and data
    private fun setMaterialRecyclerView(materialList: ArrayList<Material>) {
        binding.matieralListRecyclerView.layoutManager = LinearLayoutManager(context)
        materialsListAdapter = MaterialListAdapter(materialList, this)
        binding.matieralListRecyclerView.adapter = materialsListAdapter
    }

    //item click to go for details activity
    override fun onItemClick(material: Material) {
        Log.d(TAG, "onItemClick: $material")
    }
}