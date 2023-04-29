package com.marqumil.siebull.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseLabelZero(

	@field:SerializedName("ResponseLabelZero")
	val responseLabelZero: List<ResponseLabelOne>
)

data class ResponseLabelOne(

	@field:SerializedName("ResponseLabel")
	val responseLabel: List<ResponseLabelItem>
)

data class ResponseLabelItem(

	@field:SerializedName("score")
	val score: Any,

	@field:SerializedName("label")
	val label: String
)
