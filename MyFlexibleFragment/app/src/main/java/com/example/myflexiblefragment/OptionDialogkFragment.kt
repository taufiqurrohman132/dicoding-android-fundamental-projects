package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.myflexiblefragment.databinding.FragmentOptionDialogkBinding

class OptionDialogkFragment : DialogFragment() {
    private var _binding: FragmentOptionDialogkBinding? = null
    private val binding get() = _binding!!

    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOptionDialogkBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPilih.setOnClickListener {
            val chekedRB = binding.rgOption.checkedRadioButtonId
            Log.i("RG", "id rb : $chekedRB")
            if (chekedRB != -1){
                val coach: String? = when(chekedRB){
                    R.id.rb_lvg -> binding.rbLvg.text.toString().trim()
                    R.id.rb_saf -> binding.rbSaf.text.toString().trim()
                    R.id.rb_dm -> binding.rbDm.text.toString().trim()
                    R.id.rb_jm -> binding.rbJm.text.toString().trim()
                    else -> null
                }

                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }
        binding.btnKeluar.setOnClickListener {
            dialog?.cancel()
        }

    }

    // ketika fragment dipanggil dan  di matikan
    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment // detail fragment

        // parent fragment itu masih general, jadi harus di spesifik dulu. bisa juga misal menggunakan as
        // di inisiat dari detail kategori frag
        if (fragment is DetailCategoryFragment){
            this.optionDialogListener = fragment.optionDialogListene // priperti dari detail fragment
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // sebagai event handling ketika tombol pilih di klik
    interface OnOptionDialogListener{
        fun onOptionChosen(text: String?)
    }
}