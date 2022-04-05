package com.ssafy.unique.api.controller;

import com.ssafy.unique.api.service.Web3jService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(
        origins = { "http://localhost:5500", "http://172.30.1.59:5500", "http://192.168.0.100:5500", "http://192.168.0.40:5500","https://j6e205.p.ssafy.io" },
        allowCredentials = "true", // axios가 sessionId를 계속 다른것을 보내는데, 이것을 고정시켜준다
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
                RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })

@RestController
@RequestMapping(value="/web3j")
@Tag(name = "nft Controller", description = "ERC20 관련 데이터를 다룬다.")
public class Web3jController {

    private final Web3jService web3jService;

    public Web3jController(Web3jService web3jService) {
        this.web3jService = web3jService;
    }


    @GetMapping("/transfer")
    public void transfer() throws Exception{
        web3jService.tokentransfer();
    }

    @GetMapping("/getBalance")
    public BigInteger getBalance() throws Exception
    {

        Web3j web3 = Web3j.build(new HttpService("http://20.196.209.2:8545"));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
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
        org.web3j.protocol.core.methods.response.EthCall response = web3.ethCall(
                Transaction.createEthCallTransaction(MY_ADDRESS, CONTRACT_ADDRESS, txData),
                DefaultBlockParameterName.LATEST).sendAsync().get();

        List<Type> results = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());

        BigInteger balance = (BigInteger) results.get(0).getValue();
        System.out.println(balance);

        return balance;

    }
}
