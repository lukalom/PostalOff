package com.example.postaloff

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import com.example.postaloff.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var dbHelper: SQLiteHelper

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = NotificationService(requireContext())


        dbHelper = SQLiteHelper(requireContext())

        binding.btSave.setOnClickListener {
            addNote(service)
        }
        binding.btView.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addNote(service: NotificationService) {
        val tracking = if (binding.etTracking.text.toString().isNotEmpty()) binding.etTracking.text.toString() else ""
        val price = binding.etPrice.text.toString()
        val webPage = binding.etWebPage.text.toString()
        val imageUrl = binding.etImageUrl.text.toString()
        val destination = binding.etDestination.text.toString()
        val comments = binding.etComments.text.toString()

        if (price.isNotEmpty() && tracking.isNotEmpty()) {
            val item = ItemModel(
                tracking = tracking,
                price = price,
                webpage = webPage,
                imageUrl = imageUrl,
                destination = destination,
                comments = comments
            )
            val status = dbHelper.insertData(item)

            if (status > -1) {
                service.showNotification("The item has been added to the list successfully")
                clearEditText()
            } else {
                service.showNotification("The item has been added to the list unsuccessfully")
            }
        } else {
                service.showNotification("Please fill all fields...")
        }

    }

    private fun clearEditText() {
        with(binding) {
            etTracking.text?.clear()
            etPrice.text?.clear()
            etWebPage.text?.clear()
            etImageUrl.text?.clear()
            etDestination.text?.clear()
            etComments.text?.clear()
            etTracking.requestFocus()
        }
    }
}