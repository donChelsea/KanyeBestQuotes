package com.katsidzira.kanyebestquotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import com.katsidzira.kanyebestquotes.network.QuoteService
import com.katsidzira.kanyebestquotes.network.RetrofitSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.text.Typography.quote

class MainActivity : AppCompatActivity(), QuoteFragment.OnFragmentInteractionListener {
    private val TAG = "main activity"
    private val quoteFragment = QuoteFragment()
    private val fragmentManager = supportFragmentManager
    private var quote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTrans = fragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.container, quoteFragment)
        fragmentTrans.addToBackStack("first").commit()
    }

    override fun onQuoteRequest(): String {
        val retrofit = RetrofitSingleton().getInstance()
        val quoteService = retrofit!!.create(QuoteService::class.java)
        quoteService.getKanyeQuotes().enqueue(object : Callback<Quote> {
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                quote = response.body()!!.toString().substring(12)
                Log.d(TAG, "onResponse: $quote")
            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                Log.d(TAG, "on failure: ${t.message}")
            }
        })

        return quote
    }


}
