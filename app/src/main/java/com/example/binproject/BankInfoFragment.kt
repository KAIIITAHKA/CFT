package com.example.binproject

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.binproject.databinding.BankLayoutBinding

class BankInfoFragment : Fragment() {
    private lateinit var binding: BankLayoutBinding
    private val adapter = HistoryAdapter()
    private val viewModel: BankInfoViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return BankInfoViewModel(
                    repository = AppRepository()
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this) {
            when (it) {
                is State.Idle -> {
                    val bankInfo = it.bankInfo
                    binding.cardDetailsView.text = bankInfo.body()
                    binding.cardDetailsView.autoLinkMask = Linkify.ALL
                    binding.cardDetailsView.movementMethod = LinkMovementMethod.getInstance()
                    binding.progressBar.visibility = View.GONE
                }
                is State.InvalidInput -> {
                    val message = it.reason
                    binding.cardDetailsView.text = message
                    binding.progressBar.visibility = View.GONE
                    binding.textInputLayout.error = message
                    binding.cardDetailsView.text = "error"
                }
                State.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.cardDetailsView.text = "loading"
                }
                else -> {}
            }
        }
        viewModel.history.observe(this) {
            showBanks(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = BankLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener() {
            val currentBin = binding.textInputLayout.editText?.text

            val errorColor = Color.RED
            val errorColorStateList = ColorStateList.valueOf(errorColor)

            if (binding.textInputLayout.editText?.text.isNullOrEmpty()) {
                binding.textInputLayout.error = "input field is empty"
                binding.textInputLayout.setErrorTextColor(errorColorStateList)
            } else {
                viewModel.onSearchButtonPressed(currentBin.toString())
            }
        }
    }

    private fun showBanks(banks: List<BankInfo>) {
        binding.apply {
            recyclerView?.layoutManager = LinearLayoutManager(activity)
            recyclerView?.adapter = adapter
            adapter.addBank(banks)
        }
    }

}


