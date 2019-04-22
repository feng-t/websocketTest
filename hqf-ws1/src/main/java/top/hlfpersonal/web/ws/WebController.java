package top.hlfpersonal.web.ws;

import top.hlfpersonal.result.Result;
import top.hlfpersonal.vo.TMsg;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-04-10 22:34
 **/
public interface WebController {

    /**
     * 通知（广播）
     * WS
     * @return
     */
    Result notice();

    /**
     * 发送消息,msg包含发送者,接受者
     * WS方法
     * @param msg
     * @return
     */
    Result sendToUser(TMsg msg);

    /**
     * 发送群消息
     * WS方法
     * @param msg
     * @return
     */
    Result sendToGroup(TMsg msg);

    /**
     * 获取好友信息and分组信息
     * @return
     */
    Result getFriends();


    /**
     * 获取群聊列表
     * @return
     */
    Result getGroups();

}
