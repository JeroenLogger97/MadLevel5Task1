package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import com.example.madlevel5task1.databinding.FragmentAddNoteBinding
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveNote.setOnClickListener {
            saveNote()
        }

        observeNote()
    }

    private fun observeNote() {
        viewModel.note.observe(viewLifecycleOwner, { note ->
            note?.let {
                binding.etNoteTitle.editText?.setText(it.title)
                binding.etNoteTitle.editText?.setText(it.text)
            }

        })

        viewModel.error.observe(viewLifecycleOwner, { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, {
            //"pop" the backstack, this means we destroy this    fragment and go back to the RemindersFragment
            findNavController().popBackStack()
        })
    }

    private fun saveNote() {
        viewModel.updateNote(
            binding.etNoteTitle.editText?.text.toString(),
            binding.etNoteTitle.editText?.text.toString()
        )
    }

}