package com.sean.sample_ws.entity

import com.sean.auth.config.RoleCode
import javax.persistence.*

@Entity
class SampleMaster {

    constructor()

    constructor(uid: String, sampleMasterName: String?,
                sampleDetail: MutableList<SampleDetail>?, description: String?) {
        this.uid = uid
        this.sampleMasterName = sampleMasterName
        this.sampleDetail = sampleDetail
        this.description = description
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, unique = true, length = 32, columnDefinition = "CHAR(32)")
    var uid = ""

    @Column(nullable = true, unique = false, length = 50)
    var sampleMasterName: String? = null

    @Column(nullable = true, unique = false)
    var description: String? = null

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "sample_master_id")
    var sampleDetail: MutableList<SampleDetail>? = null

    override fun toString(): String {
        return "Role(id=$id, roleName='$sampleMasterName')"
    }
}