package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.security.acl.Group

class MainActivity : AppCompatActivity() {

   companion object{
       val TAG ="MainActivity"
   }
   private lateinit var answer1: Button
   private lateinit var answer2: Button
   private lateinit var answer3: Button
   private lateinit var answer4: Button

   private lateinit var question: TextView
   private lateinit var score: TextView

   private lateinit var finalScore: TextView
   private lateinit var restart: Button


   private lateinit var thingy :Quiz

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
       wireWidgets()



       loadQuestions()
       setup()
       restart.visibility=View.GONE
       finalScore.visibility=View.GONE

       answer1.setOnClickListener{
           thingy.answerCorrect(answer1.text.toString())
           setup()
       }
       answer2.setOnClickListener{
           thingy.answerCorrect(answer2.text.toString())
           setup()

       }
       answer3.setOnClickListener{
           thingy.answerCorrect(answer3.text.toString())
           setup()
       }
       answer4.setOnClickListener{
           thingy.answerCorrect((answer4.text).toString())
           setup()
       }

       restart.setOnClickListener {
           loadQuestions()
           setup()

           finalScore.visibility=View.GONE
           restart.visibility=View.GONE

           answer1.visibility=View.VISIBLE
           answer2.visibility=View.VISIBLE
           question.visibility=View.VISIBLE
           score.visibility=View.VISIBLE

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
       val answerSize=thingy.getAnswers().size
       question.text=thingy.getQuestion()
       score.text=getString(R.string.main_score,thingy.tScore)

       answer3.visibility=View.INVISIBLE
       answer4.visibility=View.INVISIBLE

       if(answerSize>=3){
           answer3.visibility= View.VISIBLE
           answer3.text=thingy.getAnswers()[2]
       }
       if(answerSize==4){
           answer4.visibility= View.VISIBLE
           answer4.text=thingy.getAnswers()[3]
       }

       answer1.text=thingy.getAnswers()[0]
       answer2.text=thingy.getAnswers()[1]
       Log.d(TAG,"thingy"+(answer1.text))
       if(thingy.urDone()){
           answer1.visibility=View.GONE
           answer2.visibility=View.GONE
           answer3.visibility=View.GONE
           answer4.visibility=View.GONE

           score.visibility=View.GONE
           question.visibility=View.GONE

           restart.visibility=View.VISIBLE
           finalScore.visibility=View.VISIBLE

           restart.text="Restart"
           @SuppressLint("StringFormatInvalid")
           finalScore.text= getString(R.string.main_score,thingy.tScore)
       }







   }

   private fun wireWidgets() {
       answer1=findViewById(R.id.button_Main_a1)
       answer2=findViewById(R.id.button_Main_a2)
       answer3=findViewById(R.id.button_Main_a3)
       answer4=findViewById(R.id.button_Main_a4)

             question=findViewById(R.id.textView_Main_Question)
       score=findViewById(R.id.textView_Main_Score)

       finalScore=findViewById(R.id.textView_Main_endScore)
       restart=findViewById(R.id.button_Main_restart)
   }
}
