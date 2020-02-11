package com.katsidzira.kanyebestquotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import com.katsidzira.kanyebestquotes.network.QuoteService
import com.katsidzira.kanyebestquotes.network.RetrofitSingleton
import kotlinx.android.synthetic.main.fragment_quote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.text.Typography.quote

class MainActivity : AppCompatActivity(), QuoteFragment.OnFragmentInteractionListener {
    private val TAG = "main activity"
    private val quoteFragment = QuoteFragment()
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTrans = fragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.container, quoteFragment)
        fragmentTrans.addToBackStack("first").commit()
    }

    override fun onQuoteRequest(): Quote {
        var quote: Quote = Quote("")
        val retrofit = RetrofitSingleton().getInstance()
        val quoteService = retrofit!!.create(QuoteService::class.java)
        quoteService.getKanyeQuotes().enqueue(object : Callback<Quote> {
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                quote = response.body()!!
                quote_textview.text = quote.toString()
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                Log.d(TAG, "on failure: ${t.message}")
            }
        })
        return quote
    }


}
