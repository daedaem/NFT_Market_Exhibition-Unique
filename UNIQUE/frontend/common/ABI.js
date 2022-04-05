/* 
[컨트랙트 ABI]
- 작성한 스마트 컨트랙트의 컴파일 결과로부터 얻은 ABI(in JSON)를 복사하여 붙여넣습니다.
- NFT_ABI: SSAFY NFT 컨트랙트 ABI
- SALE_FACTORY_ABI: SALE Factory 컨트랙트 ABI
- SALE_ABI: SALE 컨트랙트 ABI
*/
import SsafyNFT from "../../smart-contracts/build/contracts/SsafyNFT.json";
import SsafyToken from "../../smart-contracts/build/contracts/SsafyToken.json";
import SaleFactory from "../../smart-contracts/build/contracts/SaleFactory.json";
import Sale from "../../smart-contracts/build/contracts/Sale.json";

const ABI = {
  CONTRACT_ABI: {
    NFT_ABI: SsafyNFT.abi,
    SALE_FACTORY_ABI: SaleFactory.abi,
    SALE_ABI: Sale.abi,
    SsafyToken_ABI: SsafyToken.abi,
  },
};

export default ABI;
