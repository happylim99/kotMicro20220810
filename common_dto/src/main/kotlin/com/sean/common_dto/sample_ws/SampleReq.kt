package com.sean.common_dto.sample_ws

data class SampleMasterCrtReq(
    val sampleMasterName: String = "",
    val description: String? = null,
    val sampleDetailCrtReq: List<SampleDetailCrtReq>? = null
)

data class SampleMasterUpdReq(
    val uid: String,
    val sampleMasterName: String,
    val description: String?
)

data class SampleMasterCriteriaReq(
    val sampleMasterName: String?,
    val sampleMasterDesc: String?,
    val SampleDetailCriteriaReq: SampleDetailCriteriaReq?
)

data class SampleDetailCrtReq(
    val sampleDetailName: String = "",
    val description: String? = null
)

data class SampleDetailUpdReq(
    val uid: String,
    val sampleDetailName: String,
    val description: String?
)

data class SampleDetailCriteriaReq(
    val sampleDetailName: String?,
    val sampleDetailDesc: String?
)