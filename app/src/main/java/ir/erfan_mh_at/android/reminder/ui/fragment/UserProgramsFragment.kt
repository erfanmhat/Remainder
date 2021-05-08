package ir.erfan_mh_at.android.reminder.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.adapters.AnyObjectAdapter
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import ir.erfan_mh_at.android.reminder.ui.ReminderActivity
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel
import kotlinx.android.synthetic.main.fragment_user_programs.*

class UserProgramsFragment : Fragment(R.layout.fragment_user_programs) {
    lateinit var anyObjectViewModel: AnyObjectViewModel
    lateinit var anyObjectAdapter: AnyObjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anyObjectViewModel = (activity as ReminderActivity).anyObjectViewModel

        val anyObjectAdapter = AnyObjectAdapter()
        anyObjectAdapter.differ.submitList(
            listOf(
                AnyObject(1,null,"n1",0,null,null,"asdf",null,null),
                AnyObject(2,null,"n2",0,null,null,"asdf",null,null),
                AnyObject(3,null,"n3",0,null,null,"asdf",null,null),
                AnyObject(4,null,"n4",0,null,null,"asdf",null,null),
                AnyObject(5,null,"n5",0,null,null,"asdf",null,null)
            )
        )
        rvAnyObject.apply {
            adapter = anyObjectAdapter
            layoutManager = LinearLayoutManager(this@UserProgramsFragment.context)
        }
    }
}