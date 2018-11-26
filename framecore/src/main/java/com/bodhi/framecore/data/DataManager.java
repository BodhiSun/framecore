package com.bodhi.framecore.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 11:35
 * desc :全局的数据请求管理框架，比如Account,AppConf
 */
public abstract class DataManager<T extends BaseRespData> {

    protected T data;

    private List<DataRespListener<T>> listeners = new ArrayList<>();

    private Context appContext;

    public void init(Context context){
        this.appContext=context;
        data = newData();
        listeners.clear();
    }

    public void load(final DataRespListener<T> dataRespListener){
        request(new DataRequestListener<T>(){

            @Override
            public void onResult(T t) {
                if (t!=null) {
                    data=t;
                    update(dataRespListener);
                }
            }
        });
    }

    private void update(DataRespListener<T> targetListener) {
        if (targetListener!=null) {
            targetListener.onData(data);
        }else{
            for (DataRespListener<T> listener : listeners) {
                listener.onData(data);
            }
        }
    }

    public boolean hasListener(DataRespListener<T> listener){
        return listeners.indexOf(listener)>=0;
    }

    public void registerListener(DataRespListener<T> listener){
        int index = listeners.indexOf(listener);
        if (index>=0) {
            //大于0表示存在注册过，无需重新注册
            return;
        }

        listeners.add(listener);
    }

    public void unRegisterListener(DataRespListener<T> listener){
        listeners.remove(listener);
    }

    protected abstract T newData();

    protected abstract void request(DataRequestListener<T> dataRequestListener);

}
