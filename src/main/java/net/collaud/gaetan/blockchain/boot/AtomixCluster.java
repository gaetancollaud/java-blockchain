package net.collaud.gaetan.blockchain.boot;

//import io.atomix.AtomixReplica;
//import io.atomix.catalyst.transport.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class AtomixCluster implements CommandLineRunner {
	@Override
	public void run(String... strings) throws Exception {
//		AtomixReplica.Builder builder = AtomixReplica.builder(new Address("localhost", 8700));
//
//		AtomixReplica replica = builder.build();
//
//		CompletableFuture<AtomixReplica> future = replica.bootstrap();
//
//		LOG.info("Joining atomix replica...");
//		future.join();
//		LOG.info("Atomix replica joined");
	}
}
