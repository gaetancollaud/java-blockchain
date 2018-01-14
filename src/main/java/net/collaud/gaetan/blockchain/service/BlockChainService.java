package net.collaud.gaetan.blockchain.service;

import lombok.Getter;
import net.collaud.gaetan.blockchain.data.Block;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class BlockChainService {

	@Getter
	private LinkedList<Block> blockchain = new LinkedList<>();
}
