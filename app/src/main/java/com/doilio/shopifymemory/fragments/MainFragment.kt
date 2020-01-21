package com.doilio.shopifymemory.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.databinding.FragmentMainBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var gameMode: Int = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)
        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToGameFragment(
                    gameMode
                )
            )
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.game_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.match_two -> {
                gameMode = 2
            }
            R.id.match_three -> {
                gameMode = 3
            }
            R.id.match_four -> {
                gameMode = 4
            }
            else -> Timber.d( "Invalid Option")
        }
        return super.onOptionsItemSelected(item)
    }
}
