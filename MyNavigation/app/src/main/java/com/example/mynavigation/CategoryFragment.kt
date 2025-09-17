package com.example.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.mynavigation.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        DetailCategoryFragment().arguments = bundle

        binding.btnToKategoriLifestyle.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString(EXTRA_NAME, "lifeStyle")
//            bundle.putInt(EXTRA_STOCK, 7)
            // navigate dan mengirim data pakai bundle
//            view.findNavController().navigate(R.id.action_categoryFragment_to_detailCategoryFragment, bundle)

            // safe args
            val toDetailKategoriFrag = CategoryFragmentDirections.actionCategoryFragmentToDetailCategoryFragment()
            toDetailKategoriFrag.name = "lifesti"
            toDetailKategoriFrag.stcok = 7
            view.findNavController().navigate(toDetailKategoriFrag)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val EXTRA_NAME = "name"
        const val EXTRA_STOCK = "stok"
    }


}