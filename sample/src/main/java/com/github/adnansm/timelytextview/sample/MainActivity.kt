package com.github.adnansm.timelytextview.sample


import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Spinner
import com.github.adnansm.timelytextview.TimelyView
import android.animation.ObjectAnimator

class MainActivity : Activity() {
    private var timelyView: TimelyView? = null

    @Volatile
    private var objectAnimator: ObjectAnimator? = null

    @Volatile
    private var from = NO_VALUE

    @Volatile
    private var to = NO_VALUE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timelyView = findViewById<View>(R.id.textView1) as TimelyView
        val seekBar = findViewById<View>(R.id.seekBar) as SeekBar
        val fromSpinner = findViewById<View>(R.id.fromSpinner) as Spinner
        val toSpinner = findViewById<View>(R.id.toSpinner) as Spinner
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.from_numbers_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter
        fromSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                from = position - 1
                if (from != NO_VALUE && to != NO_VALUE) {
                    objectAnimator = timelyView!!.animate(from, to)
                    objectAnimator!!.duration = DURATION.toLong()
                } else {
                    objectAnimator = null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        toSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                to = position - 1
                if (from != NO_VALUE && to != NO_VALUE) {
                    objectAnimator = timelyView!!.animate(from, to)
                    objectAnimator!!.duration = DURATION.toLong()
                } else {
                    objectAnimator = null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        seekBar.max = DURATION
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (objectAnimator != null) objectAnimator!!.currentPlayTime = progress.toLong()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    companion object {
        const val DURATION = 1000
        const val NO_VALUE = -1
    }
}