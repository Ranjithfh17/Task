package com.fh.task.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fh.task.data.model.AdventureInfo
import com.fh.task.databinding.ActivityAdventureDetailBinding
import com.fh.task.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AdventureDetail : AppCompatActivity() {

    private lateinit var binding: ActivityAdventureDetailBinding
    private val viewModel by viewModels<TaskViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdventureDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val taskInfo = intent.getParcelableExtra<Parcelable>("info") as AdventureInfo?
        Log.i("TAG", "onCreate: $taskInfo")
        setInfo(taskInfo)

        binding.delete.setOnClickListener {

            deleteInfo(taskInfo)

        }

        binding.update.setOnClickListener {

            updateInfo(taskInfo)

        }

        navigateBack()


    }


    private fun setInfo(taskInfo: AdventureInfo?) {
        taskInfo?.let {
            binding.apply {
                name.setText(it.name)
                source.setText(it.source)
            }
        }

    }

    private fun navigateBack() {
        binding.navigateBack.setOnClickListener {
            finish()
        }
    }


    private fun deleteInfo(taskInfo: AdventureInfo?) {
        taskInfo?.let {
            viewModel.deleteInfo(it)
            Toast.makeText(this, "Info Deleted Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }

    }


    @SuppressLint("SimpleDateFormat")
    private fun updateInfo(taskInfo: AdventureInfo?) {

        val time = (Date().time / 1000)

        taskInfo?.let {
            viewModel.updateAdventure(
                AdventureInfo(
                    binding.name.text.toString(),
                    binding.source.text.toString(),
                    time.toString(),
                    taskInfo.id
                )
            )
            Toast.makeText(this, "Info Updated Successfully", Toast.LENGTH_SHORT).show()

            finish()
        }

    }


}