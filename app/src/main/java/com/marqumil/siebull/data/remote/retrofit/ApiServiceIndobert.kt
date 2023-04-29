package com.marqumil.siebull.data.remote.retrofit

import com.marqumil.siebull.data.remote.response.ResponseLabelItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceIndobert {

    @FormUrlEncoded
    @Headers("Authorization: Bearer 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")
    @POST("models/mdhugol/indonesia-bert-sentiment-classification/")
    fun postInputs(
        @Field("inputs") inputs: String
    ): Call<List<List<ResponseLabelItem>>>

}