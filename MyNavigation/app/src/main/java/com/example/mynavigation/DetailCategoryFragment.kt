package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mynavigation.databinding.FragmentDetailCategoryBinding
import com.example.mynavigation.databinding.FragmentHomeBinding

class DetailCategoryFragment : Fragment() {
    private var _binding: FragmentDetailCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val dataName = arguments?.getString(CategoryFragment.EXTRA_NAME)
//        val dataStock = arguments?.getInt(CategoryFragment.EXTRA_STOCK)

        val dataName = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        val dataStock = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).stcok

        binding.tvCategoryName.text = dataName
        binding.tvCategoryStock.text = "Stock  : "+dataStock.toString().trim()

        binding.btnToFragmentHome.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}