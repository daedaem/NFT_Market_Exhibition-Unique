import Web3 from "web3";
const GANACHE_SERVER_URL = process.env.GANACHE_SERVER_URL;

/**
 * 개인키로부터 주소를 추출합니다.
 * @param {*} privKey 개인키
 * @returns 주소
 */

export default function getAddressFrom(privKey) {
  // if ((privKey[0] === "0") & (privKey[1] === "x")) {
  // } else {
  //   privKey = "0x" + privKey;

  // }
  if (privKey.length === 66 && privKey.startsWith("0x")) {
    // console.log(privKey, "여기발호");
    // console.log(typeof privKey, "여기발호");
    const web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
    const pubKey = web3.eth.accounts.privateKeyToAccount(privKey);
    return pubKey.address;
  } else alert("Please check your Private Key.");
}
