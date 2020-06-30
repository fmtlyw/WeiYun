package com.lyw.moudlehttp.http.response;

import java.io.IOException;
import okhttp3.Call;

/**
 * Created by JuAn_Zhangsongzhou on 2017/8/23.
 */

public interface ResponseListener<T> {

    void onFailure(Call call, IOException e);

    void onResponse(Call call, int responseCode, T result);
}
