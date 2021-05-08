package ir.erfan_mh_at.android.reminder.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.ui.ReminderActivity
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel

class UserProgramsFragment : Fragment(R.layout.fragment_user_programs) {
    lateinit var anyObjectViewModel: AnyObjectViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anyObjectViewModel = (activity as ReminderActivity).anyObjectViewModel
    }
}