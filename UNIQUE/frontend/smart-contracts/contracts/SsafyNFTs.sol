// SPDX-License-Identifier: MIT
// OpenZeppelin Contracts v4.4.1 (utils/Counters.sol)

pragma solidity ^0.8.4;

import "./token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

// import "../node_modules/@openzeppelin/contracts/utils/Counters.sol";

/**
 * PJT Ⅰ - 과제 2) NFT Creator 구현
 * 상태 변수나 함수의 시그니처는 구현에 따라 변경할 수 있습니다.
 */
// contract SsafyNFT is ERC721 {
//     uint256 private _tokenIds;
//     mapping(uint256 => string) tokenURIs;

//     constructor() ERC721("ssafyNFT", "SFT") {
//         // TODO
//     }

//     function current() public view returns (uint256) {
//         return _tokenIds;
//     }
// //  참고 https://blog.logrocket.com/create-nft-minter-moralis-solidity-next-js/

// // https://www.youtube.com/watch?v=TYezN5r228U

//     function tokenURI(uint256 tokenId) public view override(ERC721) returns (string memory) {
//         // TODO
//         return tokenURIs[tokenId];
//     }

//     function create(address to, string memory _tokenURI) public returns (uint256) {
//         // TODO

//         uint256 newItemid = uint256(keccak256("_tokenURI"));
//         _mint(to, newItemid);
//         tokenURIs[newItemid] = _tokenURI;

//         return newItemid;
//     }
// }

/**
 * PJT Ⅰ - 과제 2) NFT Creator 구현
 * 상태 변수나 함수의 시그니처는 구현에 따라 변경할 수 있습니다.
 */
contract SsafyNFT is ERC721 {
    using Counters for Counters.Counter;
    Counters.Counter private _tokenIds;
    mapping(uint256 => string) tokenURIs;
    mapping(uint256 => address) contractAddress;

    constructor() ERC721("ssafyNFT", "SFT") {
        // TODO
    }

    //  참고 https://blog.logrocket.com/create-nft-minter-moralis-solidity-next-js/

    // https://www.youtube.com/watch?v=TYezN5r228U

    function tokenURI(uint256 tokenId)
        public
        view
        override
        returns (string memory)
    {
        // TODO
        return tokenURIs[tokenId];
    }

    function current() public view returns (uint256) {
        // return _tokenIds;
        // _tokenIds = Counters._value;
        uint256 tokenid = _tokenIds.current();
        return tokenid;
    }

    function create(address to, string memory _tokenURI)
        public
        returns (uint256)
    {
        // TODO
        // 현재까지 생성된 NFT 토큰 ID에서 하나 올린다.
        _tokenIds.increment();

        // 현재 만들고 있는 NFT로 새 ID할당
        uint256 newItemid = _tokenIds.current();

        // 받을 사람 주소와 새 NFT의 ID값으로 생성
        _mint(to, newItemid);

        // nft요청자에게 발급되게 하려면 아래처럼
        // _mint(msg.sender, newItemid);

        // 새 NFTID와 NFT의 JSON정보인 URI로 토큰 URI 생성
        tokenURIs[newItemid] = _tokenURI;
        contractAddress[newItemid] = address(this);
        return newItemid;
    }

    // function getContractAddress(uint256 _newItemid) public view returns(address){
    //     return contractAddress[_newItemid];
    // }
}
