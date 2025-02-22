package MessageHandle.ChatProxy;

import MessageHandle.Response;
import java.util.Map;

public interface ChatProxy {
    // 接收客户端请求并发送消息
    public String transmitMessage(Map<String, ?> request_json);

    // 获取消息当前状态
    public String acknowledgeMessage(Map<String, ?> request_json);

    // 获取某个用户所有未接收的消息
    public String pullUnReceivedMessage(Map<String, ?> request_json);

    // 获取一个空闲的消息ID用于发送消息
    public String requestMessageID(Map<String, ?> request_json);
}
