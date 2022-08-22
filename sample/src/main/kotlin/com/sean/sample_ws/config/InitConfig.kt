package com.sean.sample_ws.config

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class InitConfig(
    val dataSource: DataSource,
    val env: Environment
) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadData() {
        initPopulator();
    }

    private fun initPopulator() {
//        println(env.getProperty("aa.bb.cc"))
    }

//    private fun initPopulator() {
//        val populator = createPopulator("init.sql")
//        populator.execute(dataSource)
//    }
//
//
//    private fun createPopulator(path: String) =
//        ResourceDatabasePopulator(
//            false,
//            false,
//            "UTF-8",
//            ClassPathResource(path)
//        )
}