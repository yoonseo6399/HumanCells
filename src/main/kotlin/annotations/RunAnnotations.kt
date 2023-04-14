package annotations

import Scheduler
import logWithPrefix
import org.reflections.Reflections
import org.reflections.scanners.FieldAnnotationsScanner
import org.reflections.scanners.SubTypesScanner
import org.reflections.scanners.TypeAnnotationsScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.jvm.internal.Reflection

class RunAnnotations {
    companion object{
        const val LOGGING_DELAY = 10L
    }
    fun run(){
        runLog()
    }



    private fun runLog(){


        val reflections = Reflections(
            ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("kotlin"))
                .setScanners(
                    SubTypesScanner(false),
                    TypeAnnotationsScanner(),
                    FieldAnnotationsScanner()
                )
        )
        val field = reflections.getFieldsAnnotatedWith(Log::class.java)

        Scheduler.instance.createRepeatingTask(LOGGING_DELAY,0){
            logWithPrefix(field.toString())
        }
    }
}