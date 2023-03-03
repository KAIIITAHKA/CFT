package com.example.binproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.binproject.databinding.BankItemBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private var bankList = listOf<BankInfo>()

    class HistoryHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = BankItemBinding.bind(item)

        fun bind(bank: BankInfo) = with(binding) {
            historyTextView.text = bank.body()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bank_item, parent, false)
        return HistoryHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(bankList[position])
    }

    override fun getItemCount(): Int {
        return bankList.size
    }

    fun addBank(banks: List<BankInfo>) {
        bankList = banks
        notifyDataSetChanged()
    }
}