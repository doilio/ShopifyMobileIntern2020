package com.doilio.shopifymemory.fragments


import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)
        binding.buttonPlay.setOnClickListener { findNavController().navigate(MainFragmentDirections.actionMainFragmentToGameFragment(2)) }

        return binding.root
    }

}
