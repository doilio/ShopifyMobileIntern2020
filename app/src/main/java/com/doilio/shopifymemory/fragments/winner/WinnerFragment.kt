package com.doilio.shopifymemory.fragments.winner


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.databinding.FragmentWinnerBinding

/**
 * A simple [Fragment] subclass.
 */
class WinnerFragment : Fragment() {

    private lateinit var binding: FragmentWinnerBinding
    private lateinit var args: WinnerFragmentArgs
    private lateinit var viewModel: WinnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_winner, container, false)
        setHasOptionsMenu(true)
        args = WinnerFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = WinnerViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WinnerViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.navigateToGameFragment.observe(this, Observer {
            it?.let {
                if (it) {
                    findNavController().navigate(WinnerFragmentDirections.actionWinnerFragmentToGameFragment())
                    viewModel.navigateCompleted()
                }
            }
        })

        binding.winnerMessage.text =
            "You won the game with ${args.pairsMatched} Pairs Matched! \nand only ${args.wrongMoves} Wrong Moves."


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                shareVictory("I Just won the Shopify Memory Game with ${args.pairsMatched} pairs matched and only ${args.wrongMoves} wrong moves. Try it out *link*")
            }
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
