package com.doilio.shopifymemory.fragments.game


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.doilio.shopifymemory.R
import com.doilio.shopifymemory.adapters.GameAdapter
import com.doilio.shopifymemory.adapters.GameListener
import com.doilio.shopifymemory.databinding.FragmentGameBinding

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

        binding.lifecycleOwner = this

        viewModelFactory = GameViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)
        binding.viewModel = viewModel

        val adapter = GameAdapter(GameListener { productId ->
            Toast.makeText(activity, "$productId", Toast.LENGTH_SHORT).show()
            viewModel.onImageClicked(productId)

        })

        //  Lista de produtos
        viewModel.products.observe(this, Observer { products ->

            for (product in products) {

                val url = product.image.src
                val cardFace = product.cardFace
                val imgUri = url.toUri().buildUpon().scheme("https").build()
//                if (cardFace) {
//                    binding. .setImageResource(R.drawable.slab_back)
//                } else {
//                    Glide.with(imgView.context)
//                        .load(imgUri)
//                        .into(imgView)
//                }
            }
        })

        //binding.shopifyProducts.adapter = GameAdapter()
        binding.shopifyProducts.adapter = adapter

        /*val imageList = mutableListOf(
            front1,
            front2,
            front3,
            front4,
            front5,
            front6,
            front7,
            front8,
            front9,
            front10,
            front1,
            front2,
            front3,
            front4,
            front5,
            front6,
            front7,
            front8,
            front9,
            front10
        )
        val buttonList: Array<Button> = arrayOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.button10,
            binding.button11,
            binding.button12,
            binding.button13,
            binding.button14,
            binding.button15,
            binding.button16,
            binding.button17,
            binding.button18,
            binding.button19,
            binding.button20
        )

        var clicked = 0
        var firstClicked = -1
        var secondClicked = -1
        var wrongMoves = 0
        var rightMoves = 0

        imageList.shuffle()

        for (i in 0..19) {
            buttonList[i].text = "back"
            buttonList[i].textSize = 0F

            buttonList[i].setOnClickListener {                                                      ****

                if (buttonList[i].text == "back" && clicked < 2) {
                    buttonList[i].setBackgroundResource(imageList[i])
                    buttonList[i].text = imageList[i].toString()

                    if (clicked == 0) {
                        firstClicked = i
                    } else {
                        secondClicked = i
                    }
                    clicked++
                    if (clicked == 2) {
                        if (buttonList[firstClicked].text == buttonList[secondClicked].text) {
                            Toast.makeText(context, "Same items", Toast.LENGTH_SHORT).show()
                            buttonList[firstClicked].isClickable = false
                            buttonList[secondClicked].isClickable = false
                            rightMoves++
                            binding.player1Count.text = rightMoves.toString()
                            clicked = 0
                        } else {
                            Toast.makeText(context, "Different", Toast.LENGTH_SHORT).show()
                            wrongMoves++
                        }
                        update(rightMoves, wrongMoves)
                    }

                } else if (buttonList[i].text != "back") {
                    buttonList[i].setBackgroundResource(back_card)
                    buttonList[i].text = "back"
                    if (clicked == 0) {
                        firstClicked = -1
                    } else {
                        secondClicked = -1
                    }
                    clicked--
                }
            }

        }
*/

        return binding.root
    }


    private fun update(rightMoves: Int, wrongMoves: Int) {
        Log.d("VERIFICACAO", "Right Moves: $rightMoves\nWrong Moves: $wrongMoves\n")
        if (rightMoves == 10) {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToWinnerFragment())
        }
    }


}
