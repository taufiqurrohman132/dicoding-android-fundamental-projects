package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit

class CategoryFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDetailKategori = view.findViewById<Button>(R.id.btn_detail_category)
        btnDetailKategori.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_detail_category -> {
                val detailCategoryFrag = DetailCategoryFragment()

                // menggirim menggunakan bundle
                val bundle = Bundle()
                bundle.putString(DetailCategoryFragment.EXTRA_NAME, "LifeStyle")
                val description = "Kategori ini akan berisi produk-produk lifestyle"

                detailCategoryFrag.arguments = bundle // ketika tidak di inisiat objek bundel dari sini maka tidak bisa krim data
                // set
                detailCategoryFrag.description = description

                // non ktx
//                parentFragmentManager.beginTransaction().apply {
//                    replace(R.id.frame_container, detailCategoryFrag, DetailCategoryFragment::class.java.simpleName)
//                    addToBackStack(null)
//                    commit()
//                }

                // with ktx
                parentFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.frame_container, DetailCategoryFragment(), DetailCategoryFragment::class.java.simpleName)
                }

            }
        }
    }
}