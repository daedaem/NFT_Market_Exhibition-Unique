/**
 * PJT Ⅰ - 과제 3 테스트 코드 작성
 * @dev NFT mint, transfer, and compare URI
 */
const NftCreator = artifacts.require("SsafyNFT");

contract("NftCreator", (accounts) => {
  it("NFT mint, transfer, and compare URI", async () => {
    // SssafyNFT.SOL배포
    let tokenURI = "https://images.pexels.com/photos/1368382/pexels-photo-1368382.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";

    let sender = accounts[0];
    let receiver = accounts[1];
    let instance = await NftCreator.deployed();
    const firstResult = await instance.create(sender, tokenURI);
    const firstTokenId = firstResult.logs[0].args["2"].toNumber();
    let owner = await instance.ownerOf(firstTokenId);

    assert.equal(sender, owner, "NFT Mint Failed");

    const secondResult = await instance.transferFrom(sender, receiver, firstTokenId);
    const transferResultTokenId = secondResult.logs[0].args.tokenId.toNumber();

    owner = await instance.ownerOf(transferResultTokenId);

    assert.equal(receiver, owner, "NFT Transfer Failed.");
    const tokenURIFetched = await instance.tokenURI(transferResultTokenId);

    assert.equal(tokenURI, tokenURIFetched, "Wrong Token Id or URI.");
  });
});
