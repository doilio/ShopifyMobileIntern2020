package com.doilio.shopifymemory.fragments.game


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.doilio.shopifymemory.adapters.GridViewAdapter

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.databinding.FragmentGameBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        setHasOptionsMenu(true)
        binding.lifecycleOwner = this

        val args = GameFragmentArgs.fromBundle(arguments!!)
        viewModelFactory = GameViewModelFactory(args.pairs)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        binding.viewModel = viewModel

        //  Lista de produtos
        viewModel.products.observe(this, Observer { products ->


            val adapter = GridViewAdapter(
                activity!!.applicationContext,
                products
            )
            binding.gridView.adapter = adapter

            var clicked = 0
            var firstClicked = -1L
            var secondClicked = -1L

            binding.gridView.setOnItemClickListener { _, view, position, _ ->

                val product = products[position]
                val productImage = product.image.src
                val gridItemText = view.findViewById<TextView>(R.id.card_text)
                val gridItem = view.findViewById<ImageView>(R.id.product_image)


                // Logica para o jogo
                if (gridItemText.text == "back") {
                    if (clicked < 2) {

                        if (clicked == 0) {
                            firstClicked = product.id
                        } else {
                            secondClicked = product.id
                        }
                        Glide.with(view.context).load(productImage).into(gridItem)
                        clicked++
                        Timber.d("Clicked ${gridItemText.text}, Count $clicked  at position $position")
                        gridItemText.text = product.id.toString()
                        if (clicked == 2) {
                            // Comparar os itens
                            if (firstClicked == secondClicked) {
                                Timber.d("Same Items!")
                                viewModel.incrementRightMoves()
                                product.cardFace = true
                                clicked = 0

                            } else {
                                viewModel.incrementWrongMoves()
                                Timber.d("Different Items!")
                            }

                        }

                    } else {
                        Toast.makeText(activity, "You can only open 2 cards!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Glide.with(view.context).load(R.drawable.slab_back).into(gridItem)
                    clicked--
                    Timber.d("Clicked ${gridItemText.text}, Count $clicked  at position $position")
                    gridItemText.text = getString(R.string.back)
                }

                Timber.d("Right Moves: ${viewModel.rightMoves.value}\nWrong Moves: ${viewModel.wrongMoves.value}\n")
            }
        })

        viewModel.rightMoves.observe(this, Observer { rightMoves ->
            val wrongMoves = viewModel.wrongMoves.value!!
            val totalRightMoves = viewModel.totalRightMoves.value!!
            (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.game_fragment_title, rightMoves, totalRightMoves)
            if (rightMoves == totalRightMoves) {
                findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentToWinnerFragment(
                        rightMoves,
                        wrongMoves,
                        args.pairs
                    )
                )
            }
            Timber.d( "valor no fragment: $totalRightMoves")

        })

        return binding.root
    }

}
