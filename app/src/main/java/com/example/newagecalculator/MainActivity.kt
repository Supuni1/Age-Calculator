package com.example.newagecalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonBirthday: Button = findViewById(R.id.buttonBirthday)

        buttonBirthday.setOnClickListener {view->
            datePicker(view)
            //Toast.makeText(this,"You clicked the button,",Toast.LENGTH_SHORT).show()
        }


    }

    fun datePicker(view:View){

        val calendar=Calendar.getInstance()
        val year=calendar.get(Calendar.YEAR)
        val month=calendar.get(Calendar.MONTH)
        val dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            Toast.makeText(this,"Date is :$dayOfMonth/${month+1}/$year",Toast.LENGTH_SHORT).show()

            val selectedDte="$dayOfMonth/$month/$year"
            val tvSelectedDate: TextView = findViewById(R.id.tvSelectedDate)
            tvSelectedDate.text=selectedDte

            val simpleDate=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date=simpleDate.parse(selectedDte)

            val seconds=date!!.time/1000

            val todateInSeconds=simpleDate.parse(simpleDate.format(System.currentTimeMillis()))!!.time/1000

            val trueTimeinSeconds =todateInSeconds-seconds

            val inSeconds: TextView = findViewById(R.id.inSeconds)
            inSeconds.text="$trueTimeinSeconds seconds"

            val inMinutes: TextView = findViewById(R.id.inMinutes)
            inMinutes.text="${trueTimeinSeconds/60} minutes"

            val inHoursText: TextView = findViewById(R.id.inHoursText)
            inHoursText.text="${trueTimeinSeconds/60/60} hours"

            val inDaysText: TextView = findViewById(R.id.inDaysText)
            inDaysText.text="${trueTimeinSeconds/86400} days"






        },year,month,dayOfMonth)

        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()

    }
}