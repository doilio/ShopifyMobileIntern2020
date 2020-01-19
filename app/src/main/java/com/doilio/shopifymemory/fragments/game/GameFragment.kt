package com.doilio.shopifymemory.fragments.game


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.doilio.shopifymemory.adapters.GridViewAdapter

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory
    private val gameTag = GameFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        binding.lifecycleOwner = this

        viewModelFactory = GameViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)

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
            binding.gridView.setOnItemClickListener { parent, view, position, _ ->

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
                        Log.d(
                            gameTag, "Clicked ${gridItemText.text}, Count $clicked  at position $position"
                        )
                        gridItemText.text = product.id.toString()
                        if (clicked == 2) {
                            // Comparar os itens
                            if (firstClicked == secondClicked) {
                                Toast.makeText(activity, "Same Items!", Toast.LENGTH_SHORT)
                                    .show()

                                product.cardFace = true
                                clicked = 0

                            } else {
                                Log.d(gameTag, "Different Items!")
                            }

                        }

                    }else{
                        Toast.makeText(activity, "You can only open 2 cards!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Glide.with(view.context).load(R.drawable.slab_back).into(gridItem)
                    clicked--
                    Log.d(
                        gameTag,
                        "Clicked ${gridItemText.text}, Count $clicked  at position $position"
                    )
                    gridItemText.text = "back"
                }

            }

        })

        return binding.root
    }

    private fun update(rightMoves: Int, wrongMoves: Int) {
        Log.d(gameTag, "Right Moves: $rightMoves\nWrong Moves: $wrongMoves\n")
        if (rightMoves == 10) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinnerFragment())
        }
    }


}
