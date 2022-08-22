package com.sean.sample_ws.controller

import com.sean.common_dto.sample_ws.SampleMasterCrtReq
import com.sean.common_dto.sample_ws.SampleMasterDto
import com.sean.common_dto.sample_ws.SampleMasterUpdReq
import com.sean.sample_ws.apicomm.AuthUserClient
import com.sean.sample_ws.service.SampleMasterService
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sampleMaster")
class SampleMasterController(
    private val srv: SampleMasterService,
    private val env: Environment
) {

//    private val authUserClient: AuthUserClient

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

    @GetMapping("/properties")
    fun properties(): ResponseEntity<String?> {
        return ResponseEntity.ok(env.getProperty("aa.bb.cc"))
    }

//    @GetMapping("/testFeign/{uid}")
//    fun properties(@PathVariable uid: String): ResponseEntity<String?> {
//        return ResponseEntity.ok(authUserClient.getUserDto("opopopopopopo"))
//    }
}