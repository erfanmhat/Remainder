package ir.erfan_mh_at.android.reminder.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.adapters.AnyObjectAdapter
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import ir.erfan_mh_at.android.reminder.ui.ReminderActivity
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel
import kotlinx.android.synthetic.main.fragment_show_any_object.*
import kotlinx.android.synthetic.main.layout_any_object_recyclerview.*

class ShowAnyObjectFragment : Fragment(R.layout.fragment_show_any_object) {
    lateinit var anyObjectViewModel: AnyObjectViewModel
    lateinit var anyObjectAdapter: AnyObjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        configure()
    }

    private fun setupRecyclerView() {
        anyObjectViewModel = (activity as ReminderActivity).anyObjectViewModel

        anyObjectAdapter = AnyObjectAdapter()
        anyObjectAdapter.differ.submitList(
            listOf(
                AnyObject(
                    1,
                    null,
                    "name 1",
                    0,
                    null,
                    null,
                    getString(R.string.lorem_ipsum),
                    null,
                    "May 5,2021"
                ),
                AnyObject(
                    2,
                    null,
                    "name 2",
                    0,
                    null,
                    null,
                    "sd fsa df asd f as df",
                    null,
                    "May 6,2021"
                ),
                AnyObject(
                    3,
                    null,
                    "name 3",
                    0,
                    null,
                    null,
                    getString(R.string.lorem_ipsum),
                    null,
                    "May 7,2021"
                ),
                AnyObject(
                    4,
                    null,
                    "name 4",
                    0,
                    null,
                    null,
                    "sd fsa df asd f as df",
                    null,
                    "May 8,2021"
                ),
                AnyObject(
                    5,
                    null,
                    "name 5",
                    0,
                    null,
                    null,
                    getString(R.string.lorem_ipsum),
                    null,
                    "May 9,2021"
                )
            )
        )
        rvAnyObject.apply {
            adapter = anyObjectAdapter
            layoutManager = GridLayoutManager(this@ShowAnyObjectFragment.context, 2)
        }
    }

    private fun configure() {
        tvName.text = "sadf"
        anyObjectAdapter.setOnItemClickListener {
            Toast.makeText(this.context, it.name, Toast.LENGTH_SHORT).show()
        }
    }
}