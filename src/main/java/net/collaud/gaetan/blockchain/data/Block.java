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
	private String hash;

}
