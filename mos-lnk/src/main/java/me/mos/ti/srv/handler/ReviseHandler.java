package me.mos.ti.srv.handler;

import me.mos.ti.channel.Channel;
import me.mos.ti.packet.InRevise;
import me.mos.ti.packet.OutPacket;
import me.mos.ti.packet.OutRevise;
import me.mos.ti.user.User;

/**
 * Revise消息处理器.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年6月2日 下午7:18:45
 */
public class ReviseHandler extends AbstractPacketHandler<InRevise> {

	@Override
	public OutPacket process(Channel<?> channel, InRevise packet) throws Throwable {
		OutRevise outRevise = packet.toOutPacket();
		try {
			User user = userProvider.query(packet.getMid());
			if (user == null) {
				outRevise.err();
				return outRevise;
			}
			user.merge(packet);
			userProvider.update(user);
		} catch (Exception e) {
			outRevise.err();
		}
		return outRevise;
	}
}