package net.collaud.gaetan.blockchain.api;

import net.collaud.gaetan.blockchain.data.transactions.AbstractTransaction;
import net.collaud.gaetan.blockchain.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionWS {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(method = RequestMethod.POST)
	public <T extends AbstractTransaction> T add(T transaction) {
		return transactionService.add(transaction);
	}

	@RequestMapping("waiting")
	public List<AbstractTransaction> findAllWaiting() {
		return transactionService.getCurrentlyWaintingTransactions();
	}
}
