package net.collaud.gaetan.blockchain.data;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Block {
	private long index;
	private String previousHash;
	private long timestamp;
	private String data;
	private String nonce;
	private String hash;

	public String getBlockStringWithoutNonce() {
		StringBuilder sb = new StringBuilder();
		sb.append(index);
		sb.append(previousHash);
		sb.append(timestamp);
		sb.append(data);
		return sb.toString();
	}

	public String getBlockString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getBlockStringWithoutNonce());
		sb.append(nonce);
		return sb.toString();
	}

}
