// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "./token/ERC721/ERC721.sol";
// import "@openzeppelin/contracts/utils/Counters.sol";
import "../node_modules/@openzeppelin/contracts/utils/Counters.sol";

/**
 * PJT Ⅰ - 과제 2) NFT Creator 구현
 * 상태 변수나 함수의 시그니처는 구현에 따라 변경할 수 있습니다.
 */
contract SsafyNFT is ERC721 {
    using Counters for Counters.Counter;
    Counters.Counter private _tokenIds;
    mapping(uint256 => string) tokenURIs;

    constructor() ERC721("SsafyNFT", "SFT") {
        //erc721 생성사 호출
        // TODO
    }

    // function current() public view returns (uint256) {
    //     return _tokenIds;
    // }

    function tokenURI(uint256 tokenId)
        public
        view
        override(ERC721)
        returns (string memory)
    {
        // TODO
        return tokenURIs[tokenId];
    }

    /*
    호출자가 지정한 tokenURI를 새롭게 발행
    내부적으로 새로운 토큰 식별자를 부여받고 _mint()를 호출
    상태 변수에 토큰 식별자의 tokenURI 정보를 추가
    새롭게 생성된 토큰 식별자를 반환
    */
    function create(address to, string memory _tokenURI)
        public
        returns (uint256)
    {
        // TODO
        _tokenIds.increment();

        uint256 newItemid = _tokenIds.current();
        _mint(to, newItemid);
        tokenURIs[newItemid] = _tokenURI;

        return newItemid;
    }
}
