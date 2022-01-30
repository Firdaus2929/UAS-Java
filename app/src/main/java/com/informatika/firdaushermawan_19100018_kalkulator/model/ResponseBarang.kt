package com.informatika.firdaushermawan_19100018_kalkulator.model

import com.google.gson.annotations.SerializedName

data class ResponseBarang(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem(

	@field:SerializedName("bil1")
	val bil1: String? = null,

	@field:SerializedName("bil2")
	val bil2: String? = null,

	@field:SerializedName("hasil")
	val hasil: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
