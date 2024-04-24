package ru.practicum.android.diploma.data.converter

import ru.practicum.android.diploma.data.db.model.VacancyEntity
import ru.practicum.android.diploma.domain.models.VacancyDetails
import ru.practicum.android.diploma.domain.models.vacacy.Contacts
import ru.practicum.android.diploma.domain.models.vacacy.Employer
import ru.practicum.android.diploma.domain.models.vacacy.LogoUrls
import ru.practicum.android.diploma.domain.models.vacacy.Salary

object FavoriteVacanciesConverter {

    fun VacancyDetails.convert(): VacancyEntity = VacancyEntity(
        id = this.id,
        name = this.name,

        employerId = this.employer?.id,
        employerLogoUrl90 = this.employer?.logoUrls?.art90,
        employerLogoUrl240 = this.employer?.logoUrls?.art240,
        employerLogoUrlOriginal = this.employer?.logoUrls?.original,
        employerName = this.employer?.name,
        employerIsTrusted = this.employer?.trusted,
        employerVacanciesUrl = this.employer?.vacanciesUrl,

        salaryCurrency = this.salary?.currency,
        salaryFrom = this.salary?.from,
        salaryGross = this.salary?.gross,
        salaryTo = this.salary?.to,

        city = this.city,
        experience = this.experience,
        employment = this.employment,
        description = this.description,

        contactEmail = this.contacts?.email,
        contactName = this.contacts?.name,
        contactPhone = this.contacts?.phone,
        contactComment = this.contacts?.comment,

        link = this.link,
        keySkills = this.keySkills
    )

    fun VacancyEntity.convert(): VacancyDetails = VacancyDetails(
        id = this.id,
        name = this.name,

        salary = convertSalary(salaryCurrency, salaryFrom, salaryGross, salaryTo),

        employer = if (!employerId.isNullOrBlank()) {
            Employer(
                id = employerId,
                logoUrls = convertLogosUrl(employerLogoUrlOriginal, employerLogoUrl90, employerLogoUrl240),
                name = employerName,
                trusted = employerIsTrusted ?: false,
                vacanciesUrl = employerVacanciesUrl
            )
        } else {
            null
        },

        city = this.city,
        fullAddress = null,
        areaName = this.city ?: "",
        experience = this.experience,
        employment = this.employment,
        description = this.description,

        contacts = convertContacts(contactEmail, contactName, contactPhone, contactComment),

        link = this.link,
        keySkills = this.keySkills
    )

    private fun convertSalary(
        salaryCurrency: String?,
        salaryFrom: Int?,
        salaryGross: Boolean?,
        salaryTo: Int?
    ): Salary? {
        return if (!salaryCurrency.isNullOrBlank()) {
            Salary(
                currency = salaryCurrency,
                from = salaryFrom,
                gross = salaryGross,
                to = salaryTo
            )
        } else {
            null
        }
    }

    private fun isEmailAndNameNull(
        email: String?,
        name: String?,
    ) = email.isNullOrBlank() && name.isNullOrBlank()

    private fun isPhoneAndCommentNull(
        phone: String?,
        comment: String?
    ) = phone.isNullOrBlank() && comment.isNullOrBlank()

    private fun convertContacts(
        contactEmail: String?,
        contactName: String?,
        contactPhone: String?,
        contactComment: String?
    ): Contacts? {
        return if (
            isEmailAndNameNull(contactEmail, contactName) &&
            isPhoneAndCommentNull(contactPhone, contactComment)

        ) {
            null
        } else {
            Contacts(
                email = contactEmail,
                name = contactName,
                phone = contactPhone,
                comment = contactComment
            )
        }
    }

    private fun convertLogosUrl(
        employerLogoUrlOriginal: String?,
        employerLogoUrl90: String?,
        employerLogoUrl240: String?
    ): LogoUrls? {
        return if (!employerLogoUrlOriginal.isNullOrBlank()) {
            LogoUrls(
                art90 = employerLogoUrl90,
                art240 = employerLogoUrl240,
                original = employerLogoUrlOriginal
            )
        } else {
            null
        }
    }
}
