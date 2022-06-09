package com.wtech.connect3

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.allViews
import androidx.core.view.children
import androidx.core.view.get
import com.wtech.connect3.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var gameState:Array<Int>
    private lateinit var winingPositions:Array<Array<Int>>
    private var activePlayer by Delegates.notNull<Int>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activePlayer=0
        gameState= arrayOf(2,2,2,2,2,2,2,2,2)


        winingPositions= arrayOf(
            arrayOf(0,1,2),
            arrayOf(3,4,5),
            arrayOf(6,7,8),
            arrayOf(0,3,6),
            arrayOf(1,4,7),
           arrayOf(2,5,8),
            arrayOf(0,4,8),
            arrayOf(2,4,6)
        )
//        state 2 is un played
        binding.playAgain.setOnClickListener {
            binding.linear.visibility=View.GONE
            activePlayer=0
            for (i in 0..gameState.lastIndex){
                gameState[i]=2
            }

            binding.apply {
                img0.setImageResource(0)
                img8.setImageResource(0)
                img1.setImageResource(0)
                img2.setImageResource(0)
                img3.setImageResource(0)
                img4.setImageResource(0)
                img5.setImageResource(0)
                img6.setImageResource(0)
                img7.setImageResource(0)
            }
            


        }
    }


    fun dropIn(view: View) {
        val imageClicked:ImageView= view as ImageView

        imageClicked.alpha=1f
        val imageTapped: Int =imageClicked.tag.toString().toInt()
        if (gameState[imageTapped]==2){
            gameState[imageTapped]=activePlayer
            imageClicked.translationY=-1000f
            activePlayer = if(activePlayer==0){
                imageClicked.setImageResource(R.drawable.yellow)
                1
            } else{
                imageClicked.setImageResource(R.drawable.green)
                0
            }


            imageClicked.animate().alpha(1f).translationYBy(1000f)
                .rotationBy(3600f)
                .duration=500


            for (winingPosition in winingPositions){
                when {
                    gameState[winingPosition[0]]==gameState[winingPosition[1]]
                            && gameState[winingPosition[1]]==gameState[winingPosition[2]]
                            && gameState[winingPosition[0]]!=2 -> {
                        var  winner="Green"
                        if(gameState[winingPosition[0]]==0){
                            winner="Yellow"
                        }
                        binding.winnerText.text = "Winner: $winner"
                        binding.linear.animate().y(-1000f)

                        binding.linear.animate().alphaBy(1f).duration=300
                        binding.linear.visibility = View.VISIBLE
                        binding.linear.animate()
                            .rotation(360f)
                            .translationYBy(100f)
                            .duration=300



                    }

                }


            }
        }
    }



}