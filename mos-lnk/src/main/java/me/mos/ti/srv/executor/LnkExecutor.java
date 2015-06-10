package me.mos.ti.srv.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import me.mos.ti.etc.Profile;

/**
 * @author 刘飞 E-mail:liufei_it@126.com
 * 
 * @version 1.0.0
 * @since 2015年6月10日 下午10:49:09
 */
public class LnkExecutor extends ThreadPoolExecutor {

	private static final long DEFAULT_KEEPALIVETIME = 60L;

	public LnkExecutor(Profile profile) {
		super(profile.getCorePoolSize(), profile.getMaximumPoolSize(), DEFAULT_KEEPALIVETIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(profile.getQueueSize()), new LnkThreadFactory(),
				new LnkRejectedExecutionHandler());
	}
}