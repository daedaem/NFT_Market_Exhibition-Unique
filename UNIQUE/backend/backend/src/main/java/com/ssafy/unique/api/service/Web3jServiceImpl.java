package com.ssafy.unique.api.service;

import com.ssafy.unique.api.config.Web3jConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Web3jServiceImpl implements Web3jService{

    private final Web3j web3j;

    public Web3jServiceImpl(Web3j web3j) {
        this.web3j = web3j;
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Web3jConfig.class);
    Web3jConfig wc = ac.getBean(Web3jConfig.class);

    @Override
    public BigInteger getBalance() throws Exception {

        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
        System.out.println(web3ClientVersion.getWeb3ClientVersion());

        // 주소를 변수로 선언
        String MY_ADDRESS = "0x22d3D425AddA3B8b07267C64738a5872E2Ef0f39";
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

        BigInteger balance = (BigInteger) results.get(0).getValue();
        System.out.println(balance);

        return balance;
    }

    @Override
    public void tokentransfer() throws Exception{
        try {
            // 접속 여부 확인

            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().sendAsync().get();
            System.out.println(web3ClientVersion.getWeb3ClientVersion());

            // 주소를 변수로 선언
            String MY_ADDRESS = "0x22d3D425AddA3B8b07267C64738a5872E2Ef0f39";
            String USER_PRIVATE_KEY = "0x90967262d9145b76b2ce2cd5955b9ccad07b0cba3d7006dcfe30890a905e65d1";

            String CONTRACT_ADDRESS = "0x6C927304104cdaa5a8b3691E0ADE8a3ded41a333";
            String RECEIVER_ADDRESS = "0x585B365a684C69Dd4d475e444CbD73c6F9a1997e"; // 범주 Wallet 주소 -> 변수로 받을 수 있을 것 -> 현재 유저의 Address를 DB에서 찾아서 사용가능
            String amount = "1";

            long TX_END_CHECK_DURATION = 5000;
            int TX_END_CHECK_RETRY = 3;
            long CHAIN_ID = 31221;
            //		long CHAIN_ID = 202112031219L;

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
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

}
