package br.com.zup

import io.micronaut.core.annotation.AnnotationValue
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Constraint
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CampoUnicoValidator::class])
annotation class CampoUnico(
    val field: String,
    val domainClass: KClass<*>,
    val message: String = "campo já está cadastrado"
)

@Singleton
open class CampoUnicoValidator(val em: EntityManager) : ConstraintValidator<CampoUnico, String> {

    @Transactional
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<CampoUnico>,
        context: ConstraintValidatorContext?
    ) : Boolean {
        val campo = annotationMetadata.stringValue("field").get()
        val klass = annotationMetadata.classValue("domainClass").get()
        val resultList = em.createQuery("SELECT k FROM ${klass.simpleName} k WHERE k$campo=:value")
            .setParameter("value", value).resultList

        return resultList.isEmpty()
    }



}
