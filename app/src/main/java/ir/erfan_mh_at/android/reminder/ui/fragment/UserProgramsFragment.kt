package ir.erfan_mh_at.android.reminder.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.adapters.AnyObjectAdapter
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import ir.erfan_mh_at.android.reminder.ui.ReminderActivity
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel
import kotlinx.android.synthetic.main.fragment_user_programs.*
import kotlinx.android.synthetic.main.layout_any_object_recyclerview.*

class UserProgramsFragment : Fragment(R.layout.fragment_user_programs) {
    lateinit var anyObjectViewModel: AnyObjectViewModel
    lateinit var anyObjectAdapter: AnyObjectAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configure()
    }

    private fun configure() {
        setupRecyclerView()
        setOnClicks()
    }

    private fun setupRecyclerView() {
        anyObjectViewModel = (activity as ReminderActivity).anyObjectViewModel
        anyObjectAdapter = AnyObjectAdapter()

        anyObjectViewModel.getAllAnyObjectWithParentId(0).observe(this.viewLifecycleOwner, {
            anyObjectAdapter.differ.submitList(
                it
            )
        })
        rvAnyObject.apply {
            adapter = anyObjectAdapter
            layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        }
    }

    private fun setOnClicks() {
        fabAddAnyObject.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(
                    "anyObject",
                    AnyObject(
                        null, 0, "", 1, null, null, "", null, null
                    )
                )
            }
            findNavController().navigate(
                R.id.action_userProgramsFragment_to_showAnyObjectFragment,
                bundle
            )
        }
        anyObjectAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("anyObject", it)
            }
            findNavController().navigate(
                R.id.action_userProgramsFragment_to_showAnyObjectFragment,
                bundle
            )
        }
    }
}