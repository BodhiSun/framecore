package com.bodhi.framecore.permission;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 17:18
 * desc :
 */
public interface PermissionRequestListener {

    void onGrantSuccess();

    void onGrantFail();
}
