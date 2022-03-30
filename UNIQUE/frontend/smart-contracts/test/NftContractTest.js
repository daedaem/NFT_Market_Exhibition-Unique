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
    // 그안에 create(배포자, 이미지uri이겠지?) 함수 실행
    const firstResult = await instance.create(sender, tokenURI);
    // const tokenid = await instance.current();
    const firstTokenId = firstResult.logs[0].args["2"].toNumber();
    let owner = await instance.ownerOf(firstTokenId);

    // const tokenURIs = await instance.tokenURI(1);

    // TODO
    // 1.다음이 반드시 테스트되어야 합니다.
    assert.equal(sender, owner, "NFT Mint Failed");
    // ----------------------------------------------------------------
    // 첫번째 토큰 식별자
    const secondResult = await instance.transferFrom(sender, receiver, firstTokenId);
    const transferResultTokenId = secondResult.logs[0].args.tokenId.toNumber();

    // 전송되기 전, 토큰의 주인
    // console.log(owner);
    owner = await instance.ownerOf(transferResultTokenId);
    // let owner = await console.log(owner.toNumber());
    // 전송된 후, 토큰의 주인
    // console.log(owner);

    // TODO
    // 2. 다음이 반드시 테스트되어야 합니다.
    assert.equal(receiver, owner, "NFT Transfer Failed.");
    // ----------------------------------------------------------------
    const tokenURIFetched = await instance.tokenURI(transferResultTokenId);
    // console.log(tokenURIFetched);
    // TODO
    // 3. 다음이 반드시 테스트되어야 합니다.
    assert.equal(tokenURI, tokenURIFetched, "Wrong Token Id or URI.");

    // console.log("리십1", secondResult.receipt.logs[0].args["2"].words);
    // console.log("리십2", secondResult.receipt.logs[1].args["2"].words);
    // console.log("로그1", secondResult.logs[0].args["2"].words);
    // console.log("로그2", secondResult.logs[1].args["2"].words);
    // console.log(ERC721.ownerOf(firstTokenId));
    // 토큰식별자 확인방법
    // console.log(firstResult.logs[0].args["2"].toNumber());
    // console.log(firstResult.logs[0].args.tokenId.toNumber());
    // console.log(firstResult.logs[0].args.tokenId.words[0]);

    // console.log(createResult2.logs[0].args["2"].toNumber());
    // console.log(createResult2.logs[0].args.tokenId.toNumber());
    // console.log(createResult2.logs[0].args.tokenId.words[0]);
    // console.log(createResult2.logs[0].args);

    // return firstResult;

    // .then(function () {
    //   finals = instance.current();
    //   console.log(finals.once);
    //   console.log(finals.emit);
    // })
    // // .then(function (value) {
    // // await current();
    // // console.log(value);
    // // })
    // .catch((e) => {
    //   console.log(e);
    // });
    // 토큰보내기
    // console.log("원래 주인은", firstResult.newItemid);

    // console.log(firstResult);
    // let from = addressone;
    // let to = addressTwo;
    // instance.transferFrom(from, to);
    // 밑은 주인 확인하는거
    // await console.log("이제 주인은");
    // const owner = await instance.ownerOf(1);

    // const mytokenuri = instance.tokenURI()
    // instance.create()

    // return NftCreator.deployed().then(instance => console.log

    // return NftCreator.deployed().then(instance => { instance
    //     instance.create().then(result => console.log(result)
    //         // let sender =
    //         // let owner =
    // })

    // //     // TODO
    // //     // 다음이 반드시 테스트되어야 합니다.
    // //     // assert.equal(sender, owner, "NFT Mint Failed");
    // //     // assert.equal(receiver, owner, "NFT Transfer Failed.");
    // //     // assert.equal(tokenURI, tokenURIFetched, "Wrong Token Id or URI.")
  });
});
