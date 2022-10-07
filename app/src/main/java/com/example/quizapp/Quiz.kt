package com.example.quizapp

class Quiz(val questions:List<Question>) {

    fun getQuestion():String{
        return questions.get(0).question
    }
    fun getAnswers():List<String>{
        return questions.get(0).answers
    }
    fun getCorrect():String{
        return questions.get(0).correct
    }

}