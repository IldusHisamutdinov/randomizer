package com.rsschool.android2021

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var min: EditText
    private lateinit var max: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Предыдущий результат: ${result.toString()}"

        min = view.findViewById(R.id.min_value)
        max = view.findViewById(R.id.max_value)
        generateButton?.setOnClickListener {
            if (min.text.toString().length < 1) {
                println("не заполнил min")
                return@setOnClickListener
            }
            if (max.text.toString().length < 1) {
                println("не заполнил max")
                return@setOnClickListener
            }
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    SecondFragment.newInstance(
                        min.text.toString().toInt(),
                        max.text.toString().toInt()
                    )
                )
                .addToBackStack("first")
                .commit()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}