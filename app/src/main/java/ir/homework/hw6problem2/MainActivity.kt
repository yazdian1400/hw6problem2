package ir.homework.hw6problem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import ir.homework.hw6problem2.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var arrValue = MutableList(3){ MutableList(3){Values.EMPTY} }
    var count = 0
    var winner = Winner.NOBODY


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener{
            onClickButton(0, 0, binding.btn1)
        }
        binding.btn2.setOnClickListener{
            onClickButton(0, 1, binding.btn2)
        }
        binding.btn3.setOnClickListener{
            onClickButton(0, 2, binding.btn3)
        }
        binding.btn4.setOnClickListener{
            onClickButton(1, 0, binding.btn4)
        }
        binding.btn5.setOnClickListener{
            onClickButton(1, 1, binding.btn5)
        }
        binding.btn6.setOnClickListener{
            onClickButton(1, 2, binding.btn6)
        }
        binding.btn7.setOnClickListener{
            onClickButton(2, 0, binding.btn7)
        }
        binding.btn8.setOnClickListener{
            onClickButton(2, 1, binding.btn8)
        }
        binding.btn9.setOnClickListener{
            onClickButton(2, 2, binding.btn9)
        }
    }

    fun onClickButton(i: Int, j: Int, button: Button){
        if (winner == Winner.NOBODY && button.text == "") {
            count++
            button.text = if (count % 2 == 1) "X" else "O"
            arrValue[i][j] = if (count % 2 == 1) Values.X else Values.O
            if (isFinished(i, j)) {
                Toast.makeText(this, "finished", Toast.LENGTH_LONG).show()
            }
        }
    }

    @JvmName("isFinished1")
    fun isFinished(i: Int, j: Int): Boolean{
        if (count == 9){
            winner = Winner.DRAW
            return true
        }
        if (arrValue[i][0] == arrValue[i][1] && arrValue[i][0] == arrValue[i][2] && arrValue[i][0]!=Values.EMPTY){
            winner = if (count % 2 == 1)  Winner.X    else    Winner.O
            return true
        }
        if (arrValue[0][j] == arrValue[1][j] && arrValue[0][j] == arrValue[2][j] && arrValue[0][j]!=Values.EMPTY){
            winner = if (count % 2 == 1)  Winner.X    else    Winner.O
            return true
        }
        if (i == j){
            if (arrValue[0][0] == arrValue[1][1] && arrValue[0][0] == arrValue[2][2] && arrValue[0][0]!=Values.EMPTY){
                winner = if (count % 2 == 1)  Winner.X    else    Winner.O
                return true
            }
        }
        if (i + j == 2){
            if (arrValue[0][2] == arrValue[1][1] && arrValue[0][2] == arrValue[2][0] && arrValue[0][2]!=Values.EMPTY){
                winner = if (count % 2 == 1)  Winner.X    else    Winner.O
                return true
            }
        }
        return false
    }

}

enum class Values{
    X,
    O,
    EMPTY
}

enum class Winner{
    X,
    O,
    DRAW,
    NOBODY
}