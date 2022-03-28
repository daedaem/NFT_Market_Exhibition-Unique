import numeral from "numeral";

// 참고 http://numeraljs.com/
export function convertToAccountingFormat(number) {
  return numeral(number).format(`0,0`);
}
