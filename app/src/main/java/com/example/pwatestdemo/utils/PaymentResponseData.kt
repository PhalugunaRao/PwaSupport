package com.ekincare.webutil

data class PaymentResponseData(
    val amount: String?,
    val order_id: String?,
    val description: String?,
    val pluxee_payment:Boolean?,
    val prefill: Prefill?,
)
data class Prefill(
    val contact: String?,
    val name: String?,
    val email: String?,
)
