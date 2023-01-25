package com.example.postaloff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postaloff.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var adapter: ItemsRecyclerAdapter
    private lateinit var vpAdapter: VpAdapter
    private lateinit var dbHelper: SQLiteHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        initVpViews()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerViews(){
        dbHelper = SQLiteHelper(requireContext())
        val items = dbHelper.getAllItems()

        binding.tvNoItems.isVisible = items.isEmpty()


        adapter = ItemsRecyclerAdapter()
        binding.rvItems.layoutManager = LinearLayoutManager(requireActivity())
        adapter.setData(items)
        binding.rvItems.adapter = adapter
    }

    private fun initVpViews(){
        val images = listOf(R.drawable.nike, R.drawable.aliexpress, R.drawable.amazon)
        vpAdapter = VpAdapter(items = images)
        binding.vpPhotos.adapter = vpAdapter
    }
}