package net.collaud.gaetan.blockchain.api;

import net.collaud.gaetan.blockchain.data.Block;
import net.collaud.gaetan.blockchain.service.BlockChainService;
import net.collaud.gaetan.blockchain.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/nodes")
public class NodeWS {

	@Autowired
	private NodeService nodeService;

	@RequestMapping()
	public List<String> getAll() {
		return new ArrayList<>(nodeService.getNodes());

	}
}
