package com.bodhi.framecore.data;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/26 11:24
 * desc :
 */
public abstract class BaseRespData {

    /**
     * 重置清空数据方法，比如在某页面退出登录，其他页面的登录状态全部清空
     */
    public abstract void onReset();
}
