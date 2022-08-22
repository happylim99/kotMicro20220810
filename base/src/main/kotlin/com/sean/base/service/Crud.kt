package com.sean.base.service

interface Crud<DTO, CRT, UPT> {
    fun createOne(req: CRT): DTO
    fun updateOne(uid: String, req: UPT): DTO
    fun showOne(uid: String): DTO?
    fun showAll(): List<DTO>?
    fun deleteOne(uid: String): String
}