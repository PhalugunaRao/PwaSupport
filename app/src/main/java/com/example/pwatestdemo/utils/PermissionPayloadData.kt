package com.example.pwatestdemo.utils

import com.google.gson.annotations.SerializedName

data class PermissionPayloadData(
    val permissions: ArrayList<String>? = null,
)

data class ExternalData(
    val url: String?,
)

data class HcEnrollmentData(
    @SerializedName("program_enrollment_id")
    val enrolmentId: Int,
)

data class HcRescheduleData(
    var id: Int,
    var customer_id: Int,
    var doctor_id: Int,
    var doctor_name: String,
)

data class ReadMoreData(
    val title: String?,
    val id: Int,
)
