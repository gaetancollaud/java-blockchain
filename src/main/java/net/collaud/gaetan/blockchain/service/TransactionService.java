package net.collaud.gaetan.blockchain.service;

import lombok.extern.slf4j.Slf4j;
import net.collaud.gaetan.blockchain.data.transactions.AbstractTransaction;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {

	@Autowired
	private RedissonClient redissonClient;

	public <T extends AbstractTransaction> T add(T transaction) {
		transaction.setTimestamp(System.currentTimeMillis());
		// TODO set gain

		getTransactionQueue().add(transaction);
		return transaction;
	}

	public List<AbstractTransaction> getCurrentlyWaintingTransactions() {
		return getTransactionQueue().stream().collect(Collectors.toList());
	}

	protected Queue<AbstractTransaction> getTransactionQueue() {
		return redissonClient.getQueue("waintingTransactions");
	}
}
