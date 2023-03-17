package com.kodego.diangca.ebrahim.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kodego.diangca.ebrahim.fragments.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private  var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null) {
            var positionApplied = arguments?.getString("positionApplied")
            var desiredSalary = arguments?.getDouble("desiredSalary")!!.toDouble()
            var dateAvailable = arguments?.getString("dateAvailable")

            Snackbar.make(
                view,
                "$positionApplied \n $desiredSalary $dateAvailable",
                Snackbar.LENGTH_LONG
            ).show()
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}