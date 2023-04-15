package annotations

import javax.xml.transform.Source

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.EXPRESSION, AnnotationTarget.PROPERTY)
annotation class NeedToSee(val reason: String)


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Test


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Log

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class MustAddObject

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class In_InitBlock
