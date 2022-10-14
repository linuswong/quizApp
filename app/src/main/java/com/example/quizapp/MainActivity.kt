package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG ="MainActivity"
    }

    private lateinit var quiz:Quiz

    private lateinit var answer1: Button
    private lateinit var answer2: Button
    private lateinit var answer3: Button
    private lateinit var answer4: Button

    private lateinit var question: TextView
    private lateinit var score: TextView

    private lateinit var thingy :Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wireWidgets()
        loadQuestions()
        setup()
        
        answer1.setOnClickListener{
            thingy.checkAnswer(answer1.text)
        }
        answer2.setOnClickListener{
            thingy.checkAnswer(answer2.text)
            
        }
        answer3.setOnClickListener{
            thingy.checkAnswer(answer3.text)
            
        }
        answer4.setOnClickListener{
            thingy.checkAnswer(answer4.text)
            
        }




    }

    private fun loadQuestions() {
        val inputStream = resources.openRawResource(R.raw.questions)

        val jsonString = inputStream.bufferedReader().use{
            it.readText()
        }

        val gson=Gson()

        val type=object:TypeToken<List<Question>>(){}.type
        val questions = gson.fromJson<List<Question>>(jsonString,type)

        Log.d(TAG,"onCreate:$jsonString")
        thingy=Quiz(questions)
    }
    private fun setup(){
        //var holder:List<String> =thingy.getAnswers()
        //val nums:List<Int> =   mutableListOf(0,1)
        var x=thingy.getAnswers.size()
        question.text=thingy.getQuestion()
        score.text="$(scoreText)"+ thingy.score
        
        answer3.visibility=View.INVISIBLE
        answer4.visibility=View.INVISIBLE
        

//         if(x>=2){
//             answer1.visibility= View.VISIBLE
//             answer2.visibility= View.VISIBLE
//         }
        if(x>=3){
            answer3.visibility= View.VISIBLE
        }
        if(x==4){
            answer4.visibility= View.VISIBLE
        }



    }

    private fun wireWidgets() {
        answer1=findViewById(R.id.button_Main_a1)
        answer2=findViewById(R.id.button_Main_a2)
        answer3=findViewById(R.id.button_Main_a3)
        answer4=findViewById(R.id.button_Main_a4)

        question=findViewById(R.id.textView_Main_Question)
        score=findViewById(R.id.textView_Main_Score)
    }
}

