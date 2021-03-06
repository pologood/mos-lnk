package me.mos.ti.channel;

import me.mos.ti.channel.Channel;

import org.apache.mina.core.session.IoSession;

/**
 * 基于mina的连接通道.
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年6月14日 下午12:13:29
 */
public interface IoSessionChannel extends Channel<IoSession> {
	
}