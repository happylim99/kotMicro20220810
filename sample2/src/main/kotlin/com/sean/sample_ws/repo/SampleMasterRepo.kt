package com.sean.sample_ws.repo

import com.sean.sample_ws.entity.SampleMaster
import org.springframework.data.jpa.repository.JpaRepository
import javax.transaction.Transactional

@Transactional
interface SampleMasterRepo: JpaRepository<SampleMaster, Long> {

    fun findBySampleMasterName(sampleMasterName: String): SampleMaster?

    fun findByUid(uid: String): SampleMaster?

    fun deleteByUid(uid: String): Long
}