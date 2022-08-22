package com.sean.sample_ws.controller

import com.sean.common_dto.auth_ws.UserDto
import com.sean.common_dto.sample_ws.SampleMasterCrtReq
import com.sean.common_dto.sample_ws.SampleMasterDto
import com.sean.common_dto.sample_ws.SampleMasterUpdReq
import com.sean.sample_ws.entity.SampleMaster
import com.sean.sample_ws.service.SampleMasterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sampleMaster")
class SampleMasterController(
    private val srv: SampleMasterService
) {

    @PostMapping("/createOne")
    fun createOne(@RequestBody req: SampleMasterCrtReq): ResponseEntity<SampleMasterDto> {
        return ResponseEntity.ok(srv.createOne(req))
    }

    @PutMapping("/{uid}")
    fun updateOne(@PathVariable uid: String,
                  @RequestBody req: SampleMasterUpdReq
    ): ResponseEntity<SampleMasterDto> {
        return ResponseEntity.ok(srv.updateOne(uid, req))
    }

    @GetMapping("/{uid}")
    fun showOne(@PathVariable uid: String) = ResponseEntity.ok(srv.showOne(uid))

    @GetMapping("/showAll")
    fun showAll() = ResponseEntity.ok(srv.showAll())

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable id: String) = ResponseEntity.ok(srv.deleteOne(id))

}