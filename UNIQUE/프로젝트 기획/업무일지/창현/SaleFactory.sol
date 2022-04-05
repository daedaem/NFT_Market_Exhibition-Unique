// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "./access/Ownable.sol";
import "./token/ERC20/ERC20.sol";
import "./token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

// import "./utils/Counters.sol";

/**
 * PJT Ⅲ - Req.1-SC1 SaleFactory 구현
 * 상태 변수나 함수의 시그니처, 이벤트는 구현에 따라 변경할 수 있습니다.
 */
//  Ownable이 상속되면서  ownable의 constructor를 실행
contract SaleFactory is Ownable {
    using Counters for Counters.Counter;
    Counters.Counter private _workId;

    address public admin;
    address[] public sales;

    event NewSale(
        address indexed _saleContract,
        address indexed _owner,
        uint256 _workId
    );

    constructor() {
        admin = msg.sender;
    }

    /**
     * @dev 반드시 구현해야하는 함수입니다.
     */
    function createSale(
        uint256 itemId,
        uint256 purchasePrice,
        uint256 startTime,
        uint256 endTime,
        address currencyAddress,
        address nftAddress
    ) public returns (address) {
        // Sale Contract를 생성한다
        Sale sale = new Sale(
            msg.sender,
            admin,
            startTime,
            endTime,
            purchasePrice,
            itemId,
            currencyAddress,
            nftAddress
        );

        // 카운터를 자동으로 증가시킨다
        // _workId를 사용해서 해당 Sale이 몇번째 생성된 Sale인지 기록한다
        _workId.increment();

        // event를 사용해서 기록한다
        emit NewSale(address(sale), msg.sender, _workId.current());

        // return으로 생성된 Sale 컨트랙트 주소를 반환한다
        return address(sale);
    }

    function allSales() public view returns (address[] memory) {
        return sales;
    }
}

/**
 *  PJT Ⅲ - Req.1-SC2) Sale 구현
 */
contract Sale {
    SaleFactory salefactory;
    // 생성자에 의해 정해지는 값
    // 판매자정보
    address public seller;
    // 최종구매자정보
    address public buyer;
    // 슈퍼권한자주소
    address admin;
    uint256 public saleStartTime;
    uint256 public saleEndTime;
    uint256 public purchasePrice;
    // 거래할 nft 정보(토큰식별자))
    uint256 public tokenId;
    // 거래시 사용할 erc-20의 주소
    address public currencyAddress;
    // 거래할 nft 정보(nft주소)
    address public nftAddress;
    bool public ended;
    uint256 public workId;
    address public saleFactoryAddress;

    IERC20 public erc20Contract;
    IERC721 public erc721Constract;

    event SaleEnded(address winner, uint256 amount);

    constructor(
        address _seller,
        address _admin,
        uint256 startTime,
        uint256 endTime,
        uint256 _purchasePrice,
        uint256 _tokenId,
        address _currencyAddress,
        address _nftAddress
    ) {
        require(_purchasePrice > 0);
        seller = _seller;
        admin = _admin;
        seller = _seller;
        tokenId = _tokenId;
        // minPrice = _minPrice;
        currencyAddress = _currencyAddress;
        purchasePrice = _purchasePrice;
        saleStartTime = startTime;
        saleEndTime = endTime;
        purchasePrice = _purchasePrice;
        tokenId = _tokenId;
        currencyAddress = _currencyAddress;
        nftAddress = _nftAddress;
        ended = false;
        erc20Contract = IERC20(_currencyAddress);
        erc721Constract = IERC721(_nftAddress);
        workId = _workId;
        saleFactoryAddress = _saleFactoryAddress;
        salefactory = SaleFactory(_saleFactoryAddress);
        // saleFactoryAddress = SaleFactory(_saleFactoryAddress).call(allSales);

        //스마트컨트랙트에게 NFT토큰 전송권한 부여
        // erc721Constract.approve(address(this), tokenId);
        // 소유권을 스마트컨트랙트에게 줘버리자.
        erc721Constract.transferFrom(seller, address(this), tokenId);
    }

    function purchase() public {
        // 구매 희망자가 판매자가 제시한 즉시 구매가에 작품를 구매
        // 판매자가 아닌 경우 호출가능 -> require or modifier 를 사용해서 제약사항 추가
        // 해당 Sale의 판매 시점이 유효한 경우 -> ended의 값 확인 + start, end time확인
        // 구매 희망자가 Sale 컨트랙트에게 구매 희망자의 ERC-20 토큰을 송금할 수 있는 권한을 허용
        // -> 권한을 ERC-20 approve를 통해서 허가해주어야 한다
        // 위 제약사항을 만족한다면,
        // 구매자의 ERC-20 토큰을 즉시 구매가 만큼 판매자에게 전송
        // NFT 소유권을 구매자에게 이전
        // 컨트랙트 거래 상태
    }

    function cancelSales() public {
        // TODO
    }

    function getTimeLeft() public view returns (int256) {
        return (int256)(saleEndTime - block.timestamp);
    }

    function getSaleInfo()
        public
        view
        returns (
            uint256,
            uint256,
            // uint256,
            uint256,
            uint256,
            address,
            address
        )
    {
        return (
            saleStartTime,
            saleEndTime,
            purchasePrice,
            tokenId,
            currencyAddress,
            nftAddress
        );
    }

    // internal 혹은 private 함수 선언시 아래와 같이 _로 시작하도록 네이밍합니다.
    function _end() internal {
        ended = true;
    }

    function _getCurrencyAmount() private view returns (uint256) {
        return erc20Contract.balanceOf(msg.sender);
    }

    // modifier를 사용하여 함수 동작 조건을 재사용하는 것을 권장합니다.
    modifier onlySeller() {
        require(msg.sender == seller, "Sale: You are not seller.");
        // 함수가 실행되는 곳 _;
        _;
    }

    modifier onlyAfterStart() {
        require(
            block.timestamp >= saleStartTime,
            "Sale: This sale is not started."
        );
        _;
    }

    modifier onlyUserPermissioned() {
        require(buyer != address(0), "Sale: This Buyer is not found.");
        require(
            erc20Contract.approve(seller, purchasePrice),
            "Sale:This Contract is not allowed"
        );
        _;
    }
}
