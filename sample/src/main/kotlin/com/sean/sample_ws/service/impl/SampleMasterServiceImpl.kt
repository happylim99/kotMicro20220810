package com.sean.sample_ws.service.impl

import com.sean.base.exception.CException
import com.sean.base.ext.getUUID
import com.sean.common_dto.sample_ws.SampleMasterCrtReq
import com.sean.common_dto.sample_ws.SampleMasterDto
import com.sean.common_dto.sample_ws.SampleMasterUpdReq
import com.sean.sample_ws.entity.SampleMaster
import com.sean.sample_ws.mapper.toDto
import com.sean.sample_ws.repo.SampleMasterRepo
import com.sean.sample_ws.service.SampleMasterService
import com.sean.sample_ws.util.SampleWsUtil
import org.modelmapper.ModelMapper
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class SampleMasterServiceImpl(
    private val repo: SampleMasterRepo,
    private val modelMapper: ModelMapper,
    private val util: SampleWsUtil
): SampleMasterService {

    override fun showByCriteria(req: String): SampleMasterDto? {
        TODO("Not yet implemented")
    }

    override fun createOne(req: SampleMasterCrtReq): SampleMasterDto {
        validateCrtRequireField(req)
        println(req)
        var sampleMaster = SampleMaster()
        modelMapper.map(req, sampleMaster)
//        BeanUtils.copyProperties(req, sampleMaster)
        sampleMaster.uid = getUUID()
        sampleMaster.sampleDetail?.forEach {
            it.uid = getUUID()
        }
        sampleMaster = repo.save(sampleMaster)
        var sampleMasterDto = SampleMasterDto()
        BeanUtils.copyProperties(sampleMaster, sampleMasterDto)
        return sampleMasterDto
    }

    override fun updateOne(uid: String, req: SampleMasterUpdReq): SampleMasterDto {
        var sampleMaster: SampleMaster = repo.findByUid(uid) ?: throw CException("Not Found")
        var sampleMasterDto = SampleMasterDto()
        sampleMaster.apply {
            sampleMasterName = req.sampleMasterName
            description = req.description
            repo.save(this)
            BeanUtils.copyProperties(sampleMaster, sampleMasterDto)
        }
        return sampleMasterDto
    }

    override fun showOne(uid: String): SampleMasterDto? {
        val sampleMaster: SampleMaster = repo.findByUid(uid) ?: throw CException("Not Found")

//        var sampleMasterDto = SampleMasterDto()
//        sampleMaster?.let { BeanUtils.copyProperties(sampleMaster, sampleMasterDto) }
//        return sampleMasterDto
//        return mapper.sampleMasterToSampleMasterDto(sampleMaster)
        return sampleMaster.toDto(sampleMaster)
    }

    override fun showAll(): List<SampleMasterDto>? {
        val sampleMaster = repo.findAll()
        var sampleMasterDtoList: MutableList<SampleMasterDto> = mutableListOf()
        sampleMaster.forEach {
            val sampleMasterDto = SampleMasterDto()
            modelMapper.map(it, sampleMasterDto)
            sampleMasterDtoList.add(sampleMasterDto)
        }
        return sampleMasterDtoList
    }

    override fun deleteOne(uid: String): String {
        val theId = repo.deleteByUid(uid)
        return if(theId.equals(0)) "Unable to delete $uid" else "$uid deleted"
    }

    private fun validateCrtRequireField(req: SampleMasterCrtReq) {
        if(req.sampleMasterName == null) {
            throw CException("SampleMasterName is null")
        }
    }

}