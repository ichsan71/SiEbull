package com.marqumil.siebull.ui.askbully

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marqumil.siebull.data.remote.response.ResponseLabelItem
import com.marqumil.siebull.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AskbullyViewModel: ViewModel() {

    private val _labelItem = MutableLiveData<ResponseLabelItem>()
    val labelItem: LiveData<ResponseLabelItem> = _labelItem

    companion object{
        private const val TAG = "AskbullyFragmentViewModel"
    }

    fun postLabel(inputs: String) {
        //showLoading(true)
        val client = ApiConfig.getApiServiceIndobert().postInputs(inputs)
        client.enqueue(object : Callback<List<List<ResponseLabelItem>>> {
            @SuppressLint("SetTextI18n", "LongLogTag")
            override fun onResponse(
                call: Call<List<List<ResponseLabelItem>>>,
                response: Response<List<List<ResponseLabelItem>>>
            ) {
                //showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {

                    when (responseBody[0][0].label) {
                        "LABEL_0" -> {
                            _labelItem.value = responseBody[0][0]
                        }
                        "LABEL_1" -> {
                            _labelItem.value = responseBody[0][0]
                        }
                        else -> {
                            _labelItem.value = responseBody[0][0]
                        }

                    }

                    //Toast.makeText(this@MainActivity, responseBody[0][0].label, Toast.LENGTH_LONG).show()
                } else if(response.code() == 503){
                    Log.e(TAG, "onFailure 503: ${response.message()}")

                } else {
                    Log.e(TAG, "onFailure else: ${response.message()}")
                }
            }

            @SuppressLint("LongLogTag")
            override fun onFailure(call: Call<List<List<ResponseLabelItem>>>, t: Throwable) {
                //showLoading(false)
                Log.e(TAG, "onFailure yang bawah: ${t.message}")
            }
        })
    }

}