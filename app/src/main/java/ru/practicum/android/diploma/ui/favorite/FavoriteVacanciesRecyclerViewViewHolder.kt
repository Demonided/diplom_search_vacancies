package ru.practicum.android.diploma.ui.favorite

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.VacancyDetails

class FavoriteVacanciesRecyclerViewViewHolder(
    itemView: View,
    private val vacancyClicked: (VacancyDetails) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val vacancyCompanyLogo: ImageView = itemView.findViewById(R.id.iv_logo)
    private val vacancyTitle: TextView = itemView.findViewById(R.id.tv_vacancy_name)
    private val vacancyCompany: TextView = itemView.findViewById(R.id.tv_vacancy_type)
    private val vacancySalary: TextView = itemView.findViewById(R.id.tv_vacancy_salary)

    fun bind(model: VacancyDetails) {
        vacancyTitle.text = model.name
        vacancyCompany.text = model.employer?.name ?: ""

        // добавить форматтер з/п
        vacancySalary.text =
            "от ${model.salary?.from ?: ""} до ${model.salary?.to ?: ""} ${model.salary?.currency ?: ""}END"
                .replace("от  до  END", "Зарплата не указана")
                .replace("от  ", "")
                .replace("до  ", "")
                .replace("END", "")

        Glide
            .with(itemView)
            .load(model.employer?.logoUrls?.original ?: "")
            .placeholder(R.drawable.spiral)
            // .transform(CenterCrop())
            .transform(FitCenter())
            .into(vacancyCompanyLogo)

        itemView.setOnClickListener { vacancyClicked(model) }
    }
}
