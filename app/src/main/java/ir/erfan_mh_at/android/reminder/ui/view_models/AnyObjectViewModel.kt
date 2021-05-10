package ir.erfan_mh_at.android.reminder.ui.view_models

import androidx.lifecycle.ViewModel
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import ir.erfan_mh_at.android.reminder.repositorys.AnyObjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnyObjectViewModel(
    val anyObjectRepository : AnyObjectRepository
) : ViewModel() {

    fun upsert(item: AnyObject) = CoroutineScope(Dispatchers.Main).launch {
        anyObjectRepository.upsertAnyObject(item)
    }

    fun delete(item: AnyObject) = CoroutineScope(Dispatchers.Main).launch {
        anyObjectRepository.deleteAnyObject(item)
    }

    fun getAllAnyObject() = anyObjectRepository.getAllAnyObject()

    fun getAllAnyObjectWithParentId(anyObject_id: Int?) =
        anyObjectRepository.getAllAnyObjectWithParentId(anyObject_id)
}