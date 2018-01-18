package net.collaud.gaetan.blockchain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.collaud.gaetan.blockchain.data.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConnexionService {
	@Value("${blockchain.connexion.port:2626}")
	protected int port;

	@Autowired
	private NodeService nodeService;

	private Map<String, ConnexionHandler> connexions;

	private ServerSocket serverSocket;

	public ConnexionService() {
		this.connexions = new ConcurrentHashMap<>();
	}

//	@PostConstruct
//	protected void postConstruct() throws IOException {
//		this.serverSocket = new ServerSocket(port);
//		//Todo use thread pool
//		new Thread(() -> {
//			while (!this.serverSocket.isClosed()) {
//				try {
//					Socket newSocket = this.serverSocket.accept();
//				} catch (IOException e) {
//					LOG.error("Error while waiting for new connexion on port {}", port, e);
//				}
//			}
//		}).start();
//		new Thread(() -> {
//			while (!this.serverSocket.isClosed()) {
//				try {
//					checkNodeConnexion();
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//				}
//			}
//		}).start();
//	}

	@PreDestroy
	protected void preDestroy() {
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			LOG.error("Unable to close server socket", e);
		}
		this.connexions.values().forEach(c -> c.close());
	}

	protected void checkNodeConnexion() {
		Set<String> exisitingConnexion = this.connexions.keySet();
		List<String> newNodes = nodeService.getOtherNodes().stream()
				.filter(n -> !exisitingConnexion.contains(n))
				.collect(Collectors.toList());
		LOG.debug("Checking connexion. Connected to {} nodes, {} new nodes", exisitingConnexion.size(), newNodes.size());
		newNodes
				.forEach(n -> openConnexion(n));
	}

	protected void openConnexion(String node) {
		LOG.debug("Opening connexion to {}", node);
		try {
			Socket socket = new Socket(InetAddress.getByName(node), port);

			this.connexions.put(node, new ConnexionHandler(node, socket));
		} catch (Exception ex) {
			LOG.error("Error while connecting to {}", node, ex);
		}
	}

	public class ConnexionHandler implements Runnable {
		protected final Socket socket;
		protected final Thread thread;
		protected final String node;

		public ConnexionHandler(String node, Socket socket) {
			this.node = node;
			this.socket = socket;
			this.thread = new Thread(this, "Node " + node);
		}

		public void start() {
			this.thread.start();
		}

		public void run() {
			LOG.info("Starting listening on node {}", node);
		}

		public void close() {
			try {
				this.socket.close();
			} catch (Exception ex) {
				LOG.error("Unable to close connexion to node {}", node, ex);
			}
		}
	}

}
