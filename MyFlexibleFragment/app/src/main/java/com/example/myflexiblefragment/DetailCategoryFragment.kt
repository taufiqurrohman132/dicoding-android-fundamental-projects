package com.example.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myflexiblefragment.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment() {
    private var _binding: FragmentDetailCategoryBinding? = null
    private val binding get() = _binding!!

        var description: String? = null

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
        // tidak guna
        if (savedInstanceState != null){
            val deskripFromBundle = savedInstanceState.getString(EXTRA_DESCRIP)
            description = deskripFromBundle
        }

        if (arguments != null){
            Log.i("arg", "arg not null")
            // mengambil data menggunakan bundle
            val categoryName = arguments?.getString(EXTRA_NAME)

            // menggunakan getter setter
            binding.tvCategoryName.text = categoryName
            binding.tvCategoryDeskrip.text = description
        }

        binding.btnShowDialog.setOnClickListener {
            val optionDialogFragment = OptionDialogkFragment()

            val fragmentManager = childFragmentManager
            // menampilkan objek dialog fragment ke layar
            optionDialogFragment.show(fragmentManager, OptionDialogkFragment::class.java.simpleName)
        }
        binding.btnProfile.setOnClickListener {
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // di panggil ketika opsi dialog di pilih
    public var optionDialogListene: OptionDialogkFragment.OnOptionDialogListener = object : OptionDialogkFragment.OnOptionDialogListener{
        override fun onOptionChosen(text: String?) {
            Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIP = "extra_deskrip"
    }
}