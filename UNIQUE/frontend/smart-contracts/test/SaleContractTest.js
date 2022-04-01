/**
 *  PJT Ⅲ - Req.1-SC3) 시나리오 테스트
 */
// artifacts.require는 migration과 같음
const SsafyToken = artifacts.require("SsafyToken");
const SsafyNFT = artifacts.require("SsafyNFT");
const SaleFactory = artifacts.require("SaleFactory");
const Sale = artifacts.require("Sale");
let ssafyTokenContract, salesFactoryContract, nftContract, salesContract;
let itemId = 0;

contract("Sale Contract Testing", (accounts) => {
  const mintAmount = 10000;
  const uri = "testURI";

  async function print(title) {
    const seller = accounts[0];
    const bidder1 = accounts[1];
    const bidder2 = accounts[2];
    console.log(`\n--------------------  ${title} --------------------`);
    console.log(`Seller: ${seller} ${await getBalance(seller)}`);
    console.log(`Bidder1: ${bidder1} ${await getBalance(bidder1)}`);
    console.log(`Bidder2: ${bidder2} ${await getBalance(bidder2)}\n`);
  }

  it("Purchase", async () => {
    const seller = accounts[0];
    const purchaser = accounts[1];
    // 1) 테스트를 위한 임의의 ERC-20 토큰 생성 후 10,000토큰 발행
    // ssafytoken erc20 컨트랙트 배포
    const ssafyTokenContract = await SsafyToken.deployed();
    // ssafytoken의 컨트랙트 address
    const currencyAddress = ssafyTokenContract.address;
    // erc20컨트랙트를 통해 필요한 양만큼 토큰 생성
    await ssafyTokenContract.mint(mintAmount);

    // 2) 구매자 주소로 1,000토큰 부여
    // 배포 주소로부터 구매자 주소로 1,000토큰 부여
    const tokenretransferesult = await ssafyTokenContract.forceToTransfer(accounts[0], purchaser, 1000);
    // 3) 판매자 NFT 생성(즉시 구매가 100, 현재시각이 판매시간, 판매 종료시간은 10초 후)
    // nft생성컨트랙트 배포
    nftContract = await SsafyNFT.deployed();
    // nft생성 컨트랙트 어드레스
    const nftAddress = nftContract.address;
    // NFT생성 반환값
    const nftContractResult = await nftContract.create(seller, uri);
    // 민팅된 토큰 식별자 확인
    const TokenId = nftContractResult.logs[0].args["2"].toNumber();
    // 토큰 소유자 확인
    let owner = nftContract.ownerOf(TokenId);
    // console.log("원래", owner);
    // NFT 판매 컨트랙트 배포
    salesFactoryContract = await SaleFactory.deployed();

    // 판매 시각, 종료시각 설정
    const startTime = Math.floor(new Date() / 1000);
    const endTime = startTime + 10;
    // NFT 판매 함수 호출
    const purchaseprice = 100;
    const SalesFactoryContractResult = await salesFactoryContract.createSale(TokenId, purchaseprice, startTime, endTime, currencyAddress, nftAddress);
    const Allsales = await salesFactoryContract.allSales();

    //  5) 구매자 100토큰 purchase()호출
    // 권한 부여
    createSaleCA = SalesFactoryContractResult.logs[0].args._saleContract;
    // erc20 토큰 approve
    await ssafyTokenContract.approve(createSaleCA, purchaseprice, { from: purchaser });
    // await ssafyTokenContract.approve({ from: purchaser }, seller, purchaseprice + 100);

    // const results1t = await ssafyTokenContract.allowance(seller, createSaleCA);
    // await ssafyTokenContract.approve(purchaser, await ssafyTokenContract.balanceOf(purchaser));
    // await ssafyTokenContract.approve(purchaser, 0);

    // console.log("1번쓰", results1t.toNumber());
    // erc721 토큰 approve
    await nftContract.approve(createSaleCA, TokenId);
    // purchase 함수호출
    const createSaleInstance = await Sale.at(createSaleCA);
    const finalresults = await createSaleInstance.purchase({ from: purchaser });
    // console.log(finalresults);
    const newowner = nftContract.ownerOf(TokenId);
    // console.log("지금", newowner);
    // await child1.purchase({ from: purchaser });

    // 다음을 테스트를 통과해야합니다.
    // TODO  // 구매자의 잔액이 900과 같다. 최종 NFT소유자가 구매자.
    // 확인사항
    assert.equal(purchaser, await nftContract.ownerOf(TokenId), "Not Owned By Purchaser");
    assert.equal(900, await ssafyTokenContract.balanceOf(purchaser), "Transfer Failed");
  });
  // it("Bid and confirm", async () => {
  //   const seller = accounts[0];
  //   const bidder1 = accounts[1];
  //   const bidder2 = accounts[2]; // purchaser

  //   // TODO
  //   // 다음을 테스트를 통과해야합니다.
  //   // assert.equal(bidder2, await getNftOwner(), "Confirm Failed");
  //   // assert.equal(1000, await getBalance(bidder1), "Refund Failed");
  // });

  // it("Bid and Purchase", async () => {
  //   const seller = accounts[0];
  //   const bidder = accounts[1];
  //   const purchaser = accounts[2];

  //   // TODO
  //   // 다음을 테스트를 통과해야합니다.
  //   // assert.equal(purchaser, await getNftOwner(), "Not Owned By Purchaser");
  //   // assert.equal(1000, await getBalance(bidder), "Refund Failed");
  //   // assert.equal(900, await getBalance(purchaser), "Transfer Failed");
  // });

  // it("Bid and Cancel", async () => {
  //   const seller = accounts[0];
  //   const bidder = accounts[1];

  //   // TODO
  //   // 다음을 테스트를 통과해야합니다.
  //   // assert.equal(seller, await getNftOwner(), "Cancellation Failed");
  //   // assert.equal(1000, await getBalance(bidder), "Refund Failed");
  // });
});
