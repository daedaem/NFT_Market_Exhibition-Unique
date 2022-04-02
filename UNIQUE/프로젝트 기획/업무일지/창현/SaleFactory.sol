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
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
        // uint256 minPrice,
=======
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
        uint256 purchasePrice,
        uint256 startTime,
        uint256 endTime,
        address currencyAddress,
        address nftAddress
    ) public returns (address) {
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
        // TODO
        // 계약 내에서 다른 새로운 계약 생성을 위해서
        // 4-1).
        Sale sale = new Sale(
            // 매개변수로는 밑에 Sale 컨트랙트의 constructor 생성자 매개변수 입력
            // 위에서 입력한 값 그대로
            admin,
            msg.sender,
            itemId,
            // minPrice,
            //
            currencyAddress,
            purchasePrice,
            startTime,
            endTime,
            //
            nftAddress,
            _workId.current(),
            address(this)
        );
        // setApprovalForAll(address(this), true);
        // ERC721.setApprovalForAll(address(sale), true);
        // erc721Constract.setApprovalForAll(msg.sender, address(sale), true);

        // 4-2). 등록된 세일들에 대한 내용들을 sales리스트에 추가한다.
        // sales[_workId.current()] = address(this);
        sales.push(address(sale));
        // 4-3). 생성된 sale 컨트랙트 주소정보를 반환한다.
        // 밑둘다 있어야하는건지 하나만 있으면 되는건지
        emit NewSale(address(sale), msg.sender, _workId.current());
        _workId.increment();

=======
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
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
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
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
    // uint256 public minPrice;
    // 6 즉시구매가
=======
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
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

<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
    // 현재 최고 입찰 상태
    // address public highestBidder;
    // uint256 public highestBid;

=======
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
    IERC20 public erc20Contract;
    IERC721 public erc721Constract;

    event SaleEnded(address winner, uint256 amount);

    constructor(
        address _seller,
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
        uint256 _tokenId,
        // uint256 _minPrice,
        address _currencyAddress,
        uint256 _purchasePrice,
        uint256 startTime,
        uint256 endTime,
        address _nftAddress,
        uint256 _workId,
        address _saleFactoryAddress
    ) {
        // require(_minPrice > 0);
=======
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
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
        admin = _admin;
        seller = _seller;
        tokenId = _tokenId;
        // minPrice = _minPrice;
        currencyAddress = _currencyAddress;
        purchasePrice = _purchasePrice;
        saleStartTime = startTime;
        saleEndTime = endTime;
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
=======
        purchasePrice = _purchasePrice;
        tokenId = _tokenId;
        currencyAddress = _currencyAddress;
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
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

<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
    // function bid(uint256 bid_amount) public onlyAfterStart onlyUserPermissioned {
    //     // TODO
    //     require(msg.sender != seller, "Sale: You couldn't be a bidder because you are a seller.");
    //     require(bid_amount >= minPrice, "Sale: You should pay more than minPrice");
    //     require(bid_amount > highestBid, "Sale: You should pay more than highestBid");
    //     require(bid_amount <= purchasePrice, "Sale: You should pay less than buynow's price");
    //     highestBid = bid_amount;
    //     highestBidder = msg.sender;
    //     // 함수 호출자의 계좌로 부터 seller가 bid_amount만큼 허용하도록
    //     erc20Contract.approve(seller, bid_amount);
    //     // transferOwnership(msg.sender)
    //     emit HighestBidIncereased(msg.sender, bid_amount);

    //     // 왜 쓸데없이 bid하는데 보내고 환불하고하지 끝나고 보내면 안되나
    //     erc20Contract.transfer(seller, bid_amount);

    // }

    // modifier onlyAfterStart 사용
    function purchase() public onlyAfterStart onlyUserPermissioned {
        // function purchase() public onlyAfterStart onlyUserPermissioned payable {
        // TODO

        // 5- 제약사항들
        // 판매자가 아닌 경우 호출가능
        require(
            msg.sender != seller,
            "Sale: You couldn't purchase this item because you are a seller."
        );
        // 해당 Sale의 판매 시점이 유효한 경우
        // 끝난시간보다 현재시각이 아직 남았어야하고
        // 현재시간보다 판매시점이 전이어야함
        require(saleEndTime - block.timestamp >= 0);
        // require(block.timestamp >= saleStartTime);
        // 구매 희망자가 Sale 컨트랙트에게 구매 희망자의 ERC-20 토큰을 송금할 수 있는 권한을 허용
        // 5-1)은 일단 입찰기능이 없어서 우리는 해결안해도 됨
        // 5-2) 구매자의 ERC-20 토큰을 즉시 구매가만큼 판매자에게 송금한다.
        // 얼마 보낼 수 있는지 체킹해야하는거 아닌가
        uint256 nowpayable = _getCurrencyAmount();
        require(purchasePrice > nowpayable);

        // 함수 호출자의 계좌로 부터 seller가 bid_amount만큼 인출 허용하도록
        erc20Contract.approve(address(this), purchasePrice);
        erc20Contract.transfer(seller, purchasePrice);
        // 5-3) NFT 소유권을 구매자에게 이전
        // NFT 소유권 승인 APPROVE
        //
        erc721Constract.approve(msg.sender, tokenId);
        erc721Constract.safeTransferFrom(address(this), msg.sender, tokenId);

        // 5-4) 컨트랙트의 거래 상태와 구매자 정보를 업데이트 한다.
        // 거래상태 끝남
        _end();
        // 구매자 정보
        // buyer = msg.sender;
        emit SaleEnded(msg.sender, purchasePrice);
    }

    // function confirmItem() public {
    // //     // TODO
    //     require(saleEndTime <= block.timestamp, "Sale: The auction is not ended yet.");
    //     require(msg.sender == highestBidder, "Sale: You're are not a highest bidder.");

    //     // 6-1) 최종 제안가를 판매자에게 송금한다.
    //     erc20Contract.transferFrom(highestBidder, seller, highestBid);
    //     // 6-2) NFT 소유권을 구매자에게 이전한다.
    //     erc721Constract.safeTransferFrom(seller, highestBidder, tokenId);
    //     // 6-3) 컨트랙트의 거래 상태와 구매자 정보를 업데이트한다.
    //     _end();
    //     buyer = msg.sender;
    // }

    // // 아마도 환불은 없을듯 입찰이 없어서
    // modifier onlyseller 사용
    function cancelSales() public onlySeller {
        //     // TODO
        // 7 제약사항
        // 철회시점이 유효한 경우
        require(
            saleEndTime - block.timestamp > 0,
            "Sale: Sale is already ended."
        );
        // 호출자가  판매자 혹은 관리자인 경우
        require(
            msg.sender == seller || msg.sender == admin,
            "Sale: You are not seller or administrator."
        );
        // 7-1) 환불을 진행한다.
        // erc20Contract.transferFrom(seller, highestBidder, highestBid);
        //  7-2) 소유권을 판매자에게 되돌려 준다.
        // erc721Constract.setApprovalForAll(msg.sender, address(this), false);
        erc721Constract.approve(seller, tokenId);
        // erc721Constract.safeTransferFrom(address(this), msg.sender, tokenId);
        erc721Constract.safeTransferFrom(address(this), seller, tokenId);
        // erc721Constract.safeTransferFrom(highestBidder, seller, tokenId);
        // 7-3) 컨트랙트의 거래 상태를 업데이트 한다.
        // 구매 취소하면
        // sales 배열에서 삭제
        // delete SaleFactory().call()
        // delete saleFactoryAddress.call(bytes4(allSales()))[workId];
        delete salefactory.allSales()[workId];
        _end();
    }

    // function delete() public {
    // require(msg.sender == owner, "")
    // }
=======
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
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol

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
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
            // address,
            // uint256,
=======
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
            address,
            address
        )
    {
        return (
            saleStartTime,
            saleEndTime,
<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
            // minPrice,
            purchasePrice,
            tokenId,
            // highestBidder,
            // highestBid,
=======
            purchasePrice,
            tokenId,
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
            currencyAddress,
            nftAddress
        );
    }

<<<<<<< HEAD:UNIQUE/smart-contracts/contracts/SaleFactory.sol
    // function getHighestBid() public view returns(uint256){
    //     return highestBid;
    // }

=======
>>>>>>> 5795a0dd1e975e2490e5ca5011e05bbb6e37fade:UNIQUE/프로젝트 기획/업무일지/창현/SaleFactory.sol
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
