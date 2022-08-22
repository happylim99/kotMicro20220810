package com.sean.sample_ws.service

import com.sean.base.service.Crud
import com.sean.common_dto.sample_ws.SampleMasterCrtReq
import com.sean.common_dto.sample_ws.SampleMasterDto
import com.sean.common_dto.sample_ws.SampleMasterUpdReq

interface SampleMasterService: Crud<SampleMasterDto, SampleMasterCrtReq, SampleMasterUpdReq> {

    fun showByCriteria(req: String): SampleMasterDto?

}