package com.kodego.diangca.ebrahim.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.kodego.diangca.ebrahim.fragments.databinding.FragmentBBinding
import java.text.SimpleDateFormat
import java.util.*

class BFragment : Fragment() {
    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.btnNext.setOnClickListener {
            var bundle = bundleOf("positionApplied" to "positionApplied",
                "AFragment" to "aFragment",
                "dateAvailable" to SimpleDateFormat("yyyy-MM-d").format(Date()).toString()
            )

            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}