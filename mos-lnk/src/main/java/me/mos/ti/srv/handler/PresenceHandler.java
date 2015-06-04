package me.mos.ti.srv.handler;

import java.util.List;

import me.mos.ti.message.Message;
import me.mos.ti.packet.InPresence;
import me.mos.ti.packet.OutMessage;
import me.mos.ti.packet.OutPacket;
import me.mos.ti.packet.OutPresence;
import me.mos.ti.srv.Channel;
import me.mos.ti.srv.ServerProcessor;

import org.springframework.util.CollectionUtils;

/**
 * Presence出席消息处理器.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年6月2日 下午7:22:00
 */
public class PresenceHandler extends AbstractPacketHandler<InPresence> {

	public PresenceHandler(ServerProcessor processor) {
		super(processor);
	}

	@Override
	public OutPacket process(Channel channel, InPresence packet) throws Throwable {
		OutPresence resp = packet.toOutPacket();
		try {
			processor.online(channel);
			List<Message> offlineMessageList = messageProvider.queryMessageList(packet.getMid());
			if (!CollectionUtils.isEmpty(offlineMessageList)) {
				for (Message message : offlineMessageList) {
					OutMessage outMessage = message.toOutMessage();
					channel.write(outMessage);
					messageProvider.delete(message.getId());
				}
			}
		} catch (Exception e) {
			return resp.err();
		}
		return resp;
	}
}