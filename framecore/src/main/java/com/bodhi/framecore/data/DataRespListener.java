package com.bodhi.framecore.data;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 11:28
 * desc :
 */
public interface DataRespListener<T extends BaseRespData> {

    void onData(T t);
}
