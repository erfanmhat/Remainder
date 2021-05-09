package ir.erfan_mh_at.android.reminder.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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

        anyObjectAdapter = AnyObjectAdapter()
        anyObjectAdapter.differ.submitList(
            listOf(
                AnyObject(1,null,"name one",0,null,null,getString(R.string.lorem_ipsum),null,"May 5,2021"),
                AnyObject(2,null,"name two",0,null,null,"sd fsa df asd f as df",null,"May 6,2021"),
                AnyObject(3,null,"name three",0,null,null,getString(R.string.lorem_ipsum),null,"May 7,2021"),
                AnyObject(4,null,"name four",0,null,null,"sd fsa df asd f as df",null,"May 8,2021"),
                AnyObject(5,null,"name five",0,null,null,getString(R.string.lorem_ipsum),null,"May 9,2021")
            )
        )
        rvAnyObject.apply {
            adapter = anyObjectAdapter
            layoutManager = GridLayoutManager(this@UserProgramsFragment.context,2)
        }
    }
}