package com.sean.sample_ws.mapper

import com.sean.common_dto.sample_ws.SampleDetailDto
import com.sean.common_dto.sample_ws.SampleMasterDto
import com.sean.sample_ws.entity.SampleDetail
import com.sean.sample_ws.entity.SampleMaster

fun SampleMaster.toDto(ent: SampleMaster?): SampleMasterDto? {
    if(ent == null) {
        return null
    }

    var dto = SampleMasterDto()
    dto.uid = ent.uid
    dto.sampleMasterName = ent.sampleMasterName

    dto.sampleDetail = ent.sampleDetail?.asSequence()?.map {
        it.toDto(it)
    }?.toMutableList()

    return dto

}

fun SampleDetail.toDto(ent: SampleDetail?): SampleDetailDto? {
    if(ent == null) return null

    var dto = SampleDetailDto()
    with(dto) {
        uid = ent.uid
        sampleDetailName = ent.sampleDetailName
        return dto
    }
}