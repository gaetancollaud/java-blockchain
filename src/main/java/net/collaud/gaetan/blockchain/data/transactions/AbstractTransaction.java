package net.collaud.gaetan.blockchain.data.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AbstractTransaction {
	public enum TransactionType {
		TASK_DONE,
		VERIFY_TASK,
		TASK_VERIFIED,
		BUY_REWARD
	}

	protected TransactionType type;
	protected long timestamp;
	protected String who;

}
