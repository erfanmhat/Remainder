package ir.erfan_mh_at.android.reminder.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.adapters.AnyObjectAdapter
import ir.erfan_mh_at.android.reminder.app.AppFonts
import ir.erfan_mh_at.android.reminder.app.ReminderApplication
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import ir.erfan_mh_at.android.reminder.ui.ReminderActivity
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel
import kotlinx.android.synthetic.main.fragment_show_any_object.*
import kotlinx.android.synthetic.main.layout_any_object_recyclerview.*

class ShowAnyObjectFragment : Fragment(R.layout.fragment_show_any_object) {
    lateinit var anyObjectViewModel: AnyObjectViewModel
    lateinit var anyObjectAdapter: AnyObjectAdapter
    private lateinit var anyObject: AnyObject
    private val args: ShowAnyObjectFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configure()
    }

    private fun configure() {
        anyObject = args.anyObject
        etName.setText(anyObject.name)
        etData.setText(anyObject.data)

        etName.typeface = AppFonts.SUMMER_CALLING_FONT
        etData.typeface = AppFonts.SUMMER_CALLING_FONT

        setupRecyclerView()
        setOnClicks()
    }

    private fun setupRecyclerView() {
        anyObjectViewModel = (activity as ReminderActivity).anyObjectViewModel

        anyObjectAdapter = AnyObjectAdapter()
        anyObjectViewModel.getAllAnyObjectWithParentId(anyObject.id)
            .observe(this.viewLifecycleOwner, {
                anyObjectAdapter.differ.submitList(
                    it
                )
            })
        rvAnyObject.apply {
            adapter = anyObjectAdapter
            layoutManager = GridLayoutManager(this@ShowAnyObjectFragment.context, 2)
        }
    }

    private fun setOnClicks() {
        ivSave.setOnClickListener {
            anyObjectViewModel.upsert(
                AnyObject(
                    null,
                    anyObject.anyObject_id,
                    etName.text.toString(),
                    1,
                    null,
                    null,
                    etData.text.toString(),
                    null,
                    null
                )
            )
        }
        anyObjectAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("anyObject", it)
            }
            findNavController().navigate(
                R.id.action_showAnyObjectFragment_self,
                bundle
            )
        }

        fabAddAnyObject.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(
                    "anyObject",
                    AnyObject(
                        null, anyObject.id, "", 1, null, null, "", null, null
                    )
                )
            }
            findNavController().navigate(
                R.id.action_showAnyObjectFragment_self,
                bundle
            )
        }
    }
}