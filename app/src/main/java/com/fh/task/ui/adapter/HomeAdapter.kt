package com.fh.task.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fh.task.callbacks.OnAdventureCallback
import com.fh.task.data.model.AdventureInfo
import com.fh.task.databinding.AdapterHomeBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter(private val onAdventureCallback: OnAdventureCallback) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = AdapterHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val adventureInfo = differ.currentList[position]

        holder.setViews(adventureInfo)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class HomeViewHolder(val binding: AdapterHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SimpleDateFormat")
        fun setViews(adventureInfo: AdventureInfo) {

            binding.apply {
                name.text = adventureInfo.name
                source.text = adventureInfo.source

                try {
                    val itemLong = (adventureInfo.mtime!!.toLong() / 1000L)
                    val time = Date(adventureInfo.mtime.toLong() * 1000L);
                    val formatter = SimpleDateFormat("dd-MMM HH:mm")
                    binding.time.text = formatter.format(time)
                } catch (e: Exception) {

                }


            }


        }

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onAdventureCallback.onItemClick(differ.currentList[position])
                }
            }

            binding.name.isSelected = true

        }


    }


    private val differCallback = object : DiffUtil.ItemCallback<AdventureInfo>() {
        override fun areItemsTheSame(oldItem: AdventureInfo, newItem: AdventureInfo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AdventureInfo, newItem: AdventureInfo) =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)


}