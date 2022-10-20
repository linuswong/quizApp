package com.example.quizapp

class Quiz(val questions:List<Question>) {


   var tScore=0
   var counter=0
   var currentQuestion: Question= questions.get(0)



   fun getQuestion():String{
       return currentQuestion.question
   }
   fun getAnswers():List<String>{
       return currentQuestion.answers
   }
   fun getCorrect():String{
       return currentQuestion.correct
   }

   fun answerCorrect(choice: String){
       currentQuestion= questions.get(counter)
       if (choice.equals(getCorrect())){
           tScore++
       }
       counter++
       if(!(counter==questions.size)){
           currentQuestion = questions.get(counter)
       }
   }
   fun urDone():Boolean{
       return counter==questions.size}

}
