package ir.erfan_mh_at.android.reminder.ui.provider_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.erfan_mh_at.android.reminder.repositorys.AnyObjectRepository
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel

@Suppress("UNCHECKED_CAST")
class AnyObjectViewModelProviderFactory(
    val anyObjectRepository: AnyObjectRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        AnyObjectViewModel(anyObjectRepository) as T
}