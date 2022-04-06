package com.ssafy.unique.api.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import com.ssafy.unique.api.config.Web3jConfig;
import com.ssafy.unique.api.response.TokenRes;
import com.ssafy.unique.db.entity.Member;
import com.ssafy.unique.db.repository.MemberRepository;

@Service
public class Web3jServiceImpl implements Web3jService{

	private final Web3j web3j;
	private final MemberRepository memberRepository;

	public Web3jServiceImpl(Web3j web3j, MemberRepository _memberRepository) {
		this.web3j = web3j;
		this.memberRepository = _memberRepository;
	}

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Web3jConfig.class);
	Web3jConfig wc = ac.getBean(Web3jConfig.class);

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Override
	public TokenRes getBalance() throws Exception {

		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
		System.out.println(web3ClientVersion.getWeb3ClientVersion());


		// Security Context에서 memberSeq를 구한다
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long memberSeq = Long.parseLong(authentication.getName());

		Member member = memberRepository.findById(memberSeq).get();


		
		// 주소를 변수로 선언
//		String MY_ADDRESS = "0x22d3D425AddA3B8b07267C64738a5872E2Ef0f39";
		String MY_ADDRESS = member.getMemberAddress();
		String CONTRACT_ADDRESS = "0x6C927304104cdaa5a8b3691E0ADE8a3ded41a333";

		// input parameter
		List<Type> params = new ArrayList<>();
		params.add(new Address(MY_ADDRESS));
		
		// output parameters
		List<TypeReference<?>> returnTypes = Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {});

		Function function = new Function("balanceOf", params, returnTypes);

		String txData = FunctionEncoder.encode(function);
		org.web3j.protocol.core.methods.response.EthCall response = web3j.ethCall(
				Transaction.createEthCallTransaction(MY_ADDRESS, CONTRACT_ADDRESS, txData),
				DefaultBlockParameterName.LATEST).sendAsync().get();
		
		List<Type> results = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());

		BigInteger balance =  (BigInteger) results.get(0).getValue();
		System.out.println(balance);

		
		Long ssf = balance.longValue();
		
		
		// balanceOf의 값이 현재 저장된 값과 다르다면, 최신화한다
		if ( member.getSsf() != ssf ) {
			memberRepository.updateSsfById(ssf, member.getMemberSeq());
		}

		TokenRes res = new TokenRes();

		res.setResult(SUCCESS);
		res.setSsf(ssf);


		return res;
	}



	@Override
	public TokenRes tokentransfer(Long ssf) throws Exception{

		TokenRes res = new TokenRes();

		try {
			// 접속 여부 확인

			Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
			System.out.println(web3ClientVersion.getWeb3ClientVersion());

			// 주소를 변수로 선언
			String MY_ADDRESS = "0x22d3D425AddA3B8b07267C64738a5872E2Ef0f39";
			String USER_PRIVATE_KEY = "0x90967262d9145b76b2ce2cd5955b9ccad07b0cba3d7006dcfe30890a905e65d1";
			String CONTRACT_ADDRESS = "0x6C927304104cdaa5a8b3691E0ADE8a3ded41a333";

			// RECEIVER_ADDRESS는 member에서 꺼내서 사용한다
			// Security Context에서 memberSeq를 구한다
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Long memberSeq = Long.parseLong(authentication.getName());
			
			Member member = memberRepository.findById(memberSeq).get();
			
			String RECEIVER_ADDRESS = member.getMemberAddress(); 
			String amount = Long.toString(ssf);
			
			System.out.println(RECEIVER_ADDRESS);
			System.out.println(amount);


			long TX_END_CHECK_DURATION = 5000;
			int TX_END_CHECK_RETRY = 3;
			long CHAIN_ID = 31221;

			// credential 생성
			Credentials credential = Credentials.create(USER_PRIVATE_KEY);

			// input parameter
			List<Type> params = new ArrayList<Type>();
			params.add(new Address(RECEIVER_ADDRESS));
			params.add(new Uint256(new BigInteger(amount)));

			// output parameters
			List<TypeReference<?>> returnTypes = Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {});

			// Function 생성
			Function function = new Function(
					"transfer",
					params,
					returnTypes
					);


			// Function Encoded
			String txData = FunctionEncoder.encode(function);

			// nonce 구하기
			EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credential.getAddress(), DefaultBlockParameterName.LATEST).send();
			BigInteger nonce = ethGetTransactionCount.getTransactionCount();

			// Gas Provider 구하기
			ContractGasProvider gasProvider = new DefaultGasProvider();


			// rawTransaction 생성
			RawTransaction rawTransaction = RawTransaction.createTransaction(
					nonce,
					new BigInteger("0"),
					gasProvider.getGasLimit("TRANSFER"),
					CONTRACT_ADDRESS,
					txData);

			// 서명후 메시지를 hex로 변환
			byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, CHAIN_ID, credential);
			String hexValue = Numeric.toHexString(signedMessage);



			// 서명한 다음, 트랜잭션 진행
			EthSendTransaction transactionResponse = web3j.ethSendRawTransaction(hexValue)
					.sendAsync().get();



			Thread.sleep(3000);

			System.out.println(transactionResponse.getId());
			System.out.println(transactionResponse.getJsonrpc());
			System.out.println(transactionResponse.getRawResponse());
			System.out.println(transactionResponse.getResult());
			System.out.println(transactionResponse.getTransactionHash());



			if ( transactionResponse.getError() == null ) {
				res.setResult(SUCCESS);
			} else {
				res.setResult(FAIL);
			}

		} catch( Exception e ) {
			e.printStackTrace();
			res.setResult(FAIL);
		}

		return res;
	}

}
