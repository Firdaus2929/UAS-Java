package com.informatika.firdaushermawan_19100018_kalkulator

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.firdaushermawan_19100018_kalkulator.adapter.ListContent
import com.informatika.firdaushermawan_19100018_kalkulator.model.ResponseActionBarang
import com.informatika.firdaushermawan_19100018_kalkulator.model.ResponseBarang
import kotlinx.android.synthetic.main.activity_insert_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "Tambahkan Data"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_kali.setOnClickListener {
            val bilangan1 = et_bil1.text
            val bilangan2 = et_bil2.text
            if (bilangan1.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Angka Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (bilangan2.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Angka Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(bilangan1.toString(), bilangan2.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
        et_bil1.text.clear()
        et_bil2.text.clear()

    }

    fun actionData(bilangan1: String, bilangan2: String) {
        koneksi.service.insertBarang(bilangan1, bilangan2)
            .enqueue(object : Callback<ResponseActionBarang> {
                override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseActionBarang>,
                    response: Response<ResponseActionBarang>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}

