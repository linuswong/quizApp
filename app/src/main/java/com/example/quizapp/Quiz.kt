package com.example.quizapp

class Quiz(val questions:List<Question>) {
    
    
    var score=0
    var counter=0
    var currentQuestion: Question= questions.get(counter)
    

    fun getQuestion():String{
        return currentQuestion.question
    }
    fun getAnswers():List<String>{
        return currentQuestion.answers
    }
    fun getCorrect():String{
        return currentQuestion.correct
    }
    
    fun answerCorrect(choice: String): Boolean{
        currentQuestion= questions.get(counter)
        if (choice.equals(currentQuestion.correct)){
        score++     
        }
     counter++
     return choice.equals(currentQuestion.correct)
    }
    fun urDone(){
     return counter+1==questions.size()   
    }

}
