package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private lateinit var openResult: LinearLayout
    private var openButton: Button? = null
    private var generate: Button? = null
    private var fragmentCommunicator: Communicator? = null

    fun setOnFragmentListener(fragmentCommunicator: Communicator) {
        this.fragmentCommunicator = fragmentCommunicator
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)
        openResult = view.findViewById(R.id.open_result)
        openButton = view.findViewById(R.id.open)
        generate = view.findViewById(R.id.generate)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        val c = generate(min, max + 1)
        result?.text = c.toString()

        backButton?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment.newInstance(c))
                .addToBackStack("two")
                .commit()
        }

        openButton?.setOnClickListener{
            openResult.setVisibility(LinearLayout.VISIBLE)
        }

        generate?.setOnClickListener{
            openResult.setVisibility(LinearLayout.INVISIBLE)
            val count = generate(min, max + 1)
            result?.text = count.toString()
        }

    }

    private fun generate(min: Int, max: Int): Int {
        return Random.nextInt(min, max)
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}