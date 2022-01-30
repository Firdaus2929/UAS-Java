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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.btn_clean
import kotlinx.android.synthetic.main.activity_update_data.btn_kali
import kotlinx.android.synthetic.main.activity_update_data.et_bil1
import kotlinx.android.synthetic.main.activity_update_data.et_bil2
import kotlinx.android.synthetic.main.activity_update_data.rv_data_barang
import kotlinx.android.synthetic.main.activity_update_data.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        toolbar.title = "Tambahkan Data"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val id = i.getStringExtra("id")
        val bil1 = i.getStringExtra("Bilangan1")
        val bil2 = i.getStringExtra("Bilangan2")
        val hasil = i.getStringExtra("hasil")

        et_bil1.setText(bil1)
        et_bil2.setText(bil2)
        tv_hasil.setText(hasil)
        btn_kali.setOnClickListener {
            val bilangan1 = et_bil1.text
            val bilangan2 = et_bil2.text
            val hasil = tv_text_hasil.text
            if (bilangan1.isEmpty()) {
                Toast.makeText(
                    this@UpdateDataActivity,
                    "Angka Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (bilangan2.isEmpty()) {
                Toast.makeText(
                    this@UpdateDataActivity,
                    "Angka Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(id.toString(),bilangan1.toString(), bilangan2.toString(), hasil.toString())
            }
        }

        btn_clean.setOnClickListener {
            finish()
        }
        getData()
    }


    fun actionData(id: String, bil1: String, bil2: String, hasil: String) {
        koneksi.service.updateBarang(id, bil1, bil2, hasil).enqueue(object :
            Callback<ResponseActionBarang> {
            override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseActionBarang>,
                response: Response<ResponseActionBarang>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@UpdateDataActivity,
                        "data berhasil diupdate",
                        Toast.LENGTH_LONG
                    ).show()
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

                    val rvAdapter =
                        ListContent(datacontent, this@UpdateDataActivity, "UpdateDataActivity")


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                }
            }

        })
    }
}
