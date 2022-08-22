package com.sean.base.config

import com.sean.base.annotation.Slf4j
import com.sean.base.annotation.Slf4j.Companion.log
import com.sean.base.exception.NoLogException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
@Slf4j
class AopConfig {

    @Around("execution(* com.sean.*.controller.*.*(..))")
    private fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val start = System.currentTimeMillis()
        val signature = joinPoint.signature.toShortString()

        val response =
            (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).response

        val result = try {
            with(StringBuilder("Start -> Executing $signature")) {
//                appendParameters(joinPoint.args)
                log.info(toString())
            }
            joinPoint.proceed()
        } catch (throwable: Throwable) {
            val noThrowable = listOf<Class<*>>(
                NoLogException::class.java,
                AccessDeniedException::class.java
            )
            if(throwable::class.java.interfaces.any {
                    it in noThrowable
                }
            ) {
                log.error("*** Checked Exception during executing $signature")
            } else if(throwable::class.java.simpleName == "AccessDeniedException") {
                log.error("*** Checked Exception during executing $signature")
            } else {
                log.error("*** Exception during executing $signature", throwable)
            }
            throw throwable
        }
        val duration = System.currentTimeMillis() - start
        log.info("End -> Finished executing: $signature")
        log.info("Duration: $duration ms, Http code: ${response?.status}")
        return result
    }

    private fun StringBuilder.appendParameters(args: Array<Any>) {
        append("parameters: [")
        args.forEachIndexed { i, p ->
            append(p.javaClass.simpleName).append("(").append(p.toString()).append(")")
            if (i < args.size - 1) append(", ")
        }
        append("]")
    }

}