package com.example.nagwaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwaapp.databinding.MaterialItemBinding
import com.example.nagwaapp.interfaces.OnItemClicked
import com.example.nagwaapp.models.Material
import kotlin.collections.ArrayList

class MaterialListAdapter(
    private var materialList: ArrayList<Material>,
    private var itemListener: OnItemClicked
) : RecyclerView.Adapter<MaterialListAdapter.MaterialsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialsViewHolder {
        return MaterialsViewHolder(MaterialItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    //bind the views from viewHolder with the data in arrayList
    override fun onBindViewHolder(holder: MaterialsViewHolder, position: Int) {
        val item = materialList[position]

        holder.bind(itemListener, item)
//
//        holder.bind(item).setOnClickListener {
//
//            itemListener.onItemClick(item)
//
//        }

    }

    //return num of array size
    override fun getItemCount() = materialList.size

    //view holder for components in each item in the list
//    class MaterialsViewHolder(private var binding: MaterialItemBinding):
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(material: Material) {
//            binding.materialNameTextView.text = material.name
//            binding.itemCardView
//
//        }
//    }

    class MaterialsViewHolder(val binding: MaterialItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: OnItemClicked, item: Material) {
            binding.material = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MaterialsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MaterialItemBinding.inflate(layoutInflater, parent, false)

                return MaterialsViewHolder(binding)
            }
        }
    }

}