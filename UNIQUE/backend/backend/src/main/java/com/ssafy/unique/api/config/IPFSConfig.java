package com.ssafy.unique.api.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.ipfs.api.IPFS;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IPFSConfig {

	public IPFS ipfs;

	public IPFSConfig() {
		ipfs = new IPFS("j6e205.p.ssafy.io", 5001);
	}
}
