package com.bodhi.framecore.data;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 11:24
 * desc :
 */
public interface DataRequestListener<T extends BaseRespData> {

    void onResult(T t);
}
