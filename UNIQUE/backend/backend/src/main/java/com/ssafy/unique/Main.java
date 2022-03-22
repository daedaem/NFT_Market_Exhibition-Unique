//package com.ssafy.unique;
//
//import java.io.IOException;
//
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.EthBlockNumber;
//import org.web3j.protocol.core.methods.response.EthGasPrice;
//import org.web3j.protocol.core.methods.response.Web3ClientVersion;
//import org.web3j.protocol.http.HttpService;
//
//public class Main {
//
//	public static void main(String[] args) throws Exception {
//
//		//web3j 노드 연결("노드의 엔드포인트")
//		Web3j web3 = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
//
//
//		//API 작업 진행
//		//<response> = web3.<opreation>.send()
//
//		try {
//			// web3_clientVersion returns the current client version.
////			Web3ClientVersion clientVersion = web3.web3ClientVersion().send();
//
//			EthBlockNumber blockNumber = web3.ethBlockNumber().send();
//
//			EthGasPrice gasPrice = web3.ethGasPrice().send();
//
////		      System.out.println("Client version: " + clientVersion.getWeb3ClientVersion());
//		      System.out.println("Block number: " + blockNumber.getBlockNumber());
//		      System.out.println("Gas price: " + gasPrice.getGasPrice());
//		}
//		catch(IOException e) {
//			throw new RuntimeException("Error whilst sending json-rpc requests",e);
//		}
//	}
//
//}
