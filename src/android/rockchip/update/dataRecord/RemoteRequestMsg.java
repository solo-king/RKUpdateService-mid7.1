package android.rockchip.update.dataRecord;

public  class RemoteRequestMsg{

    /**消息类型定义 */
    enum MsgType {

        INIT ,TO_SERVICE,FROM_SERVICE
    };
    /**消息类型 */
    private MsgType msgType =  MsgType.INIT;
    /**消息本体 */
    private String msg;

    
    public RemoteRequestMsg() {
    }

    public RemoteRequestMsg(MsgType msgType, String msg) {
        this.msgType = msgType;
        this.msg = msg;
    }

    public MsgType getMsgType() {
        return this.msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "{" +
            " msgType='" + getMsgType() + "'" +
            ", msg='" + getMsg() + "'" +
            "}";
    }
}