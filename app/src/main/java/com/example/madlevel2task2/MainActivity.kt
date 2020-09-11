package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        questions.add(Question("Should be true",true))
        questions.add(Question("Should be false",false))
        questions.add(Question("Should be true",true))
        questions.add(Question("Should be false",false))


        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {


        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(checkCorrect(questions.get(position), direction)) {
                    questions.removeAt(position)
                    if(questions.size == 0) onComplete()
                } else{
                    Snackbar.make(binding.tvAnswer,R.string.incorrect_answer,Snackbar.LENGTH_SHORT).show()
                }
                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun checkCorrect(question: Question,direction: Int): Boolean{
        return question.correctAnswer && direction == 8 || !question.correctAnswer && direction == 4
    }

    private fun onComplete(){
        Snackbar.make(binding.tvAnswer,R.string.congratulations,Snackbar.LENGTH_SHORT).show()
    }
}
