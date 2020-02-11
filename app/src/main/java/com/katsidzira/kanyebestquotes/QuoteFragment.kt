package com.katsidzira.kanyebestquotes

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.katsidzira.kanyebestquotes.network.QuoteService
import com.katsidzira.kanyebestquotes.network.RetrofitSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class QuoteFragment : Fragment() {
    var quote = ""
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quote = listener?.onQuoteRequest()!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_quote, container, false)

        val quoteTextView = view!!.findViewById<TextView>(R.id.quote_textview)
        val button = view!!.findViewById<Button>(R.id.next_quote_button)

        quoteTextView.text = quote

        button.setOnClickListener { v -> quote = listener?.onQuoteRequest()!! }

        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onQuoteRequest(): String
    }

}
