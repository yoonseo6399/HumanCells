package annotations

import Scheduler
import logWithPrefix
import org.reflections.Reflections
import org.reflections.scanners.FieldAnnotationsScanner
import org.reflections.scanners.SubTypesScanner
import org.reflections.scanners.TypeAnnotationsScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import things.Cells.Protein.Reactable
import java.util.StringTokenizer
import kotlin.jvm.internal.Reflection

class RunAnnotations {
    companion object{
        const val LOGGING_DELAY = 10L
    }
    fun run(){

        //runLog()
        //runMust()
        use()
        logWithPrefix("complie complete")
    }


    val reflections = Reflections(
        ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("things"))
            .setScanners(
                SubTypesScanner(false),
                TypeAnnotationsScanner()
            )
    )
    private fun runLog(){
        val field = reflections.getFieldsAnnotatedWith(Log::class.java)

        Scheduler.instance.createRepeatingTask(LOGGING_DELAY,0){
            logWithPrefix(field.toString())
        }
    }
    private fun runMust(){



        val field = reflections.getFieldsAnnotatedWith(Log::class.java)

        Scheduler.instance.createRepeatingTask(LOGGING_DELAY,0){
            logWithPrefix(field.toString())
        }
    }
    private fun use(){

        /*
        UsedWith 어노테이션을 구현한 Class 객체를 받아옴 = AnnotatedClass

        AnnotatedClass 의 UsedWith 의 clazz 필드를 가져옴 = equalClass

        AnnotatedClass 를 구현한 Class 객체를 가져옴 = interfacedClass

        interfacedClass 의 interfaces 에 equalClass 가 있는지 확인, 없다면 익셉션
         */


        val annotatedClasses = reflections.getTypesAnnotatedWith(UsedWith::class.java) // 사실상 인터페이스임
        // 왠진 모르겠지만 UsedWith이 쓰인 class 를 상속하면 어노테이션이 달린취급됌, 처리
        val allClasses = ArrayList<Class<*>>()
        for (type in reflections.allTypes) {
            allClasses.add(Class.forName(type))
        }
        for (annotatedClass in annotatedClasses) {
            var equalClass:Class<*>
            try{
                 equalClass = annotatedClass.getAnnotation(UsedWith::class.java).clazz.java
            }catch (_: Exception){
                continue
            }
            val interfacedClasses = allClasses.filter { it.interfaces.any { it == annotatedClass } }
            if(interfacedClasses.isEmpty()) continue
            for (interfacedClass in interfacedClasses) {
                if(equalClass.isInterface){
                    if(!interfacedClass.interfaces.any{ it == equalClass }) throw Reactable.IllegalUsageException("${interfacedClass.simpleName} must be implemented with ${equalClass.simpleName}")
                }else
                if(interfacedClass.superclass != equalClass ) throw Reactable.IllegalUsageException("${interfacedClass.simpleName} must be extended with ${equalClass.simpleName}")
            }
        }

    }

    fun Collection<Class<*>>.toShortClassName() =
        this.transformTo { it.name.split('.').last() }


    fun <T,F> Collection<F>.transformTo(transformAction: (F) -> T): List<T>{
        val transformedArray = ArrayList<T>()
        this.forEach{
            transformedArray.add(transformAction.invoke(it))
        }
        return transformedArray
    }

    /*
            for(clazz in clazzs){
            val a = allType.filter { Class.forName(it).interfaces == clazz }
            val interfacedClazz = ArrayList<Class<*>>()
            a.forEach {
                val interfacedClass = Class.forName(it)
                val equalClass = interfacedClass.getAnnotation(UsedWith::class.java).clazz.java

                //interfacedClass = annotation 을 달아둔 인터페이스를 구현한 Class 객체
                //equalClass = annotation에 달아둔
                if(interfacedClass.interfaces.any{ it == equalClass }) return
                throw Reactable.IllegalUsageException("")
            }


            if(interfacedClazz.isEmpty()) return
            interfacedClazz.any { it.interfaces.any { it ==  } }

        }
        throw Reactable.IllegalUsageException("")
     */
}