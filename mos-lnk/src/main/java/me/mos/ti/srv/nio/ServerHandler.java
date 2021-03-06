package me.mos.ti.srv.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import me.mos.ti.channel.Channels;
import me.mos.ti.channel.NioSockChannel;
import me.mos.ti.packet.InPacket;
import me.mos.ti.packet.OutPacket;
import me.mos.ti.parser.PacketParser;
import me.mos.ti.srv.PacketProtocol;
import me.mos.ti.srv.process.ServerProcessor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lnk服务通道事件处理句柄.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年6月2日 上午12:08:50
 */
final class ServerHandler implements Runnable, PacketProtocol {

	private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);

	private final NioSockChannel channel;

	private final ServerProcessor processor;

	private final PacketParser parser;

	public ServerHandler(NioSockChannel channel, ServerProcessor processor, PacketParser parser) {
		super();
		this.channel = channel;
		this.processor = processor;
		this.parser = parser;
	}

	@Override
	public void run() {
		String packet = StringUtils.EMPTY;
		try {
			if (!channel.isConnect()) {
				channel.close();
				Channels.offline(channel);
				return;
			}
			packet = read();
			if (StringUtils.isBlank(packet)) {
				return;
			}
			log.error("Original Incoming Packet : {}", packet);
			InPacket inPacket = parser.parse(packet);
			if (inPacket == null) {
				return;
			}
			channel.setChannelId(inPacket.getMid());
			OutPacket outPacket = processor.process(channel, inPacket);
			if (outPacket == null) {
				return;
			}
			channel.deliver(outPacket);
		} catch (Throwable e) {
			log.error("Nio ServerHandler Error.");
		}
	}

	private String read() {
		SocketChannel channel = this.channel.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(READ_BYTE_BUF);
		try {
			while (channel.read(buf) > 0) {
				buf.flip();
				// buf.get(dst, offset, length);
			}
		} catch (Throwable e) {
			log.error("ServerHandler read Message Error.", e);
		} finally {
			this.channel.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
		return null;
	}
}