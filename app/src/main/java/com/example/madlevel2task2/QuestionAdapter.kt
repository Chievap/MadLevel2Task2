package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.QuestionBinding

class QuestionAdapter (private val questions: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(questionView: View) : RecyclerView.ViewHolder(questionView){
        val binding = QuestionBinding.bind(itemView)

        fun databind(question: Question) {
            binding.tvQuestion.text = question.question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(questions[position])
    }
}