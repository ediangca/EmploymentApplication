package com.kodego.diangca.ebrahim.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultOwner
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.fragments.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.util.*


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            var bundle = bundleOf(
                "positionApplied" to "positionApply",
                "desiredSalary" to 0.0,
                "dateAvailable" to SimpleDateFormat("yyyy-MM-d").format(Date()).toString()
            )
            var bundle1 = Bundle()
            bundle1.putString("positionApplied", "positionApply")
            bundle1.putDouble("desiredSalary", 0.0)
            bundle1.putString(
                "dateAvailable",
                SimpleDateFormat("yyyy-MM-d").format(Date())
            )

            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}