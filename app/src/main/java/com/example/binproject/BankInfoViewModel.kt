package com.example.binproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BankInfoViewModel(private val repository: AppRepository) : ViewModel() {
    val state: MutableLiveData<State> = MutableLiveData()
    val history: MutableLiveData<List<BankInfo>> = MutableLiveData()

    fun onSearchButtonPressed(bin: String) {
        viewModelScope.launch {
            try {
                state.value = State.Loading
                val result = repository.getBankInfo(bin.filter { it.isDigit() })
                state.value = State.Idle(
                    BankInfo(
                        name = result.bank.name,
                        url = result.bank.url,
                        phone = result.bank.phone,
                        city = result.bank.city.orEmpty()
                    )
                )
                history.value = history.value.orEmpty() + BankInfo(
                    name = result.bank.name,
                    url = result.bank.url,
                    phone = result.bank.phone,
                    city = result.bank.city.orEmpty()
                )
            } catch (error: Throwable) {
                state.value = State.InvalidInput(error.message.orEmpty())
            }
        }
    }
}