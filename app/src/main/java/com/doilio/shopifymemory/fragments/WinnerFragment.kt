package com.doilio.shopifymemory.fragments


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.databinding.FragmentWinnerBinding

/**
 * A simple [Fragment] subclass.
 */
class WinnerFragment : Fragment() {

    private lateinit var binding: FragmentWinnerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_winner, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.share -> shareVictory( "I Just won the Shopify Memory Game in mm:ss, with only xx wrong moves. Try it out ***link****")
        }
        return super.onOptionsItemSelected(item)

    }

    private fun shareVictory(msg: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, msg)
        startActivity(Intent.createChooser(intent, "Share victory using:"))
    }


}
