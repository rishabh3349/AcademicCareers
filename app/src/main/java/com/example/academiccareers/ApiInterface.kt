package com.example.academiccareers

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("echo?user_content_key=EdGjnmlXs7JQTCfkAWSulv005xr0l7ON3-9Yz54Nmvrlkqa5o2XKn3UEgC_NzoUpuXGs4m-NZFaG1zJcMtM04oZ9H5LWwYKSm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnKwcuB5oC2-KnHsVtiVvgo5LHAubYvnUd6V-lqmEusmlUIxYwY01Hc1EQvluj2MsGMKycy3RVNUTqJjOXjqoSbMG64PFOcilutz9Jw9Md8uu&lib=Mtm8MSmARNO6CqOt3gmiZeTihJxClUTqu")
    fun getData(): Call<GetData>
}