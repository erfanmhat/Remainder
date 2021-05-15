package ir.erfan_mh_at.android.reminder.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.adapters.AnyObjectAdapter
import ir.erfan_mh_at.android.reminder.app.AppFonts
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import ir.erfan_mh_at.android.reminder.ui.ReminderActivity
import ir.erfan_mh_at.android.reminder.ui.view_models.AnyObjectViewModel
import kotlinx.android.synthetic.main.fragment_show_any_object.*
import kotlinx.android.synthetic.main.layout_any_object_recyclerview.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
        setVariable()
        setFont()
        setupRecyclerView()
        setOnClicks()
        setTextChangedListener()
    }

    private fun setVariable(){
        anyObject = args.anyObject
        etName.setText(anyObject.name)
        etData.setText(anyObject.data)
        tvCreateAt.text=anyObject.created_at
    }

    private fun setFont(){
        etName.typeface = AppFonts.SUMMER_CALLING_FONT
        etData.typeface = AppFonts.SUMMER_CALLING_FONT
        tvCreateAt.typeface = AppFonts.SUMMER_CALLING_FONT
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
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        }
    }

    private fun setOnClicks() {
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

    private fun setTextChangedListener(){
        etName.addTextChangedListener {
            save()
        }
        etData.addTextChangedListener{
            save()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun save() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val createdAt =
            if (anyObject.created_at != null) {
                anyObject.created_at
            } else {
                currentDate
            }

        GlobalScope.launch {
            val anyObjectId = async {
                anyObjectViewModel.upsert(
                    anyObject.apply {
                        name = etName.text.toString()
                        data = etData.text.toString()
                        updated_at = currentDate
                        created_at = createdAt
                    }
                )
            }
            anyObject.id = anyObjectId.await().toInt()
        }
    }
}