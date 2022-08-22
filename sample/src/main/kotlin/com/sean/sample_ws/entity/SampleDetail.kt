package com.sean.sample_ws.entity

import javax.persistence.*

@Entity
class SampleDetail {

    constructor(id: Long, uid: String, sampleDetailName: String, description: String?) {
        this.id = id
        this.uid = uid
        this.sampleDetailName = sampleDetailName
        this.description = description
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, unique = true, length = 32, columnDefinition = "CHAR(32)")
    var uid = ""

    @Column(nullable = true, unique = false, length = 50)
    var sampleDetailName: String? = null

    @Column(nullable = true, unique = false)
    var description: String? = null
}