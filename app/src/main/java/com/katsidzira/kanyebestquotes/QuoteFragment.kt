package com.katsidzira.kanyebestquotes

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*


class QuoteFragment : Fragment() {
    var quote = Quote("")
    var color = generateRandomColor()
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quote = listener?.onQuoteRequest()!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.rootView.setBackgroundColor(color)

        val cardView = view.findViewById<View>(R.id.cardview)
        cardView.setBackgroundColor(color)

        val quoteTextView = view.findViewById<TextView>(R.id.quote_textview)
        val button = view.findViewById<Button>(R.id.next_quote_button)

        quoteTextView.text = quote.toString()

        button.setOnClickListener {
            color = generateRandomColor()
            quote = listener?.onQuoteRequest()!!
            cardView.setBackgroundColor(color)
            view.rootView.setBackgroundColor(color)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onQuoteRequest(): Quote
    }

    fun generateRandomColor(): Int {
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        return color
    }
}
