package net.collaud.gaetan.blockchain.data.transactions;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionTaskDone extends AbstractTransaction {

	public String taskName;
	public double gain;

	@Builder
	public TransactionTaskDone(TransactionType transactionType, long timestamp, String who, String taskName, double gain) {
		super(transactionType, timestamp, who);
		this.taskName = taskName;
		this.gain = gain;
	}
}
