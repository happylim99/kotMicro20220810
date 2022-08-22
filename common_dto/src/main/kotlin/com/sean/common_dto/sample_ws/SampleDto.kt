package com.sean.common_dto.sample_ws

data class SampleMasterDto(
    var uid: String = "",
    var sampleMasterName: String? = null,
    var sampleDetail: MutableList<SampleDetailDto?>? = null
)

data class SampleDetailDto(
    var uid: String = "",
    var sampleDetailName: String? = null
)