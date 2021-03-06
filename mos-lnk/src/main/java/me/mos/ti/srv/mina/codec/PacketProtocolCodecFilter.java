package me.mos.ti.srv.mina.codec;

import java.nio.charset.Charset;

import me.mos.ti.parser.PacketParser;

import org.apache.mina.filter.codec.ProtocolCodecFilter;

/**
 * 报文消息编码解码器过滤器.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 *
 * @version 1.0.0
 * @since 2015年6月11日 下午5:51:23
 */
public class PacketProtocolCodecFilter extends ProtocolCodecFilter {

	public PacketProtocolCodecFilter(Charset charset, PacketParser parser) {
		super(new PacketProtocolCodecFactory(charset, parser));
	}
}