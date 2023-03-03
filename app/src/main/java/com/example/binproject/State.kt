package com.example.binproject

sealed interface State {
    class Idle(val bankInfo: BankInfo) : State
    object Loading : State
    data class InvalidInput(val reason: String) : State
}