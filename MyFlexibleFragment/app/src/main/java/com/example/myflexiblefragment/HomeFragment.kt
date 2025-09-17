package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.myflexiblefragment.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnKategori.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_kategori -> {

                // non ktx
//                parentFragmentManager.beginTransaction().apply {
//                    replace(R.id.frame_container, CategoryFragment(), CategoryFragment::class.java.simpleName)
//                    addToBackStack(null)
//                    commit()
//                }
                // with ktx
                parentFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.frame_container, CategoryFragment(), CategoryFragment::class.java.simpleName)
                }
            }
        }
    }
}