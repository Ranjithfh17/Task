package com.fh.task.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.fh.task.Utils.Resource
import com.fh.task.callbacks.OnAdventureCallback
import com.fh.task.data.model.AdventureInfo
import com.fh.task.databinding.ActivityHomeBinding
import com.fh.task.ui.adapter.HomeAdapter
import com.fh.task.viewmodel.TaskViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), OnAdventureCallback {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private val viewModel by viewModels<TaskViewModel>()
    private var key: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        observeAdventureInfo()


    }


    private fun setUpRecyclerView() {
        homeAdapter = HomeAdapter(this)
        binding.homeRecyclerView.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }


    private fun observeAdventureInfo() {
        viewModel.infoLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.isVisible = true

                }

                is Resource.Success -> {
                    Log.i("TAG", "observeAdventureInfo:${it.data} ")
                    binding.progressbar.isVisible = false

                    viewModel.deleteInfoTable()


                    it.data?.let { taskInfo ->

                        for (file in taskInfo.files) {
                            val adventureInfo = AdventureInfo(file.name, file.source, file.mtime)
                            viewModel.insertAdventure(adventureInfo)

                        }





                        setAdventure()
                    }

                }

                is Resource.Error -> {
                    binding.progressbar.isVisible = false
                    Log.i("TAG", "observeAdventureInfo: ${it.message}")

                    Snackbar.make(binding.root, "Something Went Wrong!", Snackbar.LENGTH_LONG)
                        .show()

                }
            }
        }

    }


    private fun setAdventure() {
        viewModel.getAdventures().observe(this) {

            homeAdapter.differ.submitList(it)

            Log.i("TAG", "setAdventure:${it.size} ")

            Log.i("TAG", "setAdventure: $it")


        }
    }


    override fun onItemClick(adventureInfo: AdventureInfo) {


        val intent = Intent(this, AdventureDetail::class.java)
        intent.putExtra("info", adventureInfo)

        startActivity(intent)


    }


}


