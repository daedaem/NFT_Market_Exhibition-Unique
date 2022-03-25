# ERC-20

---

### mapping(address => uint256) private _balances;

- 사용자의 잔액을 보관
- 토큰을 보유한다는 것은, 사실 토큰 컨트랙트의 해당 _balances에 주소와 숫자가 매핑하여 저장되어 있는 것에 불과하다
- 비슷한 의미로, transfer 역시 단순히 해당 숫자를 변경하는 과정
- 하지만, 해당 값을 마구잡이로 변경할 수 있으면 문제가 발생하기 때문에 몇가지 검증 절차가 필요하다

### mapping(address => mapping(address => uint256)) private _allowances;

- 사용자의 계좌에서 상대방이 토큰을 빼올 수 있는 수를 정의
- 첫번째 address에는 계좌의 주소
- 두번째 address에는 토큰을 빼갈 수 있는 계좌의 주소
- uint256은 빼갈 수 있는 토큰의 수

**⇒ ERC20 token의 전송과정을 정확히 표현하자면**

<aside>
💡 토큰을 말 그대로 “보내는 게” 아니라 저 하나의 스마트 컨트랙트한테 상태 업데이트 해달라고 보내는 것

</aside>

### ERC20의 한계점

- 매우 단순하다! 최초의 표준 토큰이고 직관적이기 때문에 가장 많이 사용되는 것
- 가장 큰 문제점은 토큰이 유실된다는 점
    - 토큰의 전송이 토큰을 받는 주소와는 무관하게 이루어지기 때문에 발생하는 문제
    - 토큰을 받는 컨트랙트가 해당 토 큰을 다룰 수 있는 인터페이스를 가지고 있는지 토큰의 전송 전에 확인할 수 있다면, 잘못된 주소로 전송함에 따른 토큰의 유실의 방지 가능
    - 이를 해결하기 위해 등장한 토큰이 ERC777

### Constructor

- name, symbol, decimals 를 변수로 받는다
- 변수 설정 이외에 _mint 함수를 실행한다
- **name** : 토큰의 이름을 반환
- **symbol** : 토큰의 이름을 줄여서 표현한 토큰 심볼을 반환
- **decimals** :  사용자 표현을 위한 소수 자리수를 반환, Ether와 Wei의 경우 18
- **_mint** : 해당 컨트랙트를 시작한 msg.sender에게 지정한 만큼의 토큰을 발행한다
    - amount 만큼의 토큰을 생성하고, account에 할당한다
    - 전체 공급량을 증가시킨다
    - 우선, account가 zero-account이면 안되기 때문에 require를 사용
    - emit Transfer 이벤트를 통해서 log를 저장
    - _mint는 스마트 컨트랙트를 배포할 때 사전 정의된 양의 토큰을 발행하기 위해 constructor에서 한번만 호출된다
    - 추후, 추가 토큰을 발행하려면 mint와 같은 새로운 공개 메소드를 도입해야한다 → 해당 메소드는 오직 권한이 있는 사용자만이 발행할 수 있어야 하므로, 주의해서 구현해야한다

```solidity
function _mint(address account, uint256 amount) internal {
    require(account != address(0), "ERC20: mint to the zero address");

    _totalSupply = _totalSupply.add(amount);
    _balances[account] = _balances[account].add(amount);
    emit Transfer(address(0), account, amount);
}
```

### function totalSupply() public view returns (uint256);

- _totalSupply에 저장되어 있는 생성된 모든 토큰의 숫자를 반환
- return _totalSupply;

### function balanceOf(address account) public view returns (uint256);

- account에 저장되어 있는 잔액을 반환
- return _balances[account];

### function allowance(address owner, address spender) public view returns (uint256);

- owner가 spender에게 인출을 허락한 토큰의 개수를 반환
- return _allowances[owner][spender];
- 앞서 설명한 것 처럼, transfer는 토큰을 직접 건내주는 동작이 아니라 내 지갑에서 얼마를 인출해도 된다고 허락하는 동작이다

### function approve(address spender, uint256 value) public returns (bool);

- spender에게 value만큼의 토큰을 인출할 권리를 부여한다
- 이 함수를 이용할 때는 반드시 Approval 이벤트 함수를 호출해야한다
- _approve(msg.sender, spender, value);

```solidity
function _approve( address owner, address spender, uint256 value ) internal {
    require(owner != address(0), "ERC20: approve from the zero address");
    require(spender != address(0), "ERC20: approve to the zero address");

    _allowances[owner][spender] = value;
    emit Approval(owner, spender, value);
}
```

- 기본적으로 owner와 spender는 zero-address이면 안되기 때문에 require 사용
- 그 다음, _allowances의 값을 수정하여  owner의 계좌에서 spender가 얼마만큼의 토큰을 빼올 수 있는지 설정한다
- emit Approval 이벤트를 통해서 해당 내용을 log로 남긴다

### function transferFrom( address sender, address recipient, uint256 amount) public returns (bool);

- sender의 계좌에서 value개의 토큰을 recipient에게 보낸다
- 단, 이 함수는 approve 함수를 통해 인출할 권리를 받은 spender만 실행할 수 있다
- 함수 내부적으로 _transfer 동작과 _approve 동작이 이뤄진다
    - transfer 동작으로 토큰을 전송, approve 동작으로 allownaces를 다시 원상복구 시킨다

```solidity
function _transfer( address sender, address recipient, uint256 amount ) internal {
    require(sender != address(0), "ERC20: transfer from the zero address");
    require(recipient != address(0), "ERC20: transfer to the zero address");

    _balances[sender] = _balances[sender].sub(amount);
    _balances[recipient] = _balances[recipient].add(amount);
    emit Transfer(sender, recipient, amount);
}
```

- 기본적으로 sender와 recipient는 zero-address이면 안되기 때문에 require 사용
- sender의 잔고가 최소 amount이상이 되어야 한다
- 그 다음, _balances의 값을 수정하여 sender와 recipient의 계좌의 토큰의 수를 수정한다
- emit Transfer 이벤트를 통해서 해당 내용을 log로 남긴다

### function increaseAllowance(address spender, uint256 addedValue) public returns (bool);

- ERC 규약에 있는 함수는 아니지만, approve 함수에서 값을 바꿀 때 0으로 변경한 후 다시 원하는 값으로 바꾸는 것은 많이 불편핳다
- 차액 만큼 더하거나 빼는 함수를 추가적으로 제공하여 편리성 제공
- msg.sender가 자신의 계좌를 인출할 권리를 spender에게 부여한다
- _approve 함수를 통해서 addedValue 만큼의 인출 권리를 부여한다

### function decreaseAllowance(address spender, uint256 subtractedValue) public returns (bool);

- msg.sender가 자신의 계좌를 인출할 권리를 spender에게서 뺏는다
- _approve 함수를 통해서 subtractedValue 만큼의 인출 권리를 뺏는다

### function _burnFrom(address account, uint256 amount) internal;

- account로부터 amount만큼의 토큰을 파괴하고, 호출자의 허용량으로부터 amount만큼을 공제합니다.
- 내부적으로 _burn 함수를 통해서 토큰을 zero-address에 전송하고, _approve 함수를 통해서 호출자의 인출 허용량을 줄인다