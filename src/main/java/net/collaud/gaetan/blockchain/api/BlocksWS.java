package net.collaud.gaetan.blockchain.api;

import net.collaud.gaetan.blockchain.data.Block;
import net.collaud.gaetan.blockchain.service.BlockChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blocks")
public class BlocksWS {

	@Autowired
	private BlockChainService blockChainService;

	@RequestMapping()
	public List<Block> getAll() {
		return blockChainService.getBlockchain();

	}
}
