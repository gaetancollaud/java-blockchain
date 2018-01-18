package net.collaud.gaetan.blockchain.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NodeService {

	public static final long NODE_LAST_WILL_TIME_MS = 30000;
	public static final long NODE_ANNONCE_INTERVAL_MS = 10000;

	@Value("${blockchain.connexion.address:}")
	private String fixedIpAddr;

	@Autowired
	private RedissonClient redissonClient;

	private Thread thread;

//	@PostConstruct
//	protected void postConstruct() {
//		//TODO thread pool
//		thread = new Thread(() -> {
//			try {
//				while (true) {
//					this.annonceMe();
//					Thread.sleep(NODE_ANNONCE_INTERVAL_MS);
//				}
//			} catch (InterruptedException ex) {
//				// nothing to do
//			}
//		}, "nodeDiscover");
//		thread.start();
//	}

	public Collection<String> getNodes() {
		return getNodesMap().keySet();
	}

	public List<String> getOtherNodes() {
		String me = getNodeAddr();
		return getNodesMap().keySet().stream()
				.filter(n -> !n.equals(me))
				.collect(Collectors.toList());
	}

	protected RMapCache<String, Long> getNodesMap() {
		return redissonClient.getMapCache("nodes");
	}

	protected void annonceMe() {
		String myAddr = getNodeAddr();
		LOG.debug("Annoncing me on {}", myAddr);
		getNodesMap().put(myAddr, System.currentTimeMillis(), NODE_LAST_WILL_TIME_MS, TimeUnit.MILLISECONDS);
	}

	protected String getNodeAddr() {
		if (!StringUtils.isEmpty(fixedIpAddr)) {
			return fixedIpAddr;
		}
		try {
			return Inet4Address.getLocalHost().getHostAddress();
		} catch (Exception ex) {
			LOG.error("Unable to get ip address", ex);
		}
		return null;
	}

}
