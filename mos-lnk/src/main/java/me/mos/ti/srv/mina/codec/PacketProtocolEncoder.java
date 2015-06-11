package me.mos.ti.srv.mina.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 报文消息编码器.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年6月11日 下午5:45:30
 */
final class PacketProtocolEncoder implements ProtocolEncoder {
	
	private static final Logger log = LoggerFactory.getLogger(PacketProtocolEncoder.class);
	
	final Charset charset;

	PacketProtocolEncoder(Charset charset) {
		super();
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		
	}
}