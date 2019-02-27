package android.rockchip.update.interfaces;

import android.rockchip.update.dataRecord.RemoteServiceAcountInfo;



/**用于规范远端服务器连接器接口 */
public interface IRemoteServiceConnector{

    /**连接到远端服务器 */
    IClient connectToRemoteService(RemoteServiceAcountInfo accInfo, ServiceCallBack call);
    
    /**关闭远端服务器的链接*/
    int disconnectToRemoteService(IClient clinet);

}